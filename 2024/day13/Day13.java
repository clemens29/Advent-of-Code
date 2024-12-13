package day13;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day13 {

    public static void main(String[] args) {
        try {
            String day = "day13";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            long sum1 = 0;
            long sum2 = 0;
            // part 1
            for (int i = 0; i < inputLines.size(); i+=4) {
                String[] buttonA = inputLines.get(i).split(": ");
                String[] buttonB = inputLines.get(i+1).split(": ");
                String[] prize = inputLines.get(i+2).split(": ");
                long Ax = Long.parseLong(buttonA[1].split(",")[0].split("\\+")[1]);
                long Ay = Long.parseLong(buttonA[1].split(",")[1].split("\\+")[1]);
                long Bx = Long.parseLong(buttonB[1].split(",")[0].split("\\+")[1]);
                long By = Long.parseLong(buttonB[1].split(",")[1].split("\\+")[1]);
                long Px = Long.parseLong(prize[1].split(",")[0].split("=")[1]);
                long Py = Long.parseLong(prize[1].split(",")[1].split("=")[1]);
                long[] buttonCount = getCheapestButton(Ax, Ay, Bx, By, Px, Py, false);
                long tokens = buttonCount[0]*3 + buttonCount[1];
                sum1 += tokens;
            }

            // part 2
            for (int i = 0; i < inputLines.size(); i+=4) {
                String[] buttonA = inputLines.get(i).split(": ");
                String[] buttonB = inputLines.get(i+1).split(": ");
                String[] prize = inputLines.get(i+2).split(": ");
                long Ax = Long.parseLong(buttonA[1].split(",")[0].split("\\+")[1]);
                long Ay = Long.parseLong(buttonA[1].split(",")[1].split("\\+")[1]);
                long Bx = Long.parseLong(buttonB[1].split(",")[0].split("\\+")[1]);
                long By = Long.parseLong(buttonB[1].split(",")[1].split("\\+")[1]);
                long Px = Long.parseLong(prize[1].split(",")[0].split("=")[1]) + 10000000000000L; //10 000 000 000 000L
                long Py = Long.parseLong(prize[1].split(",")[1].split("=")[1]) + 10000000000000L;
                long[] buttonCount = getCheapestButton(Ax, Ay, Bx, By, Px, Py, true);
                long tokens = buttonCount[0]*3 + buttonCount[1];
                sum2 += tokens;
            }

            System.out.println(sum1);
            System.out.println(sum2);



        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static long[] getCheapestButton(long Ax, long Ay, long Bx, long By, long Px, long Py, boolean part2) {
        // X: Ax*a+Bx*b = Px  * p1
        // Y: Ay*a+By*b = Py  * p2
        // Additionsverfahren

        long p1 = Ay;
        long p2 = -Ax;

        long newBx = Bx*p1;
        long newPx = Px*p1;
        long newBy = By*p2;
        long newPy = Py*p2;

        long diffB = newBx + newBy;
        long diffP = newPx + newPy;
        long b = diffP/diffB;
        long a = (Px-Bx*b)/Ax;

        if (Ax*a+Bx*b == Px && Ay*a+By*b == Py) {
            return new long[]{a, b};
        }
        return new long[]{0, 0};
    }
}