package Wachter_Schuster;

import java.io.File;
import java.io.IOException;
import java.util.List;

import assignment6_int.*;

public class Main {
    public static void main(String[] args) throws IOException {

        MyFieldGenerator gen = new MyFieldGenerator();
        MineField mField = gen.loadFieldFromFile(new File("src\\main\\Wachter_Schuster\\input.txt"));
        Cell c00 = mField.getCell(0, 0);
        Cell c14 = mField.getCell(1, 4);
        Cell c36 = mField.getCell(3, 6);

        int distance1 = mField.getShortestDistance(c00, c14); // should return 4
        int distance2 = mField.getShortestDistance(c00, c36); // should return 8

        System.out.println(distance1);
        System.out.println(distance2);

        List<Cell> path;

        // TODO: instruct mField that we are interested in 'min' paths.
        ((MyMineField) mField).setPathType(new MaxPath());

        path = mField.getShortestPath(c00, c14);
        // (1,0)->(1,1)->(1,2)->(1,3) has one mine
        // any shortest path with one mine on it is ok

        path = mField.getShortestPath(c14, c36);
        // (2,4)->(3,4)->(3,5) has no mine. Others have also no mine.

        // TODO: instruct mField that we are interested in 'max' paths.

        path = mField.getShortestPath(c00, c14);
        // (0,1)->(0,2)->(0,3)->(0,4) is one of the shortest paths with two mines

        path = mField.getShortestPath(c00, c36);
        // there are several paths with 3 mines, one of them is
        // (0,1)->(0,2)->(0,3)->(0,4)->(0,5)->(1,5)->(1,6)->(2,6)

        // for the bonus part:

        // TODO: instruct mField that we are interested in 'safest' paths.

        path = mField.getShortestPath(c00, c36);
        // only one path is two steps away from any mine:
        // (1,0)->(2,0)->(3,0)->(3,1)->(3,2)->(3,3)->(3,4)->(3,5)
        // any other shortest path has a cell that is at most 1 step from a mine
    }
}