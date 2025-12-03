package day3;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day3 {

    public static void main(String[] args) {
        try {
            String day = "day3";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2025 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2025");
            }

            Long sum = 0L;
            int[] parts = {2, 12};

            for (int count : parts) {
                sum = 0L;
                for (String line : inputLines) {
                    char[] result = new char[count];
                    
                    for (int times = 0; times < count; times++) {
                        result[times] = getBiggestNumber(line.substring(0, line.length()-(count-times-1)));
                        line = line.substring(line.indexOf(result[times]) + 1);
                    }
                    sum += Long.parseLong(new String(result));
                }
                System.out.println("Part " + (count == 2 ? "1" : "2") + ": " + sum);
            }

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static char getBiggestNumber(String line) {
        char[] chars = line.toCharArray();
        int maxIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < chars.length; i++) {
            int value = Character.getNumericValue(chars[i]);
            if (value > maxValue) {
                maxValue = value;
                maxIndex = i;
            }
        }
        return chars[maxIndex];
    }
}