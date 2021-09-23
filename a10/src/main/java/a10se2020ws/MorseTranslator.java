package a10se2020ws;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class MorseTranslator implements Translator {

    private MorseTranslator firstInstance = null;

    private char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 
                                'f', 'g', 'h', 'i', 'j', 
                                'k', 'l', 'm', 'n', 'o', 
                                'p', 'q', 'r', 's', 't', 
                                'u', 'v', 'w', 'x', 'y', 'z' };

    // pseudo map which maps to the standard alphabet
    private String[] morseAlphabet = { ".-", "-...", "-.-.", "-..", ".",
                                    "..-.", "--.", "....", "..", ".---",
                                    "-.-", ".-..", "--", "-.", "---", 
                                    ".---.", "--.-", ".-.", "...", "-", 
                                    "..-", "...-", ".--", "-..-", "-.--", "--.." };
    
    public MorseTranslator getInstance(){
        if(firstInstance == null)
            firstInstance = new MorseTranslator();
        
        return firstInstance;
    }

    @Override
    public Character translate(char[] cKey, int start, int end) {
        String word = ""; // word to build

        // denies out of bounds if there are no more blanks
        if(end == -1)   end = cKey.length;

        // translates '|' to blank if on first index
        if(cKey[start] == '|')  return ' ';

        // iterates over input cKey, builds word out of it
        // marks already translated positions with x
        for (int i = start; i < end; i++)
            if(cKey[i] != '|'){
                word += cKey[i];
                cKey[i] = 'x';
            }
            else i = end;

        if(end < cKey.length) cKey[end] = 'x';

        // checks if input morse is present in morse alphabet 
        // and returns according letter
        for (int i = 0; i < morseAlphabet.length; i++)
            if (morseAlphabet[i].equals(word))
                return alphabet[i];

        return null;    // -1 beim testen nicht
    }

    // return first occurence of c as int, -1 if not found
    private int indexOf(char c, char [] cKey){
        for(int i = 0; i < cKey.length; i++)
            if(cKey[i] == c)
                return i;

        return -1;
    }

    /** @return MorseReader for this str2read */
    public Reader getReader(String str2read){  return new MorseReader(str2read);   }

    /** @return MorseReader for this file */
    public Reader getReader(File file){
        String str2read = "";
        try(FileReader help = new FileReader(file)){
            while(help.ready())
                str2read += help.read();

            return new MorseReader(str2read);
        }
        catch(IOException e){   System.err.println(e.getMessage()); }

        return null;
    }

    private class MorseReader extends StringReader{
        
        private String buffer = "";
        private int next;
        private int length;

        private MorseReader(String str2read){
            
            super(str2read);

            char[] cKey = new char[str2read.length()];

            str2read.getChars(0, str2read.length(), cKey, 0);
    
            for(int i = 0; i < cKey.length; i++){
                if(cKey[i] != 'x')
                    buffer += translate(cKey, i, indexOf(' ', cKey));
            }

            next = 0;
            length = buffer.length();
        }

        public int read() {
            if (next >= length) 
                return -1;
            return buffer.charAt(next++);
        }
    }
}