/**@author Lukas Wachter, Alwin Schuster  **/

package Wachter_Schuster;

public class Main {
    public static void main(String[] args) {

        System.out.println("----------Part 1: ArrayStack Calculating----------");
        Assignment3 a3 = new Assignment3();
        String[] input = { "1.5", "2", "+", "3", "4", "-", "*" };
        System.out.println(a3.calc(input, a3.getArrayStack()));

        System.out.println("----------Part 2: ListStack Calculating----------");

        Assignment3 b3 = new Assignment3();
        System.out.println(b3.calc(input, b3.getListStack()));

        System.out.println("----------Part x: ArrStack size----------");
        ArrayStack arrStack = new ArrayStack(2);
        System.out.println(arrStack.getSize());
        arrStack.push("1");
        System.out.println(arrStack.getSize());
        arrStack.push("2");

    }
}
