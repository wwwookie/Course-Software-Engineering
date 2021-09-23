package Wachter_Schuster;

import java.util.List;
import java.util.ArrayList;
import assignment6_int.Cell;

/**
 * Path-Strategy to run over the most number of mines on the shortest Path
 * within in the MineField
 **/
public class MaxPath implements Path {

    List<Cell> path;
    Cell[][] field;

    /**
     * Gets the shortest Path between start and end Cells with the most number of
     * mines on it
     * 
     * @param start : starting cell
     * @param end   : destination cell
     * 
     * @return a list of Cells which represents the path we to from start to end
     **/
    public List<Cell> getPath(Cell[][] field, Cell start, Cell end) {
        setField(field);
        List<Cell> path = new ArrayList<>();
        int startLine = start.getLine();
        int startCol = start.getColumn();

        // similar logic to getShortestDistance()
        // instead of counting, we add a cell to the path.
        while (startLine != end.getLine()) {

            if (startLine < end.getLine()) {
                startLine++;
                path.add(field[startLine][startCol]);
            }
            if (startLine > end.getLine()) {
                startLine--;
                path.add(field[startLine][startCol]);
            }
        }

        while (startCol != end.getColumn() - 1) {
            if (startCol < end.getColumn()) {
                startCol++;
                path.add(field[startLine][startCol]);
            }
            if (startCol > end.getColumn()) {
                startCol--;
                path.add(field[startLine][startCol]);
            }
        }
        return path;
    }

    /** **/
    public int getAdjacentBombs(Cell c) {
        int x = c.getLine();
        int y = c.getColumn();

        // top
        if (x - 1 >= 0)
            if (field[x - 1][y].hasMine()) {
                return 1;
            }

        // left
        if (y - 1 >= 0)
            if (field[x][y - 1].hasMine()) {
                return 2;
            }
        // right
        if (y + 1 < c.getColumn())
            if (field[x][y + 1].hasMine()) {
                return 3;
            }

        // bottom
        if (x + 1 < c.getLine())
            if (field[x + 1][y].hasMine()) {
                return 4;
            }

        return -1;
    }

    public void setField(Cell[][] field) {
        this.field = field;
    }

}
