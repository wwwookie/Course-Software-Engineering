package Wachter_Schuster;

/**
 * Represents a news source which needs a name and password to authenticate.
 * News-sources manage a list of observers which wait for news to be published.
 **/
public interface Subject {

    /** returs the name of the news-source **/
    public String getName();

    /**
     * validates the password of the news-source
     * 
     * @param s password to compare with registered password
     **/
    public boolean comparePwd(String s);

    /**
     * add observer to the list
     * 
     * @param o observer to add
     **/
    public void addObserver(Observer o);

    /**
     * remove observer from the list
     * 
     * @param o observer to remove
     **/
    public void removeObserver(Observer o);

    /**
     * notifies observers with new Events
     * 
     * @param e happening event
     **/
    public void notifyObservers(Event e);
}
