package a10se2020ws;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

class A10MainWriterTest {
	
	A10Main am;

	@BeforeEach
	void setup(){
		am = new A10Main();
	}

	@Test
	void testGetROT13WriterFile() {
		fail("Not yet implemented");
	}

	@Test
	void testGetROT13Writer() {
		fail("Not yet implemented");
	}
	
	@Test
	void testROT13_1(){
		var input = "beach";
		var expected = "ornpu";
		var strWR = am.getROT13Writer();
		assertNotNull(strWR, "ROT13 writer is null");
		try {
			strWR.write(input);
			String result = strWR.toString();
			assertEquals(expected,result);
		} catch (IOException e) {
				fail("ROT13 writer threw Exception " + e + " with cause: " + e.getCause());
		}
	}
}
