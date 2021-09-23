package Wachter_Schuster;

import assignment6_int.Cell;

public class MyCell implements Cell {

    private int line, column;
    private boolean hasMine;
    private boolean visited;

    public MyCell(int line, int column, boolean hasMine) {
        this.line = line;
        this.column = column;
        this.hasMine = hasMine;
    }

    /**
     * returns the line number of this cell (starting at 0, increases top-down)
     */
    public int getLine() {  return line;    }

    /**
     * returns the column number of the cell (starting at 0, increases from left to
     * right);
     */
    public int getColumn() {    return column;  }

    /**
     * returns true if this cell has a mine, false otherwise
     */
    public boolean hasMine() {  return hasMine; }

    public void setVisited(boolean b) { this.visited = b;   }

    public boolean getVisited(boolean b) {  return this.visited;    }
}