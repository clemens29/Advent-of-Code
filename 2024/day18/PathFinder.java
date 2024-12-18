package day18;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PathFinder {
    int height;
    int width;
    char[][] grid;
    int[] start;
    int[] end;

    final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // east, south, west, north

    public PathFinder(char[][] grid, int[] start, int[] end) {
        this.height = grid.length;
        this.width = grid[0].length;
        this.grid = grid;
        this.start = start;
        this.end = end;
    }

    class State {
        int x, y, direction, cost;
        State parent;
        public State(int x, int y, int direction, int cost, State parent) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
            this.parent = parent;
        }
    }

    public int getLowestScore() {
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost)); // queue wird nach Kosten sortiert
        int[][][] costs = new int[height][width][4]; // Kosten für jede Position und Richtung
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < 4; k++) {
                    costs[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
    
        queue.add(new State(start[0], start[1], 0, 0, null));
        costs[start[0]][start[1]][0] = 0;
    
        int minCost = Integer.MAX_VALUE;
    
        while (!queue.isEmpty()) {
            State current = queue.poll();
        
            if (current.x == end[0] && current.y == end[1]) {
                return current.cost;
            }

            // check all directions
            for (int i = 0; i < 4; i++) {
                int newDirection = (current.direction + i) % 4;
                int newX = current.x + DIRECTIONS[newDirection][0];
                int newY = current.y + DIRECTIONS[newDirection][1];
                int newCost = current.cost + 1;
                if (isValidMove(newX, newY) && newCost < costs[newX][newY][newDirection]) {
                    costs[newX][newY][newDirection] = newCost;
                    queue.add(new State(newX, newY, newDirection, newCost, current));
                }
            }

        }

        return minCost;
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < width && grid[x][y] != '#';
    }
}