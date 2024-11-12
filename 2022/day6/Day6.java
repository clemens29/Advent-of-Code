package day6;
import Template.Template;

import java.util.List;

public class Day6 {
    public static void main(String[] args) {
        try {
            List<String> inputLines = Template.readInputFile("day6", "input.txt");

            // part 1
            String line = inputLines.get(0);
            char[] chars = line.toCharArray();
            char[] last_4_chars = new char[4];
            int i = 0;
            for (char c : chars) {
                if (i < 4) {
                    last_4_chars[i] = c;
                } else {
                    System.out.println(last_4_chars);
                    for (int j = 0; j < 3; j++) {
                        last_4_chars[j] = last_4_chars[j+1];
                    }
                    last_4_chars[3] = c;
                }
                i++;
                if (i >= 4) {
                    if (all_different(last_4_chars, last_4_chars.length)) {
                        System.out.println(last_4_chars);
                        System.out.println(i);
                        break;
                    }
                }
            } 

            // part 2
            char[] last_14_chars = new char[14];
            i = 0;
            for (char c : chars) {
                if (i < 14) {
                    last_14_chars[i] = c;
                } else {
                    System.out.println(last_14_chars);
                    for (int j = 0; j < 13; j++) {
                        last_14_chars[j] = last_14_chars[j+1];
                    }
                    last_14_chars[13] = c;
                }
                i++;
                if (i >= 14) {
                    if (all_different(last_14_chars, last_14_chars.length)) {
                        System.out.println(last_14_chars);
                        System.out.println(i);
                        break;
                    }
                }
            } 
           
        } catch (Exception e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean all_different(char[] chars, int n) {
        if (n == 1) {
            return true;
        }
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
