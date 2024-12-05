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
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }
            
            // part 1
            int sum = 0;
            int width = inputLines.get(0).length();
            int height = inputLines.size();
            String sub = "";
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (j + 4 <= width) {
                        // horizontal vorwärts
                        sub = inputLines.get(i).substring(j, j + 4);
                        if (sub.equals("XMAS")) {
                            sum++;
                        }
                        // horizontal rückwärts
                        String sb = new StringBuilder(sub).reverse().toString();
                        if (sb.equals("XMAS")) {
                            sum++;
                        }
                    }
                    if (i + 4 <= height) {
                        // vertikal vorwärts
                        sub = inputLines.get(i).substring(j, j + 1) + inputLines.get(i + 1).substring(j, j + 1) + inputLines.get(i + 2).substring(j, j + 1) + inputLines.get(i + 3).substring(j, j + 1);
                        if (sub.equals("XMAS")) {
                            sum++;
                        }
                        // vertikal rückwärts
                        String sb = new StringBuilder(sub).reverse().toString();
                        if (sb.equals("XMAS")) {
                            sum++;
                        }
                    }
                    if (j + 4 <= width && i + 4 <= height) {
                        // diagonal rechts vorwärts
                        sub = inputLines.get(i).substring(j, j + 1) + inputLines.get(i + 1).substring(j + 1, j + 2) + inputLines.get(i + 2).substring(j + 2, j + 3) + inputLines.get(i + 3).substring(j + 3, j + 4);
                        if (sub.equals("XMAS")) {
                            sum++;
                        }
                    }
                    if (i - 3 >= 0 && j + 4 <= width) {
                        // diagonal rechts rückwärts
                        sub = inputLines.get(i).substring(j, j + 1) + inputLines.get(i - 1).substring(j + 1, j + 2) + inputLines.get(i - 2).substring(j + 2, j + 3) + inputLines.get(i - 3).substring(j + 3, j + 4);
                        if (sub.equals("XMAS")) {
                            sum++;
                        }
                    }
                    if (i + 4 <= height && j - 3 >= 0) {
                        // diagonal links vorwärts
                        sub = inputLines.get(i).substring(j, j + 1) + inputLines.get(i + 1).substring(j - 1, j) + inputLines.get(i + 2).substring(j - 2, j - 1) + inputLines.get(i + 3).substring(j - 3, j - 2);
                        if (sub.equals("XMAS")) {
                            sum++;
                        }
                    }
                    if (i - 3 >= 0 && j - 3 >= 0) {
                        // diagonal links rückwärts
                        sub = inputLines.get(i).substring(j, j + 1) + inputLines.get(i - 1).substring(j - 1, j) + inputLines.get(i - 2).substring(j - 2, j - 1) + inputLines.get(i - 3).substring(j - 3, j - 2);
                        if (sub.equals("XMAS")) {
                            sum++;
                        }
                    }
                }
            }
            System.out.println(sum);

            // part 2
            sum = 0;
            char[][] grid = new char[3][3];
            for (int i = 0; i <= height-3; i++) {
                for (int j = 0; j <= width-3; j++) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            grid[k][l] = inputLines.get(i+k).charAt(j+l);
                        }
                    }
                    sum += checkGrid(grid, i, j);
                }
            }
            System.out.println(sum);


        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int checkGrid (char[][] grid, int i, int j) {
        String word1 = grid[0][0] + "" + grid[1][1] + "" + grid[2][2];
        String word2 = grid[0][2] + "" + grid[1][1] + "" + grid[2][0];
        if ((word1.equals("MAS") || word1.equals("SAM")) && (word2.equals("MAS") || word2.equals("SAM"))) {
            return 1;
        }
        return 0;
        
    }
}