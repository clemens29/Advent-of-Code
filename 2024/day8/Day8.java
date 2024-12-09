package day8;
import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day8 {

    public static void main(String[] args) {
        try {
            String day = "day8";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            
            int sum1 = 0;
            int sum2 = 0;
            char[][] grid = new char[inputLines.size()][inputLines.get(0).length()];
            HashMap<Character, List<Integer[]>> antennas = new HashMap<>();

            for (int i = 0; i < inputLines.size(); i++) {
                grid[i] = inputLines.get(i).toCharArray();
            }

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    char c = grid[i][j];
                    Integer[] pos = {i, j};
                    if (c != '.') {
                        if (antennas.containsKey(c)) {
                            List<Integer[]> positions = antennas.get(c);
                            positions.add(pos);
                            antennas.put(c, positions);
                        } else {
                            List<Integer[]> positions = new ArrayList<>();
                            positions.add(pos);
                            antennas.put(c,positions);
                        }
                    }
                }
            }

            char[][] antinodeGrid = new char[grid.length][grid[0].length];
            char[][] antinodeGrid2 = new char[grid.length][grid[0].length];
            for (int i = 0; i < antinodeGrid.length; i++) {
                for (int j = 0; j < antinodeGrid[i].length; j++) {
                    antinodeGrid[i][j] = '.';
                    if (grid[i][j] != '.') {
                        antinodeGrid2[i][j] = '#';
                    } else
                    antinodeGrid2[i][j] = '.';
                }
            }

            for (Character c : antennas.keySet()) {
                List<Integer[]> positions = antennas.get(c);
                for (int i = 0; i < positions.size(); i++) {
                    int x1 = positions.get(i)[0];
                    int y1 = positions.get(i)[1];
                    for (int j = 0; j < positions.size(); j++) {
                        if (i != j) {
                            int x2 = positions.get(j)[0];
                            int y2 = positions.get(j)[1];
                            int distanceX = x1-x2;
                            int distanceY = y1-y2;
                            int antinodeX = x1 + distanceX;
                            int antinodeY = y1 + distanceY;
                            if (antinodeX >= 0 && antinodeX < grid.length && antinodeY >= 0 && antinodeY < grid[0].length) {
                                if (grid[antinodeX][antinodeY] != '#') {
                                    antinodeGrid[antinodeX][antinodeY] = '#';
                                }
                            }
                            while (antinodeX >= 0 && antinodeX < grid.length && antinodeY >= 0 && antinodeY < grid[0].length) {
                                antinodeGrid2[antinodeX][antinodeY] = '#';
                                antinodeX += distanceX;
                                antinodeY += distanceY;
                            }

                        }
                    }
                }
            }

            for (int i = 0; i < antinodeGrid.length; i++) {
                for (int j = 0; j < antinodeGrid[i].length; j++) {
                    if (antinodeGrid[i][j] == '#') {
                        sum1++;
                    }
                    if (antinodeGrid2[i][j] == '#') {
                        sum2++;
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
}