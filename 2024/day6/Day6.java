package day6;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day6 {

    public static void main(String[] args) {
        try {
            String day = "day6";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }
            
            // part 1
            int sum1 = 0;
            int row_pos = 0;
            int col_pos = 0;
            int init_row = 0;
            int init_col = 0;
            int visited[][] = new int[inputLines.size()][inputLines.get(0).length()];
            int width = inputLines.get(0).length();
            int height = inputLines.size();
            int direction = 0; // 0 = up, 1 = right, 2 = down, 3 = left
            for (int i = 0; i < inputLines.size(); i++) {
                String line = inputLines.get(i);
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) == '^') {
                        init_row = i;
                        init_col = j;
                        row_pos = i;
                        col_pos = j;
                        visited[row_pos][col_pos] = 1;
                        System.out.println("row_pos: " + row_pos + ", col_pos: " + col_pos);
                    }
                }
            }

            visited = getVisited(inputLines, visited, row_pos, col_pos, direction, width, height, 0);

            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited[i].length; j++) {
                    if (visited[i][j] != 0) {
                        sum1 += 1;
                    }
                }
            }

            System.out.println(sum1);

            // part 2
            int sum2 = 0;
            direction = 0;
            row_pos = init_row;
            col_pos = init_col;
            visited = new int[inputLines.size()][inputLines.get(0).length()];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (!(inputLines.get(i).charAt(j) == '#') && !(inputLines.get(i).charAt(j) == '^')) {
                        inputLines.set(i, inputLines.get(i).substring(0, j) + "#" + inputLines.get(i).substring(j + 1));

                        visited = getVisited(inputLines, visited, row_pos, col_pos, direction, width, height, sum1);
                        if (visited[0][0] == -42) {
                            sum2 += 1;
                        }
                        visited = new int[inputLines.size()][inputLines.get(0).length()];
                        
                        inputLines.set(i, inputLines.get(i).substring(0, j) + "." + inputLines.get(i).substring(j + 1));
                    }
                }
            }
            System.out.println(sum2);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int[][] getVisited(List<String> inputLines, int[][] visited, int row_pos, int col_pos, int direction, int width, int height, int sum1) {
        int sum = 0;
        while (0 <= row_pos && row_pos < height && 0 <= col_pos && col_pos < width) { 
            visited[row_pos][col_pos] += 1;
            if (sum1 != 0) {
                if (sum > sum1*5) {
                    visited[0][0] = -42;
                    return visited;
                }
            }
            
            if (direction == 0) {
                if (row_pos == 0 || inputLines.get(row_pos - 1).charAt(col_pos) != '#') {
                    row_pos--;
                    sum++;
                } else {
                    direction = 1;
                }
            }
            if (direction == 1) {
                if (col_pos == width - 1 || inputLines.get(row_pos).charAt(col_pos + 1) != '#') {
                    col_pos++;
                    sum++;
                } else {
                    direction = 2;
                }
            }
            if (direction == 2) {
                if (row_pos == height - 1 || inputLines.get(row_pos + 1).charAt(col_pos) != '#') {
                    row_pos++;
                    sum++;
                } else {
                    direction = 3;
                }
            }
            if (direction == 3) {
                if (col_pos == 0 || inputLines.get(row_pos).charAt(col_pos - 1) != '#') {
                    col_pos--;
                    sum++;
                } else {
                    direction = 0;
                }
            }
        }
        return visited;
    }
}