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
                System.out.println(day + " of Advent of Code 2025 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2025");
            }

            Long sumPart1 = 0L;
            Long sumPart2 = 0L;

            String line = inputLines.get(0);
            for (String part : line.split(",")) {
                String[] range = part.split("-");
                Long start = Long.parseLong(range[0].trim());
                Long end = Long.parseLong(range[1].trim());

                sumPart1 += findInvalidNumberPart1(start, end);
                sumPart2 += findInvalidNumberPart2(start, end);
            }

            System.out.println("Part 1: " + sumPart1);
            System.out.println("Part 2: " + sumPart2);
            


        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Long findInvalidNumberPart1(Long start, Long end) {
        Long sum = 0L;
        for (long i = start; i <= end; i++) {
            String iS = i + "";
            String first = iS.substring(0, iS.length() / 2);
            String second = iS.substring(iS.length() / 2);

            if (first.equals(second)) {
                sum += i;
            }
        }
        return sum;
    }

    private static Long findInvalidNumberPart2(Long start, Long end) {
        Long sum = 0L;

        for (long i = start; i <= end; i++) {
            String s = Long.toString(i);
            int len = s.length();

            boolean invalid = false;

            for (int block = 1; block <= len / 2; block++) {
                if (len % block != 0) continue;
                String first = s.substring(0, block);
                boolean allSame = true;
                for (int pos = block; pos < len; pos += block) {
                    if (!s.substring(pos, pos + block).equals(first)) {
                        allSame = false;
                        break;
                    }
                }
                if (allSame) {
                    invalid = true;
                    break;
                }
            }
            if (invalid) {
                sum += i;
            }
        }
        return sum;
    }
}