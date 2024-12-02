package day2;
import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        try {
            String day = "day2";
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
                String[] nums = line.split(" ");
                if (checkNums(nums, true, true)) {
                    sum++;
                }
            }
            System.out.println(sum);

            // part 2
            sum = 0;
            for (String line : inputLines) {
                String[] nums = line.split(" ");
                if (checkNums(nums, true, false)) {
                    sum++;
                }
            }
            System.out.println(sum);
             
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean checkNums(String[] nums, boolean first, boolean part1) {
        boolean valid = true;
        int[] n = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            n[i] = Integer.parseInt(nums[i]);
        }
        boolean decreasing = false;
        if (n[1] < n[0]) {
            decreasing = true;
        }
        for (int i = 1; i < n.length; i++) {
            if (decreasing && n[i] >= n[i - 1]) {
                valid = false;
            }
            if (decreasing && n[i]+3 < n[i - 1]) {
                valid = false;
            }
            if (!decreasing && n[i] <= n[i - 1]) {
                valid = false;
            }
            if (!decreasing && n[i]-3 > n[i - 1]) {
                valid = false;
            }
        }
        if (!valid && part1) {
            return false;
        }
        if (!valid && !part1) {
            if (first) {
                return checkNums2(nums);
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean checkNums2(String[] nums) {
        ArrayList<String> n = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    n.add(nums[j]);
                }
            }
            String[] n2 = new String[n.size()];
            for (int j = 0; j < n.size(); j++) {
                n2[j] = n.get(j);
            }
            n.clear();
            if (checkNums(n2, false, false)) {
                return true;
            }
        }
        return false;
    }
}