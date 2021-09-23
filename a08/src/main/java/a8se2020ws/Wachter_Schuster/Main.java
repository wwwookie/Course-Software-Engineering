package a8se2020ws.Wachter_Schuster;

import a8se2020ws.A8SE2020WS;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        A8SE2020WS a1 = new A8SE2020WS();
        System.out.println("-----------------------Test 1-----------------------");
        a1.reconstructFromClass(Class.forName("a8se2020ws.Wachter_Schuster.MyClass1"));

        A8SE2020WS a2 = new A8SE2020WS();
        System.out.println("-----------------------Test 2-----------------------");
        a2.reconstructFromClass(Class.forName("a8se2020ws.Wachter_Schuster.MyClass2"));

        A8SE2020WS a3 = new A8SE2020WS();
        System.out.println("-----------------------Test 3-----------------------");
        a3.reconstructFromClassName("a8se2020ws.Wachter_Schuster.MyClass1");


    }
}