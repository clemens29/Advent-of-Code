package day16;

import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day16 {

    public static void main(String[] args) {
        try {
            String day = "day16";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            int height = inputLines.size();
            int width = inputLines.get(0).length();
            char[][] grid = new char[height][width];
            int[] start = new int[2];
            int[] end = new int[2];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    grid[i][j] = inputLines.get(i).charAt(j);
                    if (grid[i][j] == 'S') {
                        start = new int[]{i, j};
                    }
                    if (grid[i][j] == 'E') {
                        end = new int[]{i, j};
                    }
                }
            }

            // part 1 
            PathFinder pathFinder = new PathFinder(grid, start, end);
            int sum1 = pathFinder.getLowestScore();
            System.out.println(sum1);

            // part 2
            int sum2 = pathFinder.getTiles(grid);
            System.out.println(sum2);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(grid[i]);
        }
    }
}
