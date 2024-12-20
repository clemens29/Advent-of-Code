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
        // create a list only for the outer perimeter of the positions in the region
        int i = 0;
        for (List<Integer[]> region : regions) {
            boolean[][] grid = new boolean[140][140];
            for (Integer[] pos : region) {
                grid[pos[0]][pos[1]] = true;
            }
            Integer[] first = region.get(0);
            
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
