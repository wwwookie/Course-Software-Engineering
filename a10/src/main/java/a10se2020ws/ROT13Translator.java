package a10se2020ws;

import java.io.IOException;
import java.io.StringWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class ROT13Translator implements Translator {

    private ROT13Translator firstInstance = null;

    public ROT13Translator getInstance(){
        if(firstInstance == null)
            firstInstance = new ROT13Translator();
        
        return firstInstance;
    }

    /**
     * shifts the character of cKey[start] 13bits, is case-sensitive and
     * ignores special characters
     **/
    public Character translate(char[] cKey, int start, int end) {
        char c = cKey[start];
        if (c >= 'a' && c <= 'm')
            return c += 13;
        else if (c >= 'A' && c <= 'M')
            return c += 13;
        else if (c >= 'n' && c <= 'z')
            return c -= 13;
        else if (c >= 'N' && c <= 'Z')
            return c -= 13;

        return c;
    }

    /** @return ROT13StringWriter */
    public Writer getWriter(){  return new ROT13StringWriter(); }

    /** @return ROT13FileWriter */
    public Writer getWriter(File file) throws IOException {
        return new ROT13FileWriter(file);
    }

    // writes ROT13Translation in a string
    private class ROT13StringWriter extends StringWriter{

        private String buffer;

        private ROT13StringWriter(){   buffer = "";    }

        public void write(String str2write){

            char[] cKey = str2write.toCharArray();

            for(int i = 0; i < cKey.length; i++)
                buffer += translate(cKey, i, 0);
        }

        public String toString(){   return buffer;  }
    }


    // writes ROT13Translation in a file
    private class ROT13FileWriter extends FileWriter{

        private FileWriter buffer;
        private String content;

        private ROT13FileWriter(File file) throws IOException {
            super(file);
            buffer = new FileWriter(file);
            content = "";
        }

        public void write(String str2write) throws IOException{
            
            char[] cKey = str2write.toCharArray();

            for(int i = 0; i < cKey.length; i++){
                buffer.write(translate(cKey, i, 0));
                content += translate(cKey, i, 0);
            }
        } 

        public String toString(){   return content; }
    }
}