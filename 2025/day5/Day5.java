package day5;
import Template.Template;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Day5 {

    public static void main(String[] args) {
        try {
            String day = "day5";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2025 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2025");
            }

            long sum1 = 0;
            boolean rangepart = true;
            ArrayList<long[]> ranges = new ArrayList<>();

            for (String line : inputLines) {
                if (!line.isBlank() && rangepart) {
                    String[] bounds = line.split("-");
                    long start = Long.parseLong(bounds[0]);
                    long end = Long.parseLong(bounds[1]);
                    ranges.add(new long[]{start, end});
                } else if (line.isBlank()) {
                    rangepart = false;
                    continue;
                }
                if (!rangepart) {
                    long number = Long.parseLong(line);
                    for (long[] range : ranges) {
                        if (number >= range[0] && number <= range[1]) {
                            sum1++;
                            break;
                        }
                    }
                }
            }

            System.out.println("Part 1: " + sum1);

            sum1 = 0;
            ranges.clear();
            for (String line : inputLines) {
                if (!line.isBlank()) {
                    String[] bounds = line.split("-");
                    long start = Long.parseLong(bounds[0]);
                    long end = Long.parseLong(bounds[1]);
                    for (long[] existingRange : ranges) {
                        if (!(end < existingRange[0] || start > existingRange[1])) {
                            start = Math.min(start, existingRange[0]);
                            end = Math.max(end, existingRange[1]);
                            ranges.remove(existingRange);
                            break;
                        }
                    }


                    ranges.add(new long[]{start, end});
                } else break;
            }

            for (long[] range : ranges) {
                sum1 += (range[1] - range[0] + 1);
            }

            System.out.println("Part 2: " + sum1);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}