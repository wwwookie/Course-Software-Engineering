/**@author Lukas Wachter, Alwin Schuster  **/

package Wachter_Schuster;

import assignment3_int.GenericStack;

/** Represents a stack as Array with constant length */
public class ArrayStack<E> implements GenericStack<E> {
    private E[] stack;
    private int counter; // used to keep track of elements

    public ArrayStack(int size) {
        stack = (E[]) new Object[size];
        counter = 0;
    }

    public ArrayStack() {
    }

    /** returns the top element of the stack and removes it from the stack */
    public E pop() {
        E item = null;
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        } else {
            counter--;
            item = stack[counter];
            stack[counter] = null;

        }
        return item;
    }

    /**
     * returns the top element of the stack without removing it
     */
    public E peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack[counter - 1];
    }

    /**
     * adds E on the top of the stack (last element)
     * 
     * @param E : Element to add
     */
    public void push(E element) {
        stack[counter++] = element;
    }

    /** if counter is less, equal than 0, no */
    public boolean isEmpty() {
        if (counter <= 0) {
            return true;
        }
        return false;
    }
}