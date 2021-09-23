//Lukas Wachter, 11904189

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Eine Map besteht aus einer Liste von Feldern, wobei jedes Feld eigene
 * Dimensionen hat und verschieden gefuellt werden kann
 */
public class Map implements Assignment1 {
    ArrayList<Field> fields = new ArrayList<Field>();
    int count = -1; // Anzahl der vorhandenen Felder in der Liste

    /**
     * @param input : zu uebergebene .txt Datei die vorgegebenem Format entsprechen
     *              muss
     */
    public Map(BufferedReader input) throws Exception {
        String line;

        while ((line = input.readLine()) != null) {
            if (Character.isDigit(line.charAt(0))) {
                if (line.charAt(0) != '0') {

                    // Dimensionen werden anhand der ersten Zeile ermittelt, entsprechendes Feld
                    // erstellt und zur Liste in Map hinzugefuegt
                    fields.add(new Field(Character.getNumericValue(line.charAt(0)),
                            Character.getNumericValue(line.charAt(2))));
                    count++;

                    // Felder werden laut Input-Datei mit Bomben an entsprechenden Positionen
                    // befuellt
                    for (int i = 0; i < fields.get(count).dimension.length; i++) {
                        line = input.readLine();
                        fields.get(count).dimension[i] = line.toCharArray();
                    }

                    setHints(this);
                    printField(fields.get(count));
                }
            }
        }
        // Summe aller hints wird aus dem output ermittelt und in die Konsole ausgegeben
        System.out.println(Long.toString(minesweep(new File("output.txt"))));
    }

    /**
     * Ermittelt fuer jedes Feld die Anzahl der anliegenden Bomben und schreibt
     * diese in die entsprechende Zelle
     * 
     * @param m : zu durchlaufende Map, in der alle Felder mit entsprechenden hints
     *          befuellt werden
     */
    private void setHints(Map m) {
        String file = "";

        for (int hinted = 0; hinted < m.fields.size(); hinted++) {
            for (int i = 0; i < m.fields.get(hinted).dimension.length; i++) {
                for (int j = 0; j < m.fields.get(hinted).dimension[i].length; j++) {
                    int mi = i - 1; // oben
                    int pi = i + 1; // unten
                    int mj = j - 1; // links
                    int pj = j + 1; // rechts

                    int bombs = 48; // 48 = 0 in ASCII

                    /** Pruefungen, ob an folgenden Stellen eine Bombe platziert ist **/
                    // links oben
                    if (mi >= 0 && mj >= 0 && isBomb(m.fields.get(hinted).dimension[mi][mj])) {
                        bombs++;
                    }

                    // links
                    if (mi >= 0 && isBomb(m.fields.get(hinted).dimension[mi][j])) {
                        bombs++;
                    }

                    // links unten
                    if (mi >= 0 && pj < m.fields.get(hinted).dimension[i].length
                            && isBomb(m.fields.get(hinted).dimension[mi][pj])) {
                        bombs++;
                    }

                    // mitte oben
                    if (mj >= 0 && isBomb(m.fields.get(hinted).dimension[i][mj])) {
                        bombs++;
                    }

                    // mitte unten
                    if (pj < m.fields.get(hinted).dimension[i].length
                            && isBomb(m.fields.get(hinted).dimension[i][pj])) {
                        bombs++;
                    }

                    // rechts oben
                    if (pi < m.fields.get(hinted).dimension.length && mj >= 0
                            && isBomb(m.fields.get(hinted).dimension[pi][mj])) {
                        bombs++;
                    }

                    // rechts
                    if (pi < m.fields.get(hinted).dimension.length && isBomb(m.fields.get(hinted).dimension[pi][j])) {
                        bombs++;
                    }

                    // rechts unten
                    if (pi < m.fields.get(hinted).dimension.length && pj < m.fields.get(hinted).dimension[i].length
                            && isBomb(m.fields.get(hinted).dimension[pi][pj])) {
                        bombs++;
                    }

                    // hier werden die tatsaechlichen hints eingetragen
                    if (isBomb(m.fields.get(hinted).dimension[i][j])) {
                        m.fields.get(hinted).dimension[i][j] = '*';
                    } else {
                        m.fields.get(hinted).dimension[i][j] = (char) bombs;
                        file += (48 - bombs);
                    }
                }
            }
        }
        // alle hints werden in eine Datei geschrieben, diese dient als Parameter fuer
        // das Interface
        try {
            writeFile("output.txt", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isBomb(char ch) {
        return ch == '*';
    }

    /**
     * gibt Feld in die Konsole aus
     * 
     * @param field : zu printendes Feld
     **/
    private void printField(Field field) {
        System.out.println("Field: " + (count + 1));
        for (char i = 0; i < field.dimension.length; i++) {
            for (char j = 0; j < field.dimension[i].length; j++) {
                System.out.print((field.dimension[i][j]));
            }
            System.out.println();
        }
    }

    /**
     * Benoetigt um File-Parameter fuer minesweep zu realisieren. Schreibt alle
     * Hints der Felder in eine eigene output.txt Datei um die Ermittlung der Summe
     * zu erleichtern
     * 
     * @param fileName : Bezeichung der zu erstellenden Datei
     * @param output   : zu lesende Daten
     */
    private void writeFile(String fileName, String output) throws Exception {
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(fileName));
        outputWriter.write(output);
        outputWriter.close();
    }

    /**
     * zu implementierendes Interface laut Assignment1
     * 
     * @param inputfile = die im Map Konstruktor uebergebene Datei
     * @return Summe aller hints aller Felder der Eingabedatei
     */
    @Override
    public long minesweep(File inputfile) throws Exception {
        long sum = 0;

        FileReader fr = new FileReader(inputfile);
        BufferedReader sumReader = new BufferedReader(fr);
        String line;

        while ((line = sumReader.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i)) && line.charAt(i) != '0') {
                    sum += Character.getNumericValue(line.charAt(i));
                }
            }
        }
        sumReader.close();
        return sum;
    }
}