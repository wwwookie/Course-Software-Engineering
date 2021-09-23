/**
 * @author Alwin Schuster, 11902727
 */

import java.io.*;

public class Minesweeper implements Assignment1{

    /**
     * @param row           - number of rows the current field has.
     * @param col           - number of columns the current field has.
     * @param sumOfNMValues - sum of Integers of all fields - mines excluded
     */
    private int row = 0; 
    private int col = 0;
    private long sumOfNMValues = 0;

    /**
     * sets up InputStreamReader and computes methods till stream is empty.
     * @param inputFile     - .txt-file read by InputStreamReader
     * @throws IOException
     */
    public Minesweeper (File inputFile) throws IOException {

        /**
         * @param fieldCounter  - counts number of Fields created
         * @param outputString  - complete String version of Fields
         */
        int fieldCounter = 0;
        String outputString = "";
        InputStream stream = new FileInputStream(inputFile);
        InputStreamReader inputStream = new InputStreamReader(stream);      //converts File -> InputStream -> InputStreamReader
        
        while(inputStream.ready()){
            int [][] field = buildField(inputStream);
            plantMines(inputStream, field);
            insertNMValues(field);
            fieldCounter++;
            outputString = fieldtoString(field, outputString, fieldCounter);
            this.row = 0; this.col = 0;
        }

        System.out.println(outputString);
    }
    
    /**
     * uses InputStreamReader to build up a 2D-Integer-Array.
     * @param inputStream   - InputStreamReader object
     * @return              - field = 2D-Integer-Array
     * @throws IOException
     */
    private int [][] buildField(InputStreamReader inputStream) throws IOException{

        int [][] field;
        String currChar = "";

        /**
         * loop uses regex to convert digits from the inputStream to actual integers.
         */
        while(col == 0 && inputStream.ready()){
            currChar = Character.toString((char) inputStream.read());
            if(!currChar.equals("-1") && currChar.matches("\\d")){
                if(row != 0){
                    this.col = Integer.parseInt(currChar);
                }
                if(row == 0){
                    this.row = Integer.parseInt(currChar);
                }
            }   
        }

        return field = new int [row][col];
    }

    /**
     * converts '*' into 9 and '.' into 0
     * ps. a non-mine position can be at most 8, so that is why mines = 9.
     * @param field         - plant mines into this 2D-Array
     * @throws IOException
     */
    private void plantMines(InputStreamReader inputStream, int [][] field) throws IOException {

        String currChar = "";
    
            for(int a = 0; a < row; a++){
                for(int b = 0; b < col; b++){
                    currChar = Character.toString((char) inputStream.read());
                    //inserts only 9 or 0 into field
                    if(isStar(currChar))
                        field[a][b] = 9;
                    else if(isDot(currChar))
                        field[a][b] = 0;
                    else b--;
                }
            }  
    }

    /**
     * puts the non-mine-integers into the field and sums up the inserted values.
     * @return      - the complete field
     */
    private int [][] insertNMValues(int [][] field){
        for(int a = 0; a < row; a++){
            for(int b = 0; b < col; b++){
                if(field[a][b] >= 9){
                    insertSurrounding(field, a, b);
                }
            }
        }

        for(int a = 0; a < row; a++){
            for(int b = 0; b < col; b++){
                if(field[a][b] < 9) this.sumOfNMValues += field[a][b];
            }
        }

        return field;
    }

    /**
     * converts field into String-representation.
     * @param field         - will be converted in String
     * @param outputString  - sum of all field-Strings created yet
     * @param fieldCounter  - counts fields generated
     * @return              - string-rep of field
     */
    private String fieldtoString(int [][] field, String outputString, int fieldCounter){        //toString nur einzeilig! 
        
        if(row != 0 && col != 0){
            outputString += "Field: " + fieldCounter + "\n";

            for(int a = 0; a < row; a++){
                for(int b = 0; b < col; b++){
                    if(field[a][b] >= 9)
                        outputString += "*";
                    else outputString += field[a][b];               //StringBuilder.append() verwenden!
                }
                outputString += "\n";
            }
        }
        return outputString;
    }

    /**
     * checks if the string is '*'
     * @param check - current char read from InputStreamReader
     * @return      - true if check = '*'
     */
    private boolean isStar(String check){
        return check.equals("*");
    }

    /**
     * checks if the String is '.'
     * @param check - current char read from InputStreamReader
     * @return      - true if check = '.'
     */
    private boolean isDot(String check){
        return check.equals(".");
    }

    /**
     * gets a position and inserts surrounding non-mine-values if not outOfBounds
     * @param a - current row
     * @param b - current column
     */
    private void insertSurrounding(int [][] field, int a, int b){
        if(a-1 >= 0 && b-1 >= 0)    field[a-1][b-1]++;
        if(a-1 >= 0)                field[a-1][b]++;
        if(a-1 >= 0 && b+1 < col)   field[a-1][b+1]++;
        if(b-1 >= 0)                field[a][b-1]++;
        if(b + 1 < col)             field[a][b+1]++;
        if(a+1 < row && b-1 >= 0)   field[a+1][b-1]++;
        if(a+1 < row)               field[a+1][b]++;
        if(a+1 < row && b+1 < col)  field[a+1][b+1]++;
    }

    /**
     * @return sum of all non-mine-integers
     */
    public long minesweep(File inputfile) throws Exception{
        return sumOfNMValues;
    }
}