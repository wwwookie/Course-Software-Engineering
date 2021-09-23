package Wachter_Schuster;

import a9se2020ws.A9Spreader;
import a9se2020ws.AuthenticationException;
import a9se2020ws.UntrustedSourceException;

public class Main {
    public static void main(String[] args) throws UntrustedSourceException, AuthenticationException {
        
        A9Spreader test = new A9Spreader();

        test.registerTrustedSource("Test1", "123");
        test.registerTrustedSource("ORF", "455");

        NewsReceiver a = new NewsReceiver();
        NewsReceiver b = new NewsReceiver();
        NewsReceiver c = new NewsReceiver();

        test.getSubject("Test1").addObserver(a);
        test.getSubject("ORF").addObserver(b);
        test.getSubject("ORF").addObserver(c);
        c.addTag("#breaking-news");

        test.spreadNews("Der ORF berichtet", "ORF", "455");
        test.spreadNews("#breaking-news Der ORF berichtet hotte News!", "ORF", "455");
        test.spreadNews("Hallo und herzlich willkommen zu den #Nachrichten", "Test1", "123");
    }
}
