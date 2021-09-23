package Wachter_Schuster;

import java.util.List;

import assignment6_int.Cell;

public interface Path {

    List<Cell> getPath(Cell[][] field, Cell start, Cell end);

}