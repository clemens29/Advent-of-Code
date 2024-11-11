package day5;
import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.sound.midi.SysexMessage;


public class Day5 {
    public static void main(String[] args) {
        try {
            List<String> inputLines = Template.readInputFile("day5", "input.txt");

            // part 1
            int stacks_num = 9;
            List<Stack<Character>> stacks = new ArrayList<>();

            for (int i = 0; i < stacks_num; i++) {
                stacks.add(new Stack<>());
                for (int j = stacks_num-2; j >= 0 ; j--) {
                    char c = inputLines.get(j).charAt(i*4+1);
                    if (c == ' ') {
                        break;
                    } else {
                        stacks.get(i).push(c);
                    }
                }
            }

            // print stacks
            

            for (int i = stacks_num+1; i < inputLines.size(); i++) {
                String line = inputLines.get(i);
                String[] parts = line.split(" ");
                int count = Integer.parseInt(parts[1]);
                int from = Integer.parseInt(parts[3]) - 1;
                int to = Integer.parseInt(parts[5]) - 1;
                //System.out.println("count: " + count + " from: " + from + " to: " + to);
                for (int j = 0; j < count; j++) {
                    if (stacks.get(from).isEmpty()) {
                        continue;
                    }
                    char c = stacks.get(from).pop();
                    stacks.get(to).push(c);
                }
            }

            // print stacks
            for (int i = 0; i < stacks_num; i++) {
                System.out.println(stacks.get(i));
            }

            String result = "";
            for (int i = 0; i < stacks_num; i++) {
                result += stacks.get(i).pop();
            }
            System.out.println(result);

            // part 2
            stacks = new ArrayList<>();

            for (int i = 0; i < stacks_num; i++) {
                stacks.add(new Stack<>());
                for (int j = stacks_num-2; j >= 0 ; j--) {
                    char c = inputLines.get(j).charAt(i*4+1);
                    if (c == ' ') {
                        break;
                    } else {
                        stacks.get(i).push(c);
                    }
                }
            }

            for (int i = stacks_num+1; i < inputLines.size(); i++) {
                String line = inputLines.get(i);
                String[] parts = line.split(" ");
                int count = Integer.parseInt(parts[1]);
                int from = Integer.parseInt(parts[3]) - 1;
                int to = Integer.parseInt(parts[5]) - 1;
                //System.out.println("count: " + count + " from: " + from + " to: " + to);
                List<Character> c = popMultiple(stacks.get(from), count);
                for (int j = c.size()-1; j != -1; j--) {
                    stacks.get(to).push(c.get(j));
                }
            }

            // print stacks
            for (int i = 0; i < stacks_num; i++) {
                System.out.println(stacks.get(i));
            }

            result = "";
            for (int i = 0; i < stacks_num; i++) {
                result += stacks.get(i).pop();
            }
            System.out.println(result);





           
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static <T> List<T> popMultiple(Stack<T> stack, int count) {
        List<T> poppedElements = new ArrayList<>();
        for (int i = 0; i < count && !stack.isEmpty(); i++) {
            poppedElements.add(stack.pop());
        }
        return poppedElements;
    }
}
