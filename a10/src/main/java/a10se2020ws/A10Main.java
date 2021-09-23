package a10se2020ws;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class A10Main implements A10Encoding{

	private MorseTranslator mt = new MorseTranslator();
	private ROT13Translator rt = new ROT13Translator();

	@Override
	public Reader getMorseReader(File file2read){
		try {
			return ((MorseTranslator) mt).getInstance().getReader(file2read);
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Writer getROT13Writer(File file2Write) {
		try {
			return rt.getInstance().getWriter(file2Write);
		} 
		catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Reader getMorseReader(String string2read) {
		return mt.getInstance().getReader(string2read);
	}

	@Override
	public Writer getROT13Writer() {
		return rt.getInstance().getWriter();
	}
}
