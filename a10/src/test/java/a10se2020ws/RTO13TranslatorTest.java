package a10se2020ws;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RTO13TranslatorTest {
    ROT13Translator tr;

    @BeforeEach
    void setup() {
        tr = new ROT13Translator();
    }

    /** Encodes a string, passes if encoded correctly **/
    @Test
    void testTranslate() {
        String input = "Hello World!";
        String expected = "Uryyb Jbeyq!";

        String actual = "";

        for (int i = 0; i < input.length(); i++) {
            actual += tr.translate(input.toCharArray(), i, input.length());
        }
        assertEquals(expected, actual);
    }

}
