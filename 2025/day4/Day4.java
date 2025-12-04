package day4;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day4 {

    public static void main(String[] args) {
        try {
            String day = "day4";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2025 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2025");
            }

            Long sum1 = 0L;
            int height = inputLines.size();
            int width = inputLines.get(0).length();

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int count = 0;
                    if (inputLines.get(i).charAt(j) != '@') continue;
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (k == 0 && l == 0) continue;
                            if (i+k >= 0 && i+k < height && j+l >= 0 && j+l < width ) {
                                if (inputLines.get(i+k).charAt(j+l) == '@') {
                                    count++;
                                }
                            }
                        }
                    }
                    System.out.println(count);
                    if (count < 4) {
                        sum1++;
                    }
                }
            }

            System.out.println("Part1: " + sum1);

            sum1 = 0L;

            char[][] grid = new char[inputLines.size()][inputLines.get(0).length()];

            for (int i = 0; i < height; i++) {
                grid[i] = inputLines.get(i).toCharArray();
            }


            for (int round = 0; round < 10000; round++) {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        int count = 0;
                        if (grid[i][j] != '@') continue;
                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {
                                if (k == 0 && l == 0) continue;
                                if (i+k >= 0 && i+k < height && j+l >= 0 && j+l < width ) {
                                    if (grid[i+k][j+l] == '@') {
                                        count++;
                                    }
                                }
                            }
                        }
                        if (count < 4) {
                            sum1++;
                            grid[i][j] = '.';
                        }
                    }
                }
                System.out.println(sum1);
            }
            

            System.out.println("Part2: " + sum1);

            for (int i = 0; i < height; i++) {
                    System.out.println(grid[i]);
                }
            
            

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}