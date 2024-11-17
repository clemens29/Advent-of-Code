package day9;

public class Node {
    public Node next;
    public Node prev;
    public int row;
    public int col;

    public Node(int start_row, int start_col) {
        next = null;
        prev = null;
        row = start_row;
        col = start_col;
    }
}
