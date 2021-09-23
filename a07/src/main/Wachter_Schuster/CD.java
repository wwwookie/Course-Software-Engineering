/**@author Wachter, Schuster **/

package Wachter_Schuster;

/** Represents a CD with 2 main attributes name and price as valid item */
public class CD extends Item {

    public CD() {}

    @Override
    public String getName() {   return this.name;   }

    @Override
    public void setName(String name) {  this.name = name;   }

    @Override
    public double getPrice() {  return this.price;  }

    public void setPrice(double price) {    this.price = price; }

    /** CDs don't have an isbn hence return 0 **/
    public int getISBN() {  return 0;   }

}
