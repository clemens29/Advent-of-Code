package day9;

import Template.Template;
import java.io.IOException;
import java.util.List;

public class Day9 {
    public static void main(String args[]) {
        try {
            List<String> inp = Template.readInputFile("day9", "input.txt");

            int size = 1000;
            int size_rope = 10;
            Rope rope = new Rope(size_rope, size);

            for (String line : inp) {
                String[] parts = line.split(" ");
                char direction = parts[0].charAt(0);
                int steps = Integer.parseInt(parts[1]);

                for (int step = 0; step < steps; step++) {
                    rope.move(direction);
                }
            }

            System.out.println(rope.count());

        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }
}