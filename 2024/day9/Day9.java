package day9;

import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9 {

    public static void main(String[] args) {
        try {
            String day = "day9";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            long sum1 = 0;
            long sum2 = 0;
            String line = inputLines.get(0);
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                numbers = getNumbers(numbers, line, i);
            }

            // Part 1
            ArrayList<Integer> numbers1 = new ArrayList<>(numbers);

            for (int i = 0; i < numbers1.size(); i++) {
                if (numbers1.get(i) == -1) {
                    numbers1 = moveFileBlock1(numbers1, i);
                }
            }

            sum1 = calcChecksum(numbers1);
            System.out.println(sum1);

            // Part 2
            ArrayList<Integer> numbers2 = new ArrayList<>(numbers);
            int maxFileId = 0;
            for (int num : numbers2) {
                if (num != -1) {
                    maxFileId = Math.max(maxFileId, num);
                }
            }

            for (int fileId = maxFileId; fileId >= 0; fileId--) {
                int startIndex = findFileStart(numbers2, fileId);
                if (startIndex == -1) {
                    continue;
                }

                int fileSize = getFileSize(numbers2, startIndex);
                int targetIndex = findLeftmostFreeSpace(numbers2, fileSize);
                if (targetIndex != -1 && targetIndex < startIndex) {
                    numbers2 = moveFile(numbers2, startIndex, targetIndex, fileSize);
                }
            }

            sum2 = calcChecksum(numbers2);
            System.out.println(sum2);


        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == -1) {
                System.out.print(". ");
            } else {
                System.out.print(numbers.get(i) + " ");
            }
        }
        System.out.println();
    }

    public static ArrayList<Integer> getNumbers(ArrayList<Integer> numbers, String line, int i) {
        int space = Integer.parseInt(line.substring(i, i + 1));
        for (int j = 0; j < space; j++) {
            if (i % 2 == 0) {
                // file
                numbers.add(i / 2);
            } else {
                // free space
                numbers.add(-1);
            }
        }
        return numbers;
    }

    public static ArrayList<Integer> moveFileBlock1(ArrayList<Integer> numbers, int i) {
        for (int j = numbers.size() - 1; j > i; j--) {
            if (numbers.get(j) != -1) {
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(j));
                numbers.set(j, temp);
                break;
            }
        }
        return numbers;
    }

    public static ArrayList<Integer> moveFile(ArrayList<Integer> numbers, int startIndex, int targetIndex, int fileSize) {
        ArrayList<Integer> file = new ArrayList<>();
        for (int i = 0; i < fileSize; i++) {
            file.add(numbers.get(startIndex + i));
            numbers.set(startIndex + i, -1);
            numbers.set(targetIndex + i, file.get(i));
        }
        return numbers;
    }

    public static int findFileStart(ArrayList<Integer> numbers, int fileId) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == fileId) {
                return i;
            }
        }
        return -1;
    }

    public static int findLeftmostFreeSpace(ArrayList<Integer> numbers, int fileSize) {
        for (int i = 0; i < numbers.size(); i++) {
            if (getFreeSpace(numbers, i) >= fileSize) {
                return i;
            }
        }
        return -1;
    }

    public static int getFreeSpace(ArrayList<Integer> numbers, int i) {
        int freeSpace = 0;
        for (int j = i; j < numbers.size(); j++) {
            if (numbers.get(j) == -1) {
                freeSpace++;
            } else {
                break;
            }
        }
        return freeSpace;
    }

    public static int getFileSize(ArrayList<Integer> numbers, int i) {
        int fileSize = 0;
        int fileId = numbers.get(i);
        for (int j = i; j < numbers.size(); j++) {
            if (numbers.get(j) == fileId) {
                fileSize++;
            } else {
                return fileSize;
            }
        }
        return fileSize;
    }

    public static long calcChecksum(ArrayList<Integer> numbers) {
        long sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == -1) {
                continue;
            }
            sum += numbers.get(i) * i;
        }
        return sum;
    }
}
