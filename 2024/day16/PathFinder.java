package day16;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;


public class PathFinder {
    int height;
    int width;
    char[][] grid;
    int[] start;
    int[] end;
    List<List<int[]>> paths;

    final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // east, south, west, north

    public PathFinder(char[][] grid, int[] start, int[] end) {
        this.height = grid.length;
        this.width = grid[0].length;
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.paths = new ArrayList<>();
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
        
            // Ziel erreicht
            if (current.x == end[0] && current.y == end[1]) {
                if (current.cost < minCost) {
                    minCost = current.cost; 
                    paths.clear();          // Lösche alle bisherigen Pfade
                }
                if (current.cost == minCost) {
                    paths.add(getPath(current)); //  aktuellen Pfad hinzu
                }
                continue;
            }
        
            if (current.cost > costs[current.x][current.y][current.direction]) {
                continue;
            }
        
            // Bewegung nach vorne
            int newX = current.x + DIRECTIONS[current.direction][0];
            int newY = current.y + DIRECTIONS[current.direction][1];
            if (isValidMove(newX, newY) && current.cost + 1 <= costs[newX][newY][current.direction]) {
                costs[newX][newY][current.direction] = current.cost + 1;
                queue.add(new State(newX, newY, current.direction, current.cost + 1, current));
            }
        
            // Links drehen
            int leftDir = (current.direction + 3) % 4;
            if (current.cost <= costs[current.x][current.y][leftDir]) {
                costs[current.x][current.y][leftDir] = current.cost;
                queue.add(new State(current.x, current.y, leftDir, current.cost, current));
            }
        
            // Rechts drehen
            int rightDir = (current.direction + 1) % 4;
            if (current.cost <= costs[current.x][current.y][rightDir]) {
                costs[current.x][current.y][rightDir] = current.cost;
                queue.add(new State(current.x, current.y, rightDir, current.cost, current));
            }
    
        }
        return minCost;
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < width && grid[x][y] != '#';
    }

    private List<int[]> getPath(State state) {
        List<int[]> path = new ArrayList<>();
        while (state != null) {
            path.add(new int[]{state.x, state.y});
            state = state.parent;
        }
        return path;
    }

    public int getTiles (char[][] grid) {
        int tiles = 0;
    
        for (List<int[]> path : paths) {
            for (int[] p : path) {
                grid[p[0]][p[1]] = 'O';
            }
        }
    
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'O') {
                    tiles++;
                }
            }
        }
        return tiles;
    }

}