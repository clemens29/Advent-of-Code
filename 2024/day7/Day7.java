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

            long sum1 = 0;
            long sum2 = 0;
            
            // part 1
            for (String line : inputLines) {
                String[] parts = line.split(": ");
                long result = Long.parseLong(parts[0]);
                String[] operands = parts[1].split(" ");
                String[] byteCodes = new String[(int)Math.pow(2, operands.length-1)];
                for (int i = 0; i < Math.pow(2, operands.length-1); i++) {
                    // bit representation of the byte code
                    String byteCode = Integer.toBinaryString(i);
                    while (byteCode.length() < operands.length-1) {
                        byteCode = "0" + byteCode;
                    }
                    byteCodes[i] = byteCode;
                }
                if (calcSum(operands, result, byteCodes)) {
                    sum1 += result;
                }
            }
            System.out.println(sum1);

            // part 2
            for (String line : inputLines) {
                String[] parts = line.split(": ");
                long result = Long.parseLong(parts[0]);
                String[] operands = parts[1].split(" ");
                String[] byteCodes = new String[(int)Math.pow(3, operands.length-1)];
                for (int i = 0; i < Math.pow(3, operands.length-1); i++) {
                    // all possible byte codes with 3 values: 0, 1, 2
                    String byteCode = Integer.toString(i, 3);
                    while (byteCode.length() < operands.length-1) {
                        byteCode = "0" + byteCode;
                    }
                    byteCodes[i] = byteCode;
                }
                if (calcSum(operands, result, byteCodes)) {
                    sum2 += result;
                }
            }
            System.out.println(sum2);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean calcSum(String[] operands, long result, String[] byteCode) {
        for (String bc : byteCode) {
            long sum = Long.parseLong(operands[0]);
            for (int i = 0; i < operands.length - 1; i++) {
                long operand = Long.parseLong(operands[i + 1]);
                if (bc.charAt(i) == '0') {
                    sum += operand;
                } else if (bc.charAt(i) == '1') {
                    sum *= operand;
                } else {
                    sum = Long.parseLong(String.valueOf(sum) + String.valueOf(operand));
                }
            }
            if (sum == result) {
                return true;
            }
        }
        return false;
    }
}