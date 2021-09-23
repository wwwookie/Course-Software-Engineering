package a11se2020ws;

import a11se2020ws.model.BookManager;
import a11se2020ws.presentation.MainFX;
import javafx.application.Application;

public class Main {
    private static final MainFX MainFX = new MainFX();
    private static BookManager bm = new BookManager();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            public void run() {
                Application.launch(MainFX.class, args);
            }
        };

        bm.setObserver(MainFX); // sets observer which displays changes in gui

        t.start();

        Thread.sleep(3000);

        // making model changes

        bm.addBook("Der Hobbit", "JRR Tolkien", 20122012, "001");
        // observthe changes in the gui
        System.out.println(".");

        Thread.sleep(500);

        bm.addBook("Lustiges Taschenbuch - Darkwing Duck der dunkle Schatten", "WaltDisney", 20102016, "002");
        // observe the changes in the gui
        System.out.println(".");

        Thread.sleep(500);

        bm.editBook("Darkwing Duck der dunkle Schatten", "Walt Disney", 11042018, "002");
        // observe the changes in the gui
        System.out.println(".");

        Thread.sleep(500);

        bm.removeBook("001");
        // observe the changes in the gui
        System.out.println(".");
    }
}
