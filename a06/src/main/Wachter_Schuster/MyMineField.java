package Wachter_Schuster;

import java.util.ArrayList;
import java.util.List;

import assignment6_int.Cell;
import assignment6_int.MineField;

public class MyMineField implements MineField {
    private Cell field[][];
    private Path pathType;

    public MyMineField(int nrLines, int nrColumns) {
        this.field = new Cell[nrLines][nrColumns];
    }

    // needed for desired Path (minPath || maxPath)
    public void setPathType(Path pathType) {
        this.pathType = pathType;
    }

    public Path getPathType() {
        return this.pathType;
    }

    @Override
    public int getShortestDistance(Cell start, Cell end) {
        int distance = 0;
        int startLine = start.getLine();
        int startCol = start.getColumn();

        if (startLine == end.getLine() && startCol == end.getColumn())
            return distance;

        // we increase/decrease startLine/startCol and count
        // till startLine = endLine and startCol = endCol.
        while (startLine != end.getLine() || startCol != end.getColumn()) {
            if (startLine < end.getLine()) {
                startLine++;
                distance++;
            }
            if (startLine > end.getLine()) {
                startLine--;
                distance++;
            }
            if (startCol < end.getColumn()) {
                startCol++;
                distance++;
            }
            if (startCol > end.getColumn()) {
                startCol--;
                distance++;
            }
        }

        return distance - 1;
    }

    @Override
    public List<Cell> getShortestPath(Cell start, Cell end) {

        Path curr = getPathType();
        return curr.getPath(field, start, end);
    }

    @Override
    public Cell getCell(int line, int column) {
        return field[line][column];
    }

    public void setCell(Cell c) {
        field[c.getLine()][c.getColumn()] = c;
    }

    @Override
    public int getNrOfLines() {
        return field.length;
    }

    @Override
    public int getNrOfColumns() {
        return field[0].length;
    }

    /** passes this field on to the needed Path-Algorithm **/
    public Cell[][] getField() {
        return this.field;
    }

}