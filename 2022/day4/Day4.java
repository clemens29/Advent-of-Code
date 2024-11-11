package day4;
import Template.Template;

import java.util.List;
import java.io.IOException;

public class Day4 {

    public static void main(String[] args) {
        try {
            List<String> inputLines = Template.readInputFile("day4", "input.txt");

            // part 1
            int sum = 0;
            for (String line : inputLines) {
                String firstPart = line.split(",")[0];
                String secondPart = line.split(",")[1];
                int start1 = Integer.parseInt(firstPart.split("-")[0]);
                int end1 = Integer.parseInt(firstPart.split("-")[1]);
                int start2 = Integer.parseInt(secondPart.split("-")[0]);
                int end2 = Integer.parseInt(secondPart.split("-")[1]);
                if (start1 - start2 <= 0 && end1 - end2 >= 0) {
                    sum += 1;
                    continue;
                }
                if (start2 - start1 <= 0 && end2 - end1 >= 0) {
                    sum += 1;
                    continue;
                }
            }
            System.out.println(sum);


            // part 2
            sum = 0;
            for (String line : inputLines) {
                String firstPart = line.split(",")[0];
                String secondPart = line.split(",")[1];
                int start1 = Integer.parseInt(firstPart.split("-")[0]);
                int end1 = Integer.parseInt(firstPart.split("-")[1]);
                int start2 = Integer.parseInt(secondPart.split("-")[0]);
                int end2 = Integer.parseInt(secondPart.split("-")[1]);
                if (end1 >= start2 && start1 <= end2) {
                    sum += 1;
                    continue;
                }
                if (end2 >= start1 && start2 <= end1) {
                    sum += 1;
                    continue;
                }
            }
            System.out.println(sum);
            
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}