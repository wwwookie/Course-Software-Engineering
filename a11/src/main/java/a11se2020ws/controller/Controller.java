// controller

package a11se2020ws.controller;

import a11se2020ws.model.BookManager;

public class Controller {

    private BookManager bm;

    public Controller(BookManager bm) {
        this.bm = bm;
    }

    public BookManager getModel() {
        return this.bm;
    }
}
