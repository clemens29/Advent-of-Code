package day15;
import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day15 {

    public static void main(String[] args) {
        try {
            String day = "day15";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            int width = inputLines.get(0).length();
            int height = 0;
            for (int i = 1; i < inputLines.size(); i++) {
                if (inputLines.get(i).length() == 0) {
                    height = i;
                    break;
                }
            }

            char[][] grid = new char[height][width];
            for (int i = 0; i < height; i++) {
                String line = inputLines.get(i);
                grid[i] = line.toCharArray();
            }

            String moves = "";
            for (int i = height+1; i < inputLines.size(); i++) {
                String line = inputLines.get(i);
                moves += line;
            }


            // part 1
            Integer[] robotPos = getRobotPos(grid);
            moveRobot(grid, moves, robotPos[0], robotPos[1], false);

            int sum1 = calcGPS(grid, 'O');
            System.out.println(sum1);

            // part 2
            List<List<Character>> grid2 = getGridPart2(inputLines, width, height);
            width = grid2.get(0).size();
            height = grid2.size();
            char[][] grid2_arr = new char[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    grid2_arr[i][j] = grid2.get(i).get(j);
                }
            }
            
            robotPos = getRobotPos(grid2_arr);
            moveRobot(grid2_arr, moves, robotPos[0], robotPos[1], true);
            int sum2 = calcGPS(grid2_arr, '[');
            System.out.println(sum2);


        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Integer[] getRobotPos(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '@') {
                    return new Integer[]{i, j};
                }
            }
        }
        return new Integer[]{-1, -1};
    }

    public static Integer[] moveRobot(char[][] grid, String moves, int i, int j, boolean part2) {
        for (int k = 0; k < moves.length(); k++) {
            char move = moves.charAt(k);
            if (!part2) {
                if (move == '^') {
                    grid = moveCol(grid, i, j, -1);
                } else if (move == 'v') {
                    grid = moveCol(grid, i, j, 1);
                } else if (move == '>') {
                    grid = moveRow(grid, i, j, 1);
                } else if (move == '<') {
                    grid = moveRow(grid, i, j, -1);
                }
            } else {
                if (move == '^') {
                    grid = moveCol2(grid, i, j, -1, grid.length, grid[0].length);
                } else if (move == 'v') {
                    grid = moveCol2(grid, i, j, 1, grid.length, grid[0].length);
                } else if (move == '>') {
                    grid = moveRow2(grid, i, j, 1);
                } else if (move == '<') {
                    grid = moveRow2(grid, i, j, -1);
                }
            }
            Integer[] robotPos = getRobotPos(grid);
            i = robotPos[0];
            j = robotPos[1];
        }
        return new Integer[]{i, j};
    }

    public static char[][] moveCol(char[][] grid, int i, int j, int dir) {
        int pos = i+dir;
        char next_c = grid[pos][j];
        int o_count = 0;
        boolean can_move = false;
        while (pos >= 0 && pos < grid.length && next_c != '#') {
            if (next_c == '.') {
                can_move = true;
                break;
            }
            o_count++;
            pos += dir;
            if (pos >= 0 && pos < grid.length) {
                next_c = grid[pos][j];
            } else {
                break;
            }
        }

        pos = i+dir;
        if (can_move) {
            grid[i][j] = '.';
            grid[pos][j] = '@';
            for (int k = 1; k < o_count+1; k++) {
                grid[pos+k*dir][j] = 'O';
            }
        }
        return grid;
    }

    public static char[][] moveRow(char[][] grid, int i, int j, int dir) {
        int pos = j+dir;
        char next_c = grid[i][pos];
        int o_count = 0;
        boolean can_move = false;
        while (pos >= 0 && pos < grid.length && next_c != '#') {
            if (next_c == '.') {
                can_move = true;
                break;
            }
            o_count++;
            pos += dir;
            if (pos >= 0 && pos < grid.length) {
                next_c = grid[i][pos];
            } else {
                break;
            }
        }

        pos = j+dir;
        if (can_move) {
            grid[i][j] = '.';
            grid[i][pos] = '@';
            for (int k = 1; k < o_count+1; k++) {
                grid[i][pos+k*dir] = 'O';
            }
        }

        return grid;
    }

    public static int calcGPS(char[][] grid, char c) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == c) {
                    sum += i*100 + j;
                }
            }
            
        }
        return sum;
    }

    public static List<List<Character>> getGridPart2(List<String> inputLines, int width, int height) {
        List<List<Character>> grid = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            String line = inputLines.get(i);
            List<Character> new_line = new ArrayList<>();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c == '#') {
                    new_line.add('#');
                    new_line.add('#');
                } else if (c == '.') {
                    new_line.add('.');
                    new_line.add('.');
                } else if (c == 'O') {
                    new_line.add('[');
                    new_line.add(']');
                } else if (c == '@') {
                    new_line.add('@');
                    new_line.add('.');
                }
            }
            grid.add(new_line);
        }
        return grid;
    }

    public static char[][] moveCol2(char[][] grid, int i, int j, int dir, int height, int width) {
        char[][] try_move = new char[height][width];
        for (int k = 0; k < height; k++) {
            for (int l = 0; l < width; l++) {
                if (k-dir == i && l == j) {
                    try_move[k][l] = '@';
                } else {
                    try_move[k][l] = '.';
                }
            }
        }
        int pos = i+dir;
        int next_row = pos;
        int next_col = j;
        char next_c = grid[next_row][next_col];
        if (next_c == '.') {
            grid[i][j] = '.';
            grid[pos][j] = '@';
            return grid;
        }
        if (next_c == '#') {
            return grid;
        }

        // get the boxes affected by the move recursively
        try_move = getBoxesToMove(grid, try_move, next_row, next_col, dir, height, width);

        boolean can_move = canMoveBoxes(grid, try_move, height, width);
        if (can_move) {
            // overwrite the grid with the new positions
            if (dir == -1) {
                for (int k = 0; k < height; k++) {
                    for (int l = 0; l < width; l++) {
                        if (try_move[k][l] == '[' || try_move[k][l] == ']' || try_move[k][l] == '@') {
                            grid[k][l] = try_move[k][l];
                            grid[k-dir][l] = '.';
                        }
                    }
                }
            } else {
                for (int k = height-1; k >= 0; k--) {
                    for (int l = 0; l < width; l++) {
                        if (try_move[k][l] == '[' || try_move[k][l] == ']' || try_move[k][l] == '@') {
                            grid[k][l] = try_move[k][l];
                            grid[k-dir][l] = '.';
                        }
                    }
                }
            }
        }
        


        return grid;
    }
    

    public static char[][] moveRow2(char[][] grid, int i, int j, int dir) {
        int pos = j+dir;
        char next_c = grid[i][pos];
        int o_count = 0;
        boolean can_move = false;
        while (pos >= 0 && pos < grid[0].length && next_c != '#') {
            if (next_c == '.') {
                can_move = true;
                break;
            }
            o_count++;
            pos += dir;
            if (pos >= 0 && pos < grid[0].length) {
                next_c = grid[i][pos];
            } else {
                break;
            }
        }

        pos = j+dir;
        if (can_move) {
            grid[i][j] = '.';
            grid[i][pos] = '@';
            for (int k = 1; k < o_count+1; k++) {
                if (k%2==0) {
                    grid[i][pos+k*dir] = '[';
                    if (dir == 1) {
                        grid[i][pos+k*dir] = ']';
                    }
                } else {
                    grid[i][pos+k*dir] = ']';
                    if (dir == 1) {
                        grid[i][pos+k*dir] = '[';
                    }
                }
            }
        }

        return grid;
    }

    // get the boxes affected by the move recursively
    public static char[][] getBoxesToMove(char[][] grid, char[][] try_move, int i, int j, int dir, int height, int width) {
        
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return try_move;
        }
        char next_c = grid[i][j];
        if (next_c == ']') {
            try_move[i+dir][j] = ']';
            try_move[i+dir][j-1] = '[';
            try_move = getBoxesToMove(grid, try_move, i+dir, j, dir, height, width);
            try_move = getBoxesToMove(grid, try_move, i+dir, j-1, dir, height, width);
        } else if (next_c == '[') {
            try_move[i+dir][j] = '[';
            try_move[i+dir][j+1] = ']';
            try_move = getBoxesToMove(grid, try_move, i+dir, j, dir, height, width);
            try_move = getBoxesToMove(grid, try_move, i+dir, j+1, dir, height, width);
        }
        return try_move;
    }

    // compare grids to see if the boxes can move
    public static boolean canMoveBoxes(char[][] grid, char[][] try_move, int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '#' && try_move[i][j] == '[' || grid[i][j] == '#' && try_move[i][j] == ']') {
                    return false;
                }
            }
        }
        return true;
    }
}