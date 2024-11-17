package day10;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day10 {
    public static void main(String[] args) {

        try {
            List<String> input = Template.readInputFile("day10", "input.txt");
            int cycle = 0;
            int register = 1;
            int strength = 0;

            // part 1
            for (String line : input) {
                String[] parts = line.split(" ");
                String command = parts[0];

                if (command.equals("noop")) {
                    cycle++;
                    strength += checkCycle(cycle, register);
                } else if (command.equals("addx")) {
                    int value = Integer.parseInt(parts[1]);
                    for (int i = 0; i < 2; i++) {
                        cycle++;
                        strength += checkCycle(cycle, register);
                        if (i == 1) {
                            register += value;
                        }
                    }
                }
            }

            System.out.println("Strength: " + strength);

            // part 2
            register = 1;
            strength = 0;
            cycle = 0;
            int[] sprite = updateSprite(register);
            String crt = updateCrt(sprite, "", cycle);
            for (String line : input) {
                String[] parts = line.split(" ");
                String command = parts[0];

                if (command.equals("noop")) {
                    cycle++;
                    sprite = updateSprite(register);
                    crt = updateCrt(sprite, crt, cycle);
                } else if (command.equals("addx")) {
                    int value = Integer.parseInt(parts[1]);
                    for (int i = 0; i < 2; i++) {
                        cycle++;
                        sprite = updateSprite(register);
                        crt = updateCrt(sprite, crt, cycle);
                        if (i == 1) {
                            register += value;
                        }
                    }

                }
            }
            System.out.println(crt);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int checkCycle(int cycle, int register) {
        if (cycle == 20 || cycle % 40 == 20) {
            System.out.println("Cycle: " + cycle + " Register: " + register);
            return cycle * register;
        }
        return 0;
    }

    private static int[] updateSprite(int register) {
        int[] sprite = {register-1, register, register+1};
        return sprite;
    }

    private static String updateCrt(int[] sprite, String crt, int cycle) {
        int pixel = cycle % 40 - 1;
        if (cycle % 40 == 0) {
            crt += "\n";
        } else {
            for (int i = 0; i < sprite.length; i++) {
                if (sprite[i] == pixel) {
                    crt += "#";
                    return crt;
                }
            }
            crt += ".";
        }
        return crt;
    }
    
}
