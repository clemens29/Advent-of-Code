package day7;
import Template.Template;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        try {
            List<String> input = Template.readInputFile("day7", "input.txt");
            String currentDir = "root";
            FileTree tree = new FileTree(currentDir);

            // file tree from input
            for (String line : input) {
                if (line.equals("$ cd /")) {
                    continue;
                }
                String[] parts = line.split(" ");
                System.out.println(parts[0]);
                if (parts[0].equals("$")) {
                    if (parts[1].equals("cd")) {
                        System.out.println(parts[2]);
                        if (parts[2].equals("..")) {
                            // go up one directory
                            currentDir = currentDir.substring(0, currentDir.lastIndexOf("/"));
                        } else {
                            // go to directory
                            System.out.println("Current dir: " + currentDir);
                            currentDir += "/" + parts[2];
                            System.out.println("Next dir: " + currentDir);
                        }
                    } else if (parts[1] == "ls") {
                        // list files in current directory, continue
                        continue;
                    }
                } else if (parts[0].equals("dir")) {
                    // creates directory in current directory
                    tree.addFile(currentDir.split("/"), parts[1], 0, false);

                }  
                if (Character.isDigit(parts[0].charAt(0))) {
                    // creates file in current directory
                    tree.addFile(currentDir.split("/"), parts[1], Integer.parseInt(parts[0]), true);
                }
            }

            System.out.println("File tree:");
            tree.printTree();

            int full_size = tree.calcSizesOfDirs(tree.getRoot(), 0);
            System.out.println(tree.getCount());

            // part 2
            int rest = 70000000 - full_size;
            rest = 30000000 - rest;
            ArrayList<Integer> dirs_size = tree.getSizeArray();

            for (int i = 0; i < dirs_size.size() - 1; i++) {
                if (dirs_size.get(i) >= rest) {
                    System.out.println(dirs_size.get(i));
                    break;
                }
            }




        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }



        
    }
}
