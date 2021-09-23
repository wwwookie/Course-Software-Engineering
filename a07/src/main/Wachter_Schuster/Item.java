/**@author Wachter, Schuster **/

package Wachter_Schuster;

/** Abstract parent class of Book, Cd and Listing. */
public abstract class Item {

    protected String name;
    protected double price;

    //sollten alle nicht abstract sein, dann muss man die methoden try/catchen.
    
    /** @return the name of this particular Item. */
    abstract public String getName();

    /** Item's name is set with this. */
    abstract public void setName(String name);

    /** @return the price of this particular Item. */
    abstract public double getPrice();
}