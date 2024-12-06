package day7;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day7 {

    public static void main(String[] args) {
        try {
            String day = "day7";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }
            
            // part 1
            int sum = 0;
            for (String line : inputLines) {
            }

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}