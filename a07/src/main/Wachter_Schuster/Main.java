package Wachter_Schuster;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        ItemManager im = new ItemManager();
        im.loadXml(new File("src\\Test\\input.xml"));
        im.getPrice("root");
    }
}
