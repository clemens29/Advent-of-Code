package day5;
import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day5 {

    public static void main(String[] args) {
        try {
            String day = "day5";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }
            
            // part 1
            int sum1 = 0;
            int sum2 = 0;
            boolean isBreak = false;
            HashMap<Integer, List<Integer>> rules = new HashMap<>();
            for (int i = 0; i < inputLines.size(); i++) {
                if (inputLines.get(i).length() == 0) {
                    isBreak = true;
                    continue;
                }
                if (!isBreak) {
                    String r = inputLines.get(i);
                    String[] rule = new String[2];
                    rule[0] = r.substring(0, 2);
                    rule[1] = r.substring(3, 5);
                    
                    System.out.println(rule[0] + " " + rule[1]);
                    int key = Integer.parseInt(rule[0]);
                    if (rules.containsKey(key)) {
                        List<Integer> values = rules.get(key);
                        values.add(Integer.parseInt(rule[1]));
                        rules.put(key, values);
                    } else {
                        rules.put(key, new ArrayList<>(List.of(Integer.parseInt(rule[1]))));
                    }
                } else {
                    String[] update = inputLines.get(i).split(",");
                    sum1 += checkUpdate(rules, update);
                    sum2 += checkUpdate2(rules, update);
                }
            }
            System.out.println(sum1);
            System.out.println(sum2);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static int checkUpdate(HashMap<Integer, List<Integer>> rules, String[] update) {
        for (int j = 0; j < update.length; j++) {
            int num = Integer.parseInt(update[j]);
            if (rules.containsKey(num)) {
                List<Integer> values = rules.get(num);
                for (int value : values) {
                    for (int k = 0; k < j; k++) {
                        if (Integer.parseInt(update[k]) == value) {
                            return 0;
                        }
                    }
                }
            }
        }
        return Integer.parseInt(update[update.length / 2]);
    }

    public static int checkUpdate2(HashMap<Integer, List<Integer>> rules, String[] update) {
        // print update
        for (int i = 0; i < update.length; i++) {
            System.out.print(update[i] + " ");
            
        }
        System.out.println();
        boolean isWrong = false;
        List<Integer> newList = new ArrayList<>();
        if (checkUpdate(rules, update) == 0) {
            isWrong = true;
        }
        if (isWrong) {
            for (int j = 0; j < update.length; j++) {
                newList.add(Integer.parseInt(update[j]));
            }
            while (checkUpdate(rules, update) == 0) {
                for (int j = 0; j < update.length; j++) {
                    int num = Integer.parseInt(update[j]);
                    if (rules.containsKey(num)) {
                        List<Integer> values = rules.get(num);
                        for (int value : values) {
                            for (int k = 0; k < j; k++) {
                                if (newList.get(k) == value) {
                                    int temp = newList.get(j);
                                    newList.set(j, newList.get(k));
                                    newList.set(k, temp);
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < update.length; i++) {
                    update[i] = Integer.toString(newList.get(i));
                }
            }
            int sum = newList.get(newList.size() / 2);
            newList.clear();
            return sum;
        }
        return 0;
    }
}