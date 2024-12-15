package day14;

import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day14 {

    public static void main(String[] args) {
        try {
            String day = "day14";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            int maxWidth = 101;
            int maxHeight = 103;
            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
                maxWidth = 11;
                maxHeight = 7;
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            ArrayList<Robot> robots = new ArrayList<>();

            for (String line : inputLines) {
                String[] parts = line.split(" ");
                String[] p = parts[0].split("=")[1].split(",");
                int row = Integer.parseInt(p[1]);
                int col = Integer.parseInt(p[0]);
                String[] v = parts[1].split("=")[1].split(",");
                int velRow = Integer.parseInt(v[1]);
                int velCol = Integer.parseInt(v[0]);
                robots.add(new Robot(row, col, velRow, velCol));
            }
            
            // Part 1
            int secondsPart1 = 100;
            robots = moveRobots(robots, maxWidth, maxHeight, secondsPart1);
            int sum1 = calcRobots(robots, maxWidth, maxHeight);
            System.out.println(sum1);

            // Part 2
            int sum2 = findSecondsForEasterEgg(robots, maxWidth, maxHeight)+secondsPart1;
            System.out.println(sum2);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printRobots(ArrayList<Robot> robots, int maxWidth, int maxHeight) {
        char[][] grid = new char[maxHeight][maxWidth];
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                grid[i][j] = '.';
            }
        }
        for (Robot robot : robots) {
            grid[robot.row][robot.col] = '#';
        }
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static ArrayList<Robot> moveRobots(ArrayList<Robot> robots, int maxWidth, int maxHeight, int seconds) {
        for (int i = 0; i < seconds; i++) {
            for (Robot robot : robots) {
                robot.move(maxWidth, maxHeight);
            }
        }
        return robots;
    }

    public static int calcRobots(ArrayList<Robot> robots, int maxWidth, int maxHeight) {
        int[] quadrantsCount = new int[4];
        for (Robot robot : robots) {
            // left top
            if (robot.row <= (maxHeight / 2) - 1 && robot.col <= (maxWidth / 2) - 1) {
                quadrantsCount[0]++;
            }
            // right top
            if (robot.row <= (maxHeight / 2) - 1 && robot.col >= (maxWidth / 2) + 1) {
                quadrantsCount[1]++;
            }
            // left bottom
            if (robot.row >= (maxHeight / 2) + 1 && robot.col <= (maxWidth / 2) - 1) {
                quadrantsCount[2]++;
            }
            // right bottom
            if (robot.row >= (maxHeight / 2) + 1 && robot.col >= (maxWidth / 2) + 1) {
                quadrantsCount[3]++;
            }
        }
        return quadrantsCount[0] * quadrantsCount[1] * quadrantsCount[2] * quadrantsCount[3];
    }

    public static int findSecondsForEasterEgg(ArrayList<Robot> robots, int maxWidth, int maxHeight) {
        int seconds = 0;
        while (seconds < 100000) {
            seconds++;
            robots = moveRobots(robots, maxWidth, maxHeight, 1);
            if (isEasterEggVisible(robots, maxWidth, maxHeight)) {
                break;
            }
        }
        return seconds;
    }

    public static boolean isEasterEggVisible(ArrayList<Robot> robots, int maxWidth, int maxHeight) {
        char[][] grid = new char[maxHeight][maxWidth];
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                grid[i][j] = '.';
            }
        }
        for (Robot robot : robots) {
            grid[robot.row][robot.col] = '#';
        }

        int robotCount = 0;
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                if (grid[i][j] == '#') {
                    robotCount++;
                }
            }
        }

        return robotCount == 500;
    }
}
