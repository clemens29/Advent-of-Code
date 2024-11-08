package day2;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        try {
            List<String> inputLines = Template.readInputFile("day2", "input.txt");

            // part 1
            int sum = 0;
            for (String line : inputLines) {
                char opp = line.charAt(0);
                char you = line.charAt(2);
                if (opp == 'A' && you == 'X' || opp == 'B' && you == 'Y' || opp == 'C' && you == 'Z') {
                    sum += 3;
                } else if (opp == 'A' && you == 'Y' || opp == 'C' && you == 'X' || opp == 'B' && you == 'Z') {
                    sum += 6;
                }
                if (you == 'X') {
                    sum += 1;
                } else if (you == 'Y') {
                    sum += 2;
                } else {
                    sum += 3;
                }
            }
            System.out.println(sum);

            // part 2
            sum = 0;
            for (String line : inputLines) {
                char opp = line.charAt(0);
                char you = line.charAt(2);
                if (you == 'Z') {
                    sum += 6;
                    if (opp == 'A') {
                        sum += 2;
                    } else if (opp == 'B') {
                        sum += 3;
                    } else {
                        sum += 1;
                    }
                }
                else if (you == 'Y') {
                    sum += 3;
                    if (opp == 'A') {
                        sum += 1;
                    } else if (opp == 'B') {
                        sum += 2;
                    } else {
                        sum += 3;
                    }
                }
                else {
                    if (opp == 'A') {
                        sum += 3;
                    } else if (opp == 'B') {
                        sum += 1;
                    } else {
                        sum += 2;
                    }
                }
            }
            System.out.println(sum);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}