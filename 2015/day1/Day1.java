package day1;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {
        try {
            String day = "day1";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            inputLines.forEach(System.out::println);

            int result = 0;
            int position = 1;
            for (char element : inputLines.get(0).toCharArray()) {
                if (element == '(') {
                    result++;
                } else if (element == ')') {
                    result--;
                }
                if (result == -1) {
                    System.out.println("Reached basement at position: " + position);
                    break;
                }
                position++;
            }
            System.out.println("Final result: " + result);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}