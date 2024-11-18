package day11;

import Template.Template;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    public static void main(String[] args) {
        try {
            List<String> input = Template.readInputFile("day11", "input.txt");
            List<Monkey> monkeys = new ArrayList<>();

            int num_monkeys = (input.size() + 1) / 7;

            // Initialisierung der Affen und deren Attribute
            for (int i = 0; i < num_monkeys; i++) {
                ArrayList<Integer> starting_items = new ArrayList<>();
                String[] items = input.get(7 * i + 1).split(": ")[1].split(", ");
                for (String item : items) {
                    starting_items.add(Integer.parseInt(item));
                }

                String operation = input.get(7 * i + 2).split("= old ")[1];
                int test_op = Integer.parseInt(input.get(7 * i + 3).split(" ")[5]);
                int if_true = Integer.parseInt(input.get(7 * i + 4).split(" ")[9]);
                int if_false = Integer.parseInt(input.get(7 * i + 5).split(" ")[9]);

                monkeys.add(new Monkey(starting_items, operation, test_op, if_true, if_false, i));
            }

            // Simulation der Runden
            int rounds = 20;
            for (int round = 0; round < rounds; round++) {
                for (Monkey monkey : monkeys) {
                    // Liste der Items, die geworfen werden sollen
                    List<int[]> itemsToThrow = new ArrayList<>();

                    for (int item : monkey.starting_items) {
                        int[] result = monkey.operate(item);
                        itemsToThrow.add(result);
                    }

                    
                    monkey.starting_items.clear();

                    for (int[] result : itemsToThrow) {
                        int throwTo = result[0];
                        int value = result[1];
                        monkeys.get(throwTo).starting_items.add(value);
                    }

                }
            }


            for (Monkey monkey : monkeys) {
                System.out.println("Monkey " + monkey.name + " inspected items " + monkey.inspected_items + " times.");
            }

            List<Integer> twoHighest = new ArrayList<>();
            twoHighest.add(0);
            twoHighest.add(0);
            for (Monkey m : monkeys) {
                if (m.inspected_items > twoHighest.get(0)) {
                    twoHighest.remove(0);
                    twoHighest.add(m.inspected_items);
                    twoHighest.sort(null);
                }
            }

            System.out.println(twoHighest.get(0));
            System.out.println(twoHighest.get(1));
            System.out.println(twoHighest.get(0) * twoHighest.get(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}