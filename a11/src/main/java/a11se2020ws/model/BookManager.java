// model

package a11se2020ws.model;

import java.util.ArrayList;

import a11se2020ws.presentation.MainFX;

public class BookManager {

    private MainFX observer;
    private ArrayList<Book> books;

    /** Stores and manages books **/
    public BookManager() {
        this.books = new ArrayList<Book>();
    }

    /** checks if book is in list **/
    public boolean contains(String isbn) {
        for (int i = 0; i < books.size(); i++)
            if (books.get(i).getISBN().equals(isbn)) {
                return true;
            }
        return false;
    }

    /**
     * Adds book to the list.
     * 
     * @return ture if 'isbn' does not already exist, else false
     **/
    public boolean addBook(String title, String author, int year, String isbn) {
        if (!contains(isbn)) {
            books.add(new Book(title, author, year, isbn));
            observer.update(books);
            return true;
        } else
            return false;
    }

    /**
     * Removes book with 'isbn' from this list.
     * 
     * @param isbn
     * @return true if found and removed, else false
     **/
    public boolean removeBook(String isbn) {
        for (int i = 0; i < books.size(); i++)
            if (books.get(i).getISBN().equals(isbn)) {
                books.remove(i);
                observer.update(books);
                return true;
            }
        return false;
    }

    /**
     * Edits book with certain isbn.
     * 
     * @return true if found and edited, else false
     **/
    public boolean editBook(String title, String author, int year, String isbn) {
        for (int i = 0; i < books.size(); i++)
            if (books.get(i).getISBN().equals(isbn)) {
                books.get(i).setTitle(title);
                books.get(i).setAuthor(author);
                books.get(i).setYear(year);
                books.get(i).setISBN(isbn);
                observer.update(books);
                return true;
            }
        return false;
    }

    /** @return List of Books **/
    public ArrayList<Book> getBookList() {
        return this.books;
    }

    /** prints all books stored in the list **/
    public void printBookList() {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }

    /** sets a new observer **/
    public void setObserver(MainFX obs) {
        this.observer = obs;
    }
}
