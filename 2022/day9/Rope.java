package day9;

public class Rope {
    public Node head;
    public Node tail;
    public int size;
    public boolean[][] field;
    public int size_field;

    public Rope(int size_rope, int size_field) {
        head = new Node(size_field / 2, size_field / 2);
        tail = head;
        size = size_rope;
        this.size_field = size_field;

        for (int i = 1; i < size_rope; i++) {
            Node n = new Node(size_field / 2, size_field / 2);
            tail.next = n;
            n.prev = tail;
            tail = n;
        }

        // Initialisiere das Spielfeld
        field = new boolean[size_field][size_field];
    }

    public void move(char direction) {
        moveHead(direction, head);
        moveTail(head, head.next);
    }

    private void moveHead(char direction, Node currentNode) {
        if (direction == 'U') {
            currentNode.row -= 1;
        } else if (direction == 'D') {
            currentNode.row += 1;
        } else if (direction == 'L') {
            currentNode.col -= 1;
        } else if (direction == 'R') {
            currentNode.col += 1;
        }
    }

    private void moveTail(Node current, Node next) {
        if (next == null) {
            return;
        }

        while (Math.abs(current.row - next.row) > 1 || Math.abs(current.col - next.col) > 1) {
            if (current.row != next.row) {
                if (current.row > next.row) {
                    next.row += 1;
                } else {
                    next.row -= 1;
                }
            }
            if (current.col != next.col) {
                if (current.col > next.col) {
                    next.col += 1;
                } else {
                    next.col -= 1;
                }
            }

            if (next.next == null) {
                setField(next.row, next.col);
            }
        }

        moveTail(next, next.next);
    }

    public void setField(int row, int col) {
        if (row >= 0 && row < size_field && col >= 0 && col < size_field) {
            field[row][col] = true;
        }
    }

    public int count() {
        int count = 0;
        for (int i = 0; i < size_field; i++) {
            for (int j = 0; j < size_field; j++) {
                if (field[i][j]) {
                    count++;
                }
            }
        }
        return count + 1;
    }
}
