package day8;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        try {
            List<String> inp = Template.readInputFile("day8", "input.txt");
            // list of strings to list of integers

            int[][] input = Template.convertStringListToIntArray(inp);

            // part 1
            int width = input[0].length;
            int heigth = input.length;
            System.out.println("Width: " + width + ", Heigth: " + heigth);
            int sum_vis_trees = 2*width + 2*heigth - 4;
            System.out.println("Sum of outer trees: " + sum_vis_trees);

            for (int i = 1; i < width-1; i++) {
                for (int j = 1; j < heigth-1; j++) {
                    int curr = input[i][j];
                    if (checkTree(input, i, j, curr, true, true, true, true)) { 
                        sum_vis_trees += 1;
                    }
                }
            }

            System.out.println("Sum of visible trees: " + sum_vis_trees);

            // part 2
            int highest_score = 0;
            for (int i = 1; i < width-1; i++) {
                for (int j = 1; j < heigth-1; j++) {
                    int curr = input[i][j];
                    System.out.println("Current tree: " + curr);
                    int score = checkTree2(input, i, j, curr);
                    System.out.println("Score: " + score);
                    if (score > highest_score) {
                        highest_score = score;
                    }
                }
            }

            System.out.println("Highest score: " + highest_score);


        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }

    private static boolean checkTree(int[][] input, int i, int j, int curr, boolean l, boolean r, boolean u, boolean d) {
        // Abbruchbedingung, wenn am Rand angekommen
        if (i == 0 || i == input.length - 1 || j == 0 || j == input[0].length - 1) {
            return true;
        }
    
        // Links
        if (l && input[i][j - 1] < curr && checkTree(input, i, j - 1, curr, true, false, false, false)) {
            return true;
        }
    
        // Rechts
        if (r && input[i][j + 1] < curr && checkTree(input, i, j + 1, curr, false, true, false, false)) {
            return true;
        }
    
        // Oben
        if (u && input[i - 1][j] < curr && checkTree(input, i - 1, j, curr, false, false, true, false)) {
            return true;
        }
    
        // Unten
        if (d && input[i + 1][j] < curr && checkTree(input, i + 1, j, curr, false, false, false, true)) {
            return true;
        }
    
        return false;
    }

    private static int checkTree2(int[][] input, int i, int j, int curr) {
        int leftScore = 0, rightScore = 0, upScore = 0, downScore = 0;
    
        // Berechnung für links
        for (int k = j - 1; k >= 0; k--) {
            leftScore++;
            if (input[i][k] >= curr) break;
        }
    
        // Berechnung für rechts
        for (int k = j + 1; k < input[0].length; k++) {
            rightScore++;
            if (input[i][k] >= curr) break;
        }
    
        // Berechnung für oben
        for (int k = i - 1; k >= 0; k--) {
            upScore++;
            if (input[k][j] >= curr) break;
        }
    
        // Berechnung für unten
        for (int k = i + 1; k < input.length; k++) {
            downScore++;
            if (input[k][j] >= curr) break;
        }
    
        System.out.println("Left: " + leftScore + ", Right: " + rightScore + ", Up: " + upScore + ", Down: " + downScore);
        return leftScore * rightScore * upScore * downScore;
    }
    
}
