package Wachter_Schuster;

/**
 * Observer can opt to receive news from specific sources which then will be
 * updated if these sources publish news
 **/
public interface Observer {

    /**
     * updates the observer with news from source containing source name, news and
     * timestamp when news were received
     * 
     * @param src publishing source
     * @param e   happening event (news)
     **/
    public void update(Object src, Event e);
}
