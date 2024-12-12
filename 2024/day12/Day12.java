package day12;
import Template.Template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day12 {

    public static void main(String[] args) {
        try {
            String day = "day12";
            List<String> inputLines = Template.readInputFile(day, "input.txt");

            if (args.length > 0) {
                inputLines = Template.readInputFile(day, "test.txt");
                System.out.println(day + " of Advent of Code 2024 - test input");
            } else {
                System.out.println(day + " of Advent of Code 2024");
            }

            int sum1 = 0;
            int sum2 = 0;
            int width = inputLines.get(0).length();
            int height = inputLines.size();
            HashMap<Character, List<List<Integer[]>>> regions = new HashMap<>();

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    char plant = inputLines.get(i).charAt(j);
                    Integer[] pos = {i, j};
            
                    regions.putIfAbsent(plant, new ArrayList<>());
                    List<List<Integer[]>> regionList = regions.get(plant);
            
                    List<List<Integer[]>> mergeRegions = new ArrayList<>();
                    for (List<Integer[]> region : regionList) {
                        for (Integer[] p : region) {
                            if (checkAdjacent(p, i, j)) {
                                mergeRegions.add(region);
                                break;
                            }
                        }
                    }
            
                    if (mergeRegions.isEmpty()) {
                        List<Integer[]> newRegion = new ArrayList<>();
                        newRegion.add(pos);
                        regionList.add(newRegion);
                    } else {
                        List<Integer[]> mergedRegion = new ArrayList<>();
                        for (List<Integer[]> region : mergeRegions) {
                            mergedRegion.addAll(region);
                            regionList.remove(region);
                        }
                        mergedRegion.add(pos);
                        regionList.add(mergedRegion);
                    }
                }
            }


            List<Plant> plants = new ArrayList<>();
            for (Character plant : regions.keySet()) {
                plants.add(new Plant(plant, regions.get(plant)));
            }

            printPlants(regions);

            for (Plant plant : plants) {
                sum1 += plant.getSum(false);
                sum2 += plant.getSum(true);
            }

            System.out.println(sum1);
            System.out.println(sum2);


        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printPlants(HashMap<Character, List<List<Integer[]>>> regions) {
        for (Character plant : regions.keySet()) {
            System.out.println(plant + ": ");
            for (List<Integer[]> region : regions.get(plant)) {
                for (Integer[] pos : region) {
                    System.out.print("(" + pos[0] + ", " + pos[1] + ") ");
                }
                System.out.println();
            }
        }
    }

    public static HashMap<Character, List<Integer[]>> addPlant(HashMap<Character, List<Integer[]>> plants, List<String> inputLines, int row, int col) {
        char plant = inputLines.get(row).charAt(col);
        if (plants.containsKey(plant)) {
            List<Integer[]> plantList = plants.get(plant);
            Integer[] pos = {row, col};
            plantList.add(pos);
            plants.put(plant, plantList);
        } else {
            List<Integer[]> plantList = new ArrayList<>();
            Integer[] pos = {row, col};
            plantList.add(pos);
            plants.put(plant, plantList);
        }
        return plants;
    }

    public static boolean checkAdjacent(Integer[] pos, int i, int j) {
        int x = pos[0];
        int y = pos[1];
        if ((x == i && y == j - 1) || (x == i - 1 && y == j) || 
            (x == i && y == j + 1) || (x == i + 1 && y == j)) {
            return true;
            }
        return false;
    }
}