package Wachter_Schuster;

import java.sql.Timestamp;
import java.util.ArrayList;

public class NewsReceiver implements Observer {

    private ArrayList<String> followedTags = new ArrayList<>();

    @Override
    public void update(Object src, Event e) {
        if(checkTag(e.getTag()))
            System.out.println("News from: " + src + " " + getTimestamp() + " \n" + e.getNews());
    }

    /** gets the timestamp of latest news **/
    public Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /** adds new tag */
    public void addTag(String tag){ followedTags.add(tag);  }

    /** removes tag */
    public void removeTag(String tag){ followedTags.remove(tag);  }

    private boolean checkTag(String tag){
        if(followedTags.isEmpty())  return true;
        
        for(String s : followedTags){
            if(s.equals(tag))   return true;
        }

        return false;
    }
}
