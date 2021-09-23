package a11se2020ws.model;

/** represents a book with typical attributes: title, author, published year, isbn **/
public class Book {
    private String title;
    private String author;
    private int year;
    private String isbn;

    /** Represents a Book with its according attributes **/
    public Book(String title, String author, int year, String isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    public Book() {
    };

    // returns title of book
    public String getTitle() {
        return this.title;
    }

    // sets title of book
    public void setTitle(String title) {
        this.title = title;
    }

    // returns author of book
    public String getAuthor() {
        return this.author;
    }

    // sets author of book
    public void setAuthor(String author) {
        this.author = author;
    }

    // returns year of book
    public int getYear() {
        return this.year;
    }

    // sets year of book
    public void setYear(int year) {
        this.year = year;
    }

    // returns isbn of book
    public String getISBN() {
        return this.isbn;
    }

    // sets isbn of book
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    // returns a book in form of String
    public String toString() {
        return getTitle() + ", " + getAuthor() + ", " + getYear() + ", " + getISBN();
    }
}
