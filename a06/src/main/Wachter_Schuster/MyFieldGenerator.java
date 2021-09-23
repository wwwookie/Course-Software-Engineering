package Wachter_Schuster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import assignment6_int.FieldGenerator;
import assignment6_int.MineField;

public class MyFieldGenerator implements FieldGenerator {

    @Override
    public MyMineField loadFieldFromFile(File inputFile) throws IOException {

        try (BufferedReader input = new BufferedReader(new FileReader(inputFile))) {
            MyMineField mField = new MyMineField(0, 0);
            String line;
            if ((line = input.readLine()) != null) {
                if (Character.isDigit(line.charAt(0))) {

                    // creates new MineField
                    if (line.charAt(0) != '0') {
                        mField = new MyMineField(Character.getNumericValue(line.charAt(0)),
                                Character.getNumericValue(line.charAt(2)));
                    }

                    // fills MineField according to input
                    for (int lin = 0; lin < mField.getNrOfLines(); lin++) {
                        line = input.readLine();
                        for (int col = 0; col < mField.getNrOfColumns(); col++) {
                            if (line.charAt(col) == '.') {
                                mField.setCell(new MyCell(lin, col, false));
                            } else if (line.charAt(col) == '*') {
                                mField.setCell(new MyCell(lin, col, true));
                            }
                        }
                    }
                }
            }
            return mField;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public MineField randomMineField(int nrLines, int nrColumns) {
        MyMineField rField = new MyMineField(nrLines, nrColumns);
        Random rand = new Random();

        for (int lin = 0; lin < rField.getNrOfLines(); lin++) {
            for (int col = 0; col < rField.getNrOfLines(); col++) {
                if (rand.nextInt(5) == 0)
                    rField.setCell(new MyCell(lin, col, true));
                else
                    rField.setCell(new MyCell(lin, col, false));
            }
        }
        return rField;
    }

}