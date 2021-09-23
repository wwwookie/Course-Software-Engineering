/**@author Lukas Wachter, Alwin Schuster  **/

package Wachter_Schuster;

import assignment3_int.Calculator;
import assignment3_int.GenericStack;

public class Assignment3 implements Calculator {

    /** constructor must be empty **/
    public Assignment3() {
    };

    /**
     * Takes a string as input, puts it on the stack and calculates it
     * 
     * @param input : input to calculate
     * @param stack : stack variant to use
     * 
     * @return the outcome of the input
     **/
    public double calc(String[] input, GenericStack<Double> stack) {

        // checks if stack is an ArrayStack, assigns size according to input if so
        if ((stack.getClass().getSimpleName().equals("ArrayStack"))) {
            stack = new ArrayStack(input.length);
        }

        boolean parseFlag;
        char currChar;

        double a, b, parsedNum;

        /**
         * loops over input, puts numbers on the stack until any operator shows up. If
         * there is an operator switch case processes it on the 2 top most stack
         * elements
         **/
        for (int i = 0; i < input.length; i++) {
            parseFlag = true;
            if (input[i].length() == 1) {
                parseFlag = false;
                currChar = input[i].charAt(0);

                switch (currChar) {
                    case '+':
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(a + b);
                        break;

                    case '-':
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b - a);
                        break;

                    case '*':
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(a * b);
                        break;

                    case '/':
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b / a);
                        break;

                    default:
                        parseFlag = true; // if no operator, it has to be a number so we must parse
                }

            }
            if (parseFlag) {
                try {
                    parsedNum = Double.parseDouble(input[i]);
                    stack.push(parsedNum);
                } catch (Exception e) {
                    System.err.println("Number can't be parsed!");
                }
            }
        }
        return stack.pop();
    }

    /** returns a new empty ArrayStack **/
    public GenericStack<Double> getArrayStack() {
        return new ArrayStack();
    }

    /** returns a new empty ListStack **/
    public GenericStack<Double> getListStack() {
        return new ListStack();
    }
}
