package day10;

import Template.Template;
import java.io.IOException;
import java.util.List;

public class Day10 {

    public static void main(String[] args) {
        try {
            String day = "day10";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            int sum1 = 0;
            int sum2 = 0;
            int rows = inputLines.size();
            int cols = inputLines.get(0).length();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (inputLines.get(row).charAt(col) == '0') {
                        boolean[][] visited1 = new boolean[rows][cols];
                        boolean[][] visited2 = new boolean[rows][cols];
                        int score = getScore(row, col, inputLines, visited1, false);
                        int score2 = getScore(row, col, inputLines, visited2, true);
                        sum1 += score;
                        sum2 += score2;
                    }
                }
            }

            System.out.println(sum1);
            System.out.println(sum2);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int getScore(int row, int col, List<String> inputLines, boolean[][] visited, boolean distinctRoutes) {
        if (visited[row][col]) return 0;
        visited[row][col] = true;
    
        int currentHeight = Character.getNumericValue(inputLines.get(row).charAt(col));

        if (currentHeight == 9) {
            if (distinctRoutes) {
                visited[row][col] = false;
            }
            return 1;
        }
    
        int score = 0;

        int[] row_offset = {-1, 0, 1, 0};
        int[] col_offset = {0, 1, 0, -1};
    
        for (int i = 0; i < 4; i++) {
            int newRow = row + row_offset[i];
            int newCol = col + col_offset[i];
    
            if (isValid(row, col, newRow, newCol, inputLines, visited)) {
                score += getScore(newRow, newCol, inputLines, visited, distinctRoutes);
            }
        }
    
        if (distinctRoutes) {
            visited[row][col] = false;
        }
        return score;
    }

    public static boolean isValid(int row, int col, int newRow, int newCol, List<String> inputLines, boolean[][] visited) {
        if (newRow < 0 || newRow >= inputLines.size() || newCol < 0 || newCol >= inputLines.get(0).length()) {
            return false;
        }

        if (visited[newRow][newCol]) {
            return false;
        }

        int currentHeight = Character.getNumericValue(inputLines.get(row).charAt(col));
        int newHeight = Character.getNumericValue(inputLines.get(newRow).charAt(newCol));

        return newHeight == currentHeight + 1;
    }
}
