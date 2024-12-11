package day11;
import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day11 {

    public static void main(String[] args) {
        try {
            String day = "day11";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }


            String line = inputLines.get(0);
            String[] parts = line.split(" ");
            List<String> stonesInitital = new ArrayList<>();
            for (String part : parts) {
                stonesInitital.add(part);
            }

            HashMap<String, Long> stoneCountsInit = new HashMap<>();
            for (String stone : stonesInitital) {
                long currentCount = 0;
                if (stoneCountsInit.containsKey(stone)) {
                    currentCount = stoneCountsInit.get(stone);
                } 
                stoneCountsInit.put(stone, currentCount + 1);
            }

            long sum1 = 0;
            long sum2 = 0;
            HashMap<String, Long> stoneCounts1 = stoneCountsInit;
            HashMap<String, Long> stoneCounts2 = stoneCountsInit;

            int times = 75;
            for (int i = 0; i < times; i++) {
                stoneCounts2 = getNewStoneCounts(stoneCounts2);
                if (i < 25) {
                    stoneCounts1 = getNewStoneCounts(stoneCounts1);
                }
            }

            sum1 = calcStones(stoneCounts1);
            sum2 = calcStones(stoneCounts2);
            System.out.println(sum1);
            System.out.println(sum2);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static HashMap<String, Long> getNewStoneCounts(HashMap<String, Long> stonesCount) {
        HashMap<String, Long> newStonesCount = new HashMap<>();
        for (String stone : stonesCount.keySet()) {
            long count = stonesCount.get(stone);
            // rules
            if (stone.equals("0")) {
                newStonesCount = addStone(newStonesCount, "1", count);
            } else if(stone.length() % 2 == 0) {
                String part1 = stone.substring(0, stone.length() / 2);
                String part2 = stone.substring(stone.length() / 2);
                long num1 = Long.parseLong(part1);
                long num2 = Long.parseLong(part2);
                part1 = Long.toString(num1);
                part2 = Long.toString(num2);
                newStonesCount = addStone(newStonesCount, part1, count);
                newStonesCount = addStone(newStonesCount, part2, count);
            } else {
                long num = Long.parseLong(stone);
                num = num * 2024;
                stone = Long.toString(num);
                newStonesCount = addStone(newStonesCount, stone, count);
            }
        }
        return newStonesCount;
    }

    public static HashMap<String, Long> addStone(HashMap<String, Long> stoneCounts, String stone, long count) {
        if (stoneCounts.containsKey(stone)) {
            long currentCount = stoneCounts.get(stone);
            stoneCounts.put(stone, currentCount + count);
        } else {
            stoneCounts.put(stone, count);
        }
        return stoneCounts;
    }

    public static long calcStones(HashMap<String, Long> stoneCounts) {
        long sum = 0;
        for (String stone : stoneCounts.keySet()) {
            long count = stoneCounts.get(stone);
            sum += count;
        }
        return sum;
    }
}