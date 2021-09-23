/**@author Wachter, Schuster **/

package Wachter_Schuster;

import java.util.ArrayList;

/** Represents lists which contain either books, cds and/ or more lists. */
public class Listing extends Item {

    private ArrayList<Item> content;

    /** constructor, initializes a new List */
    public Listing() {  content = new ArrayList<>();    }

    /** @return the sum of all prices in this list. */
    public double getPrice() {

        int sum = 0;

        for (int i = 0; i < content.size(); i++) {
            sum += content.get(i).getPrice();
        }

        return sum;
    }

    /** @return the name of the list **/
    public String getName() {   return this.name;   }

    /** sets the name of the list **/
    public void setName(String name) {  this.name = name;   }

    /** @return the content of this list (leave-items and lists). */
    public ArrayList<Item> getContent() {   return this.content;    }

    /** Adds an Item to this List's content list. */
    public void addContent(Item item) { content.add(item);  }
}