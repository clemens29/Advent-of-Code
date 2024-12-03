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
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }
            
            // part 1
            int sum = 0;
            String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";

            for (String line : inputLines) {
                for (int i = 0; i < line.length()-8; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (line.length() < i+8+j) break;
                        String sub = line.substring(i, i+8+j);
                        if (sub.matches(regex)) {
                            sum += multiply(sub);
                            continue;
                        }
                    }
                }
            }
            System.out.println(sum);


            // part 2
            sum = 0;
            boolean enabled = true;

            for (String line : inputLines) {
                for (int i = 0; i < line.length()-8; i++) {
                    String dont = line.substring(i,i+7);
                    String doo = line.substring(i,i+4);
                    if (dont.equals("don't()")) {
                        enabled = false;
                    }
                    if (doo.equals("do()")) {
                        enabled = true;
                    }

                    if (!enabled) continue;

                    for (int j = 0; j < 5; j++) {
                        if (line.length() < i+8+j) break;
                        String sub = line.substring(i, i+8+j);
                        if (sub.matches(regex)) {
                            sum += multiply(sub);
                            continue;
                        }
                    }
                }
            }
            System.out.println(sum);



            // part 2

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }

        
    }
    public static int multiply(String sub) {
        String num1Str = sub.substring(4, sub.indexOf(","));
        String num2Str = sub.substring(sub.indexOf(",")+1, sub.length()-1);
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);
        // System.out.println(sub);
        // System.out.println("num1: " + num1 + " num2: " + num2);
        // System.out.println();
        return num1 * num2;
    }
}