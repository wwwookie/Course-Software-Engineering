package a9se2020ws;

import java.util.ArrayList;
import java.util.List;

import Wachter_Schuster.*;

/**
 * Represents a broadcasting component which can register news sources and
 * spread news through these sources
 **/
public class A9Spreader implements NewsSpreader {

    private List<Subject> subjects;

    // Default constructor must be present, you may add implementation if needed
    public A9Spreader() {   this.subjects = new ArrayList<>();  }

    /**
     * registers a new source as trusted, adds it to the list of subjects
     * 
     * @param source the name of the source
     * @param pwd    password of the source
     **/
    public boolean registerTrustedSource(String source, String pwd) {

        if (findSubject(source)) {
            return false;
        } else {
            subjects.add(new NewsSource(source, pwd));
            return true;
        }
    }

    /**
     * sends out new news from selected sources to all observers observing these
     * sources
     * 
     * @param news   news to send out
     * @param source source to send the news from
     * @param pwd    password from source to authenticate
     **/
    public void spreadNews(String news, String source, String pwd)
            throws UntrustedSourceException, AuthenticationException {

        if (!findSubject(source)) {
            throw new UntrustedSourceException(source);
        }

        if (findSubject(source) && !(getSubject(source).comparePwd(pwd))) {
            throw new AuthenticationException(source);
        }

        Event e = new Event(news);
        getSubject(source).notifyObservers(e);
    }

    /**
     * finds a source within the trusted sources list
     * 
     * @param name name of source to find
     * @return true if source is in the list
     **/

    private boolean findSubject(String name) {

        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getName().equals(name))
                return true;
        }

        return false;
    }

    /**
     * @param name of desired source
     * @return the source from the list
     **/
    public Subject getSubject(String name) {

        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getName().equals(name)) {
                return subjects.get(i);
            }
        }
        return null;
    }

}
