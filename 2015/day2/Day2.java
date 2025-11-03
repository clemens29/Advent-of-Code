package day2;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        try {
            String day = "day2";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            int resultPart1 = 0;
            int resultPart2 = 0;
            for (String line : inputLines) {
                int l = Integer.parseInt(line.split("x")[0]);
                int w = Integer.parseInt(line.split("x")[1]);
                int h = Integer.parseInt(line.split("x")[2]);

                int lw = l * w;
                int wh = w * h;
                int hl = h * l;

                resultPart1 += 2*lw + 2*wh + 2*hl + Math.min(lw, Math.min(wh, hl));

                int[] sides = {l, w, h};
                java.util.Arrays.sort(sides);
                resultPart2 += 2 * sides[0] + 2 * sides[1] + (l * w * h);
            }

            System.out.println("Part 1: " + resultPart1);
            System.out.println("Part 2: " + resultPart2);

            
        


        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}