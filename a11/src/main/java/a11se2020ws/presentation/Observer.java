package a11se2020ws.presentation;

import java.util.ArrayList;

import a11se2020ws.model.Book;

public interface Observer {
    public void update(ArrayList<Book> books);
}
