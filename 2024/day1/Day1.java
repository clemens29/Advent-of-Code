package day1;
import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {
        try {
            List<String> inputLines = Template.readInputFile("day1", "input.txt");
            
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
           for (String line : inputLines) {
                String[] nums = line.split("   ");
                int num1 = Integer.parseInt(nums[0]);
                int num2 = Integer.parseInt(nums[1]);
                list1.add(num1);
                list2.add(num2);
            }
            list1.sort(null);
            list2.sort(null);
            int sum = 0;
            for (int i = 0; i < list1.size(); i++) {
                int diff = Math.abs(list1.get(i) - list2.get(i));
                sum += diff;
            }
            System.out.println(sum);

            // part 2
            sum = 0;
            for (int i = 0; i < list1.size(); i++) {
                int first = list1.get(i);
                int sameAsFirst = 0;
                for (int j = 0; j < list2.size(); j++) {
                    int second = list2.get(j);
                    if (first == second) {
                        sameAsFirst++;
                    }
                }
                sum += first * sameAsFirst;
            }
            System.out.println(sum);

            

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}