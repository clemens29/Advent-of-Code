package day11;

import java.util.ArrayList;

public class Monkey {
    ArrayList<Integer> starting_items;
    String operation;
    int test_op;
    int if_true;
    int if_false;
    int inspected_items;
    int name;

    public Monkey(ArrayList<Integer> starting_items, String operation, int test_op, int if_true, int if_false, int name) {
        this.starting_items = starting_items;
        this.operation = operation;
        this.test_op = test_op;
        this.if_true = if_true;
        this.if_false = if_false;
        this.inspected_items = 0;
        this.name = name;
    }

    public String toString() {
        
        String s = "\nMonkey: " + name + "\nStarting items: ";
        for (int i = 0; i < starting_items.size(); i++) {
            s += starting_items.get(i) + " ";
        }
        return s;
    }

    public int[] operate(int item) {
            inspected_items++;
            String[] ops = operation.split(" ");
            if (ops[0].equals("*")) {
                if (ops[1].equals("old")) {
                    item *= item;
                } else {
                    item *= Integer.parseInt(ops[1]);
                }
            } else if (ops[0].equals("+")) {
                if (ops[1].equals("old")) {
                    item += item;
                } else {
                    item += Integer.parseInt(ops[1]);
                }
            }
            item /= 3;
            if (item % test_op == 0) {
                return new int[]{if_true, item};
            } else {
                return new int[]{if_false, item};
            }
    }
}