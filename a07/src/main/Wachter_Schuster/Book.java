/**@author Wachter, Schuster **/

package Wachter_Schuster;

/**
 * Represents a book with 3 main attributes: name, price and isbn as valid item
 **/
public class Book extends Item {
    private int isbn;

    public Book() {}

    @Override
    public String getName() {   return name;    }

    @Override
    public void setName(String name) {  this.name = name;   }

    @Override
    public double getPrice() {  return price;   }

    public void setPrice(double price) {    this.price = price; }

    public void setISBN(int isbn) { this.isbn = isbn;   }

    public int getISBN() {  return this.isbn;   }
}
