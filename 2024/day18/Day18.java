package day18;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day18 {

    public static void main(String[] args) {
        try {
            String day = "day18";
            List<String> inputLines = Template.readInputFile(day, "input.txt");
            
            int maxSize = 1024;
            int maxSizeGrid = 71;
        
            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
                maxSize = 12;
                maxSizeGrid = 7;
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            char[][] grid = new char[maxSizeGrid][maxSizeGrid];
            for (int i = 0; i < maxSizeGrid; i++) {
                for (int j = 0; j < maxSizeGrid; j++) {
                    grid[i][j] = '.';
                }
            }
            for (int i = 0; i < maxSize; i++) {
                String[] parts = inputLines.get(i).split(",");
                int[] pos = new int[2];
                pos[0] = Integer.parseInt(parts[1]);
                pos[1] = Integer.parseInt(parts[0]);
                grid[pos[0]][pos[1]] = '#';
            }

            int[] start = new int[2];
            start[0] = 0;
            start[1] = 0;
            int[] end = new int[2];
            end[0] = maxSizeGrid-1;
            end[1] = maxSizeGrid-1;

            // part 1
            PathFinder pathFinder = new PathFinder(grid, start, end);
            int sum1 = pathFinder.getLowestScore();
            System.out.println(sum1);

            // part 2	
            for (int i = maxSize; i < inputLines.size(); i++) {
                String[] parts = inputLines.get(i).split(",");
                int[] pos = new int[2];
                pos[0] = Integer.parseInt(parts[1]);
                pos[1] = Integer.parseInt(parts[0]);
                grid[pos[0]][pos[1]] = '#';
                pathFinder = new PathFinder(grid, start, end);
                int sum2 = pathFinder.getLowestScore();
                if (sum2 == Integer.MAX_VALUE) {
                    System.out.println(pos[1] + "," + pos[0]);
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printGrid(char[][] grid, int maxSize) {
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}