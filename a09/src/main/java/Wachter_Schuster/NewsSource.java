package Wachter_Schuster;

import java.util.ArrayList;

public class NewsSource implements Subject {

    private String name;
    private char[] pwd;

    private ArrayList<Observer> observers = new ArrayList<>();

    public NewsSource(String name, String pwdStr) {

        this.name = name;
        pwd = new char[pwdStr.length()];

        for (int i = 0; i < pwd.length; i++) {
            this.pwd[i] = pwdStr.charAt(i);
        }
    }

    public String getName() {   return name;    }

    public void notifyObservers(Event e) {
        for (Observer obs : observers) {
            obs.update(getName(), e);
        }
    }

    @Override
    public void addObserver(Observer o) {   observers.add(o);   }

    @Override
    public void removeObserver(Observer o) {    observers.remove(o);    }

    /** 
     * compares the subject's password with the given String
     * @return true if equal
     */
    public boolean comparePwd(String s) {
        String pwdStr = "";

        for (int i = 0; i < pwd.length; i++) {
            pwdStr += pwd[i];
        }
        return pwdStr.equals(s);
    }
}
