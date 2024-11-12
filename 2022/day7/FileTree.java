package day7;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileTree {
    FileNode root;
    int count = 0;
    ArrayList<Integer> dirs_size = new ArrayList<>();

    public FileTree(String name) {
        root = new FileNode(name);
    }

    public void addFile(String[] path, String name, int size, boolean isFile) {
        FileNode current = root;
        for (String dir : path) {
            FileNode child = findChild(current, dir);
            if (child == null) {
                child = new FileNode(dir);
                current.addChild(child);
            }
            current = child;
        }
        if (isFile) {
            current.addChild(new FileNode(name, size));
        } else {
            current.addChild(new FileNode(name));
        }
    }

    public FileNode findChild(FileNode parent, String name) {
        for (FileNode child : parent.getChildren()) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    public void printTree() {
        // Direkt nur die Kinder des obersten "root"-Knotens ausgeben
        for (FileNode child : root.getChildren()) {
            printTree(child, "");
        }
    }

    private void printTree(FileNode node, String indent) {
        System.out.println(indent + (node.isFile() ? "File: " : "Dir: ") + node.getName() +
                           (node.isFile() ? " (" + node.getSize() + " KB)" : ""));
        if (!node.isFile()) {
            for (FileNode child : node.getChildren()) {
                printTree(child, indent + "    ");
            }
        }
    }

    public FileNode getRoot() {
        return root;
    }

    public int calcSizesOfDirs(FileNode root, int level) {
        int res = 0;
        int size_of_dir = 0;

        // file tree rekursiv von unten aufrufen
        if (root.isFile()) {
            return root.getSize();
        }
        for (FileNode child : root.getChildren()) {
            if (child.isFile()) {
                res += child.getSize();
            } else {
                size_of_dir = calcSizesOfDirs(child, level + 1);
                res += size_of_dir;
                System.out.println(size_of_dir);
                dirs_size.add(size_of_dir);
                if (size_of_dir <= 100000) {
                    count += size_of_dir;
                }
            }
        }
        return res;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Integer> getSizeArray() {
        dirs_size.sort(null);
        return dirs_size;
    }
}
