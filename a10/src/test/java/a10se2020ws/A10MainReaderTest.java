package a10se2020ws;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

class A10MainReaderTest {
	
	A10Main am;

	@BeforeEach
	void setup(){
		am = new A10Main();
	}
	
	@Test
	void testGetMorseReaderFile() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMorseReaderString() {
		fail("Not yet implemented");
	}
	
	@Test
	void testMorse_1(){
		var input = ".- -.. .";
		var expected = "ade";
		var strMR = am.getMorseReader(input);
		assertNotNull(strMR, "Morse reader is null");
		try {
			for( char expC : expected.toCharArray() ){
				int c = strMR.read();
				if ( c!= -1 ) {
					assertEquals(expC, (char)c);
				} else {
					fail("Morse reader unexpected stream end.");
				}
			}
			int c = strMR.read();
			assertEquals(c,-1);
		} catch (IOException e) {
			fail("Morse reader threw Exception " + e + " with cause: " + e.getCause());
		}
	}
	
}
