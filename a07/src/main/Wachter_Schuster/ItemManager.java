/**@author Wachter, Schuster **/

package Wachter_Schuster;

import java.io.File;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import assignment7_int.XMLData;

/**
 * An ItemManager can read XML files and is able to recreate the same structure
 * and depth with objects and their respective val from said XML files
 **/

public class ItemManager implements XMLData {

    private Listing rootItem;
    private double itemPrice;

    @Override
    public void loadXml(File input) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(input);
        Element root = doc.getDocumentElement();

        rootItem = new Listing();

        rootItem.setName(root.getAttribute("name"));
        rootItem = createObjects(root, rootItem);
    }

    /**
     * sums up all prices within the same and deeper levels of item
     * 
     * not all items are required to have a price
     * 
     * @param item : starting point of summation
     * @return all summed up price
     **/

    @Override
    public Optional<Double> getPrice(String item) {

        if (rootItem.getName().equals(item)) {
            return Optional.of((rootItem.getPrice()));
        }
        for (int i = 0; i < rootItem.getContent().size(); i++) {
            if (rootItem.getContent().get(i).getName().equals(item)) {
                return Optional.of(rootItem.getContent().get(i).getPrice());

            } else if (rootItem.getContent().get(i).getClass().getSimpleName().equals("Listing")) {
                Listing help = (Listing) rootItem.getContent().get(i);
                findItem(item, help);
                return Optional.ofNullable(itemPrice);
            }
        }
        return Optional.ofNullable(null);
    }

    /**
     * searches for an item of the provided list, all underlying levels are being
     * searched for the key
     * 
     * @param item : key to search for
     * @param list : list, can contain more lists
     **/

    private void findItem(String item, Listing list) {
        double itemPrice = 0.0;
        boolean found = false;
        Listing help;

        for (int i = 0; i < list.getContent().size(); i++) {        // linear search on current level
            if (list.getContent().get(i).getName().equals(item)) {
                found = true;
                itemPrice = list.getContent().get(i).getPrice();
                setItemPrice(itemPrice);
                break;
            }
        }

        if (!found) {                                               // search next levels via lists
            for (int i = 0; i < list.getContent().size(); i++) {
                if (list.getContent().get(i).getClass().getSimpleName().equals("Listing")) {
                    help = (Listing) list.getContent().get(i);
                    findItem(item, help);
                }
            }
        }
    }

    /**
     * creates objects according to their respective hierarchy with their correct
     * type and attributes
     **/

    public Listing createObjects(Node node, Listing parentItem) {

        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node n = node.getChildNodes().item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) n;

                if (e.getTagName().equals("book")) {
                    parentItem.addContent(makeBook(e));
                } else if (e.getTagName().equals("cd")) {
                    parentItem.addContent(makeCD(e));
                } else {
                    Listing help = new Listing();
                    help.setName(e.getAttribute("name"));
                    parentItem.addContent(help);
                    createObjects(n, help);
                }
            }
        }
        return parentItem;
    }

    /** Creates a book object with it's according attributes and values **/

    public Book makeBook(Element elem) {
        Book b = new Book();
        b.setName(elem.getAttribute("name"));
        b.setPrice((double) Double.parseDouble(elem.getAttribute("price")));
        b.setISBN(Integer.parseInt(elem.getAttribute("isbn")));
        return b;
    }

    /** Creates a CD object with it's according attributes and values **/

    public CD makeCD(Element elem) {
        CD c = new CD();
        c.setName(elem.getAttribute("name"));
        c.setPrice((double) Double.parseDouble(elem.getAttribute("price")));
        return c;
    }

    /** used to break out of recursion, sets price from searched key **/

    public void setItemPrice(double price) {
        this.itemPrice = price;
    }

}