package day14;

public class Robot {
    public int row;
    public int col;
    public int velRow;
    public int velCol;

    public Robot(int row, int col, int velRow, int velCol) {
        this.row = row;
        this.col = col;
        this.velRow = velRow;
        this.velCol = velCol;
    }

    public void move(int maxWidth, int maxHeight) {
        row += velRow;
        col += velCol;
        if (row < 0) {
            row += maxHeight;
        }
        if (col < 0) {
            col += maxWidth;
        }
        if (row >= maxHeight) {
            row -= maxHeight;
        }
        if (col >= maxWidth) {
            col -= maxWidth;
        }
    }
}