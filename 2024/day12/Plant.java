package day12;

import java.util.Arrays;
import java.util.List;

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
                    Integer[] new_pos = {x + x_directions[j], y + y_directions[j]};
                    boolean contains = region.stream().anyMatch(p -> Arrays.equals(p, new_pos));
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
            // sides von links nach rechts
            for (Integer[] pos : region) {
                int x = pos[0];
                int y = pos[1];
                int[] x_directions = {0};
                int[] y_directions = {1};
                for (int j = 0; j < 1; j++) {
                    Integer[] new_pos = {x + x_directions[j], y + y_directions[j]};
                    boolean contains = region.stream().anyMatch(p -> Arrays.equals(p, new_pos));
                    if (!contains) {
                        System.out.println("New side: " + new_pos[0] + ", " + new_pos[1]);
                        sides[i]+=1;
                    }

                }
            }
            // sides von oben nach unten
            for (Integer[] pos : region) {
                int x = pos[0];
                int y = pos[1];
                int x_direction = 1;
                int y_direction = 0;
                Integer[] new_pos = {x + x_direction, y + y_direction};
                boolean contains = region.stream().anyMatch(p -> Arrays.equals(p, new_pos));
                if (!contains) {
                    System.out.println("New side: " + new_pos[0] + ", " + new_pos[1]);
                    sides[i]+=1;
                    break;
                }
            }
            // sides von rechts nach links
            for (Integer[] pos : region) {
                int x = pos[0];
                int y = pos[1];
                int x_direction = 0;
                int y_direction = 1;
                Integer[] new_pos = {x + x_direction, y + y_direction};
                boolean contains = region.stream().anyMatch(p -> Arrays.equals(p, new_pos));
                if (!contains) {
                    System.out.println("New side: " + new_pos[0] + ", " + new_pos[1]);
                    sides[i]+=1;
                    break;
                }
            }
            // sides von unten nach oben
            for (Integer[] pos : region) {
                int x = pos[0];
                int y = pos[1];
                int x_direction = 1;
                int y_direction = 0;
                Integer[] new_pos = {x + x_direction, y + y_direction};
                boolean contains = region.stream().anyMatch(p -> Arrays.equals(p, new_pos));
                if (!contains) {
                    System.out.println("New side: " + new_pos[0] + ", " + new_pos[1]);
                    sides[i]+=1;
                    break;
                }

            }

            i++;
        }
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
