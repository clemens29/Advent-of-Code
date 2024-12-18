package day17;
import Template.Template;

import java.io.IOException;
import java.util.List;

public class Day17 {

    public static void main(String[] args) {
        try {
            String day = "day17";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }
 
            int[] registers = new int[3];
            for (int i = 0; i < registers.length; i++) {
                String part = inputLines.get(i).split(": ")[1];
                registers[i] = Integer.parseInt(part);
            }
            String program = inputLines.get(4).split(": ")[1];
            program = program.replaceAll(",", "");

            String result = runProgram(program, registers);

            int i = 1000000000;
            while (!program.equals(result)) {
                registers[0] = i;
                registers[1] = 0;
                registers[2] = 0;
                result = runProgram(program, registers);
                
                i++;
                if (result.length() == program.length() || i % 1000000 == 0) {
                    System.out.println("i: " + i);
                    System.out.println(program);
                    System.out.println(result);
                    System.out.println();
                }
            }
            System.out.println(i);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String runProgram(String program, int[] registers) {
        String result = "";
    
        for (int i = 0; i < program.length(); i += 2) {
            char opcode = program.charAt(i);
            char operandString = program.charAt(i + 1);
            
            int operand = Character.getNumericValue(operandString);
    
            switch (opcode) {
                case '0':  // adv
                    operand = getComboValue(operand, registers);
                    registers[0] = adv(registers[0], operand);
                    break;
                case '1':  // bxl
                    registers[1] = bxl(registers[1], operand);
                    break;
                case '2':  // bst
                    operand = getComboValue(operand, registers);
                    registers[1] = bst(registers[1], operand);
                    break;
                case '3':  // jnz
                    i = jnz(registers[0], operand, i);
                    break;
                case '4':  // bxc
                    registers[1] = bxl(registers[1], registers[2]);
                    break;
                case '5':  // out
                    operand = getComboValue(operand, registers);
                    result += (operand % 8) + ",";
                    break;
                case '6':  // bdv
                    operand = getComboValue(operand, registers);
                    registers[1] = adv(registers[0], operand);
                    break;
                case '7':  // cdv
                    operand = getComboValue(operand, registers);
                    registers[2] = adv(registers[0], operand);
                    break;
                default:
                    break;
            }
        }
    
        return result.replaceAll(",", "");
    }

    private static int getComboValue(int operand, int[] registers) {
        switch (operand) {
            case 4: return registers[0];
            case 5: return registers[1];
            case 6: return registers[2];
            default: return operand;
        }
    }

    public static int adv(int a, int b) {
        return (int) (a / Math.pow(2, b));
    }

    public static int bxl(int a, int b) {
        return a ^ b;
    }

    public static int bst(int a, int b) {
        return b % 8;
    }
    
    public static int jnz(int a, int b, int i) {
        if (a != 0) {
            return (int)b - 2;
        }
        return i;        
    }
}