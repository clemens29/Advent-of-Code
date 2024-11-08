package day3;
import Template.Template;

import java.util.List;
import java.io.IOException;

public class Day3 {

    public static void main(String[] args) {
        try {
            List<String> inputLines = Template.readInputFile("day3", "input.txt");

            // part 1
            int sum = 0;
            for (String line : inputLines) {
                // first + second half of the string
                String firstHalf = line.substring(0, line.length() / 2);
                String secondHalf = line.substring(line.length() / 2);
                for (char c : firstHalf.toCharArray()) {
                    if (secondHalf.contains(String.valueOf(c))) {
                        if ((int) c < 91) {
                            sum += (int) c - 38;
                        } else {
                            sum += (int) c - 96;
                        }
                        break;
                    }
                }
            }
            System.out.println(sum);

            // part 2
            sum = 0;
            for (int i = 0; i < inputLines.size()/3; i++) {
                String line1 = inputLines.get(i*3);
                String line2 = inputLines.get(i*3 + 1);
                String line3 = inputLines.get(i*3 + 2);
                for (char c : line1.toCharArray()) {
                    if (line2.contains(String.valueOf(c)) && line3.contains(String.valueOf(c))) {
                        if ((int) c < 91) {
                            sum += (int) c - 38;
                        } else {
                            sum += (int) c - 96;
                        }
                        break;
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