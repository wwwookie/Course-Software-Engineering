package Wachter_Schuster;

/**
 * Represents a happening event. Events consist of the actual content (news) and
 * tags. News receiver can opt to only be notified when news contain certain
 * tags. Tags must be writen in the begining of the message.
 **/
public class Event {

    private String content;
    private String tag;

    /**
     * creates a new event with news
     * 
     * @param news to be spread
     **/
    public Event(String news) {

        if (news.charAt(0) == '#')
            this.tag = news.substring(0, news.indexOf(" "));

        this.content = news;
    }

    /** @return the news content **/
    public String getNews() {    return this.content;   }

    /** @return the news tag */
    public String getTag() {    return this.tag;    }
}
