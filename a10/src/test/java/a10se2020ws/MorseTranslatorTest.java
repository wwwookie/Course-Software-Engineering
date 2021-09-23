package a10se2020ws;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseTranslatorTest {
    MorseTranslator mtr;

    @BeforeEach
    void setup() {
        mtr = new MorseTranslator();
    }

    /** Decodes morse input, passes if decoded correctly **/
    @Test
    void testTranslate() {
        String input = ".... . .-.. .-.. --- |.-- --- .-. .-.. -..";
        String expected = "hello world";

        String actual = "";

        char[] cKey = input.toCharArray();

        for (int i = 0; i < input.length(); i++) {
            if (cKey[i] != 'x') {
                actual += mtr.translate(cKey, i, indexOf(' ', cKey));
            }
        }
        assertEquals(expected, actual);
    }

    // return first occurence of c as int, -1 if not found
    private int indexOf(char c, char[] cKey) {
        for (int i = 0; i < cKey.length; i++)
            if (cKey[i] == c)
                return i;

        return -1;
    }

}
