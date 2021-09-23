/**
 * @author Alwin Schuster, 11902727
 */

import java.io.*;

public class Core{
    public static void main(String[] args) throws Exception {

        File inputFile = new File("Assignment1-test.txt");
        Minesweeper mySweeper = new Minesweeper(inputFile);
        System.out.print(mySweeper.minesweep(inputFile));
    }
}