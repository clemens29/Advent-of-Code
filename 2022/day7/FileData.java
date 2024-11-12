package day7;

import java.util.ArrayList;
import java.util.List;

class FileNode {
    private String name;
    private int size; 
    private boolean isFile; // true für Datei, false für Verzeichnis
    private List<FileNode> children;

    // Konstruktor für Verzeichnisse
    public FileNode(String name) {
        this.name = name;
        this.size = 0;
        this.isFile = false;
        this.children = new ArrayList<>();
    }

    // Konstruktor für Dateien
    public FileNode(String name, int size) {
        this.name = name;
        this.size = size;
        this.isFile = true;
        this.children = null; // Dateien haben keine Kinder
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isFile() {
        return isFile;
    }

    public List<FileNode> getChildren() {
        return children;
    }

    public void addChild(FileNode child) {
        if (!isFile) {
            children.add(child);
        } else {
            throw new UnsupportedOperationException("Kann keine Kinder zu einer Datei hinzufügen.");
        }
    }
}