package day12;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Plant {
    private char name;
    private int[] area;
    private int[] perimeter;
    private int[] sides;
    private List<List<Integer[]>> regions;

    public Plant(char name, List<List<Integer[]>> regions) {
        this.name = name;
        this.area = new int[regions.size()];
        this.perimeter = new int[regions.size()];
        this.sides = new int[regions.size()];
        this.regions = regions; 
    }

    public int calcAreaRegion(List<Integer[]> region) {
        return region.size();
    }

    public void calcArea() {
        int i = 0;
        for (List<Integer[]> region : regions) {
            area[i] = calcAreaRegion(region);
            i++;
        }
    }

    public void calcPerimeter() {
        int i = 0;
        for (List<Integer[]> region : regions) {
            perimeter[i] = 4 * calcAreaRegion(region);
            for (Integer[] pos : region) {
                int x = pos[0];
                int y = pos[1];
                int[] x_directions = {0, 1};
                int[] y_directions = {1, 0};
                for (int j = 0; j < 2; j++) {
                    int new_x = x + x_directions[j];
                    int new_y = y + y_directions[j];
                    boolean contains = region.stream().anyMatch(p -> p[0] == new_x && p[1] == new_y);
                    if (contains) {
                        perimeter[i] -= 2;
                    }
                }
            }
            i++;
        }
    }

    public void calcSides() {
        int i = 0;
        for (List<Integer[]> region : regions) {
            sides[i] = 0;
    
            // Berechne die Anzahl der Ecken
            int cornerCount = countCorners(region);
            System.out.println("Eckenanzahl: " + cornerCount);
            
            i++;
        }
    }
    
    // Zählt die Ecken in der Region
    private int countCorners(List<Integer[]> region) {
        int cornerCount = 0;
        
        // Definiere die vier möglichen Nachbarschaftsrichtungen (oben, rechts, unten, links)
        int[] x_directions = {0, 1, 0, -1};
        int[] y_directions = {1, 0, -1, 0};
        
        // Gehe durch jede Position in der Region
        for (Integer[] pos : region) {
            int x = pos[0];
            int y = pos[1];
            
            // Zähle die fehlenden benachbarten Zellen (Nachbarn außerhalb der Region)
            int missingSides = 0;
            
            // Überprüfe alle vier Nachbarn
            for (int j = 0; j < 4; j++) {
                int new_x = x + x_directions[j];
                int new_y = y + y_directions[j];
                
                // Prüfe, ob der Nachbar außerhalb der Region ist
                boolean contains = region.stream().anyMatch(p -> p[0] == new_x && p[1] == new_y);
                
                // Wenn der Nachbar nicht vorhanden ist, dann fehlt eine Seite
                if (!contains) {
                    missingSides++;
                }
            }
    
            // Wenn mindestens 2 benachbarte Seiten fehlen, ist es eine Ecke
            if (missingSides >= 2) {
                cornerCount++;
                System.out.println("Fehlende Seite bei: " + x+ ", " + y);
            }
        }
        
        return cornerCount;
    }
    
    
    
    

    public int getSum(boolean part2) {
        int sum = 0;
        calcArea();
        calcPerimeter();
        calcSides();
        for (int i = 0; i < regions.size(); i++) {
            if (part2) {
                System.out.println("Area: " + area[i] + " Sides: " + sides[i]);
                sum += area[i] * sides[i];
            } else {
                sum += area[i] * perimeter[i];
            }
        }
        return sum;
    }

}
