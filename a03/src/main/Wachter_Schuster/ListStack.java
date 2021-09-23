/** @author Lukas Wachter, Alwin Schuster */

package Wachter_Schuster;
import assignment3_int.GenericStack;

/** 
 * Represents a Stack built of boxes.
 * @param topBox : the top object of the stack
 * 
 * Does not allow null!
 */
public class ListStack<E> implements GenericStack<E> {

    private Box <E> topBox;

    public ListStack(){ this.topBox = new Box<>(null, null); }

    /** Returns the top element of the stack and removes it from the stack. */
    public E pop() {
        if (isEmpty()) {
            System.out.println("Error: Stack is empty - cannot pop.");
            return null;
        } 
        else {
            Box <E> boxToPop = topBox;
            topBox = topBox.getLowerBox();
            return boxToPop.getContent();
        }
    }

    /** Returns the top element of the stack without removing it. */
    public E peek() {
        if(isEmpty()){
            System.out.println("Error: Stack is empty - cannot peek.");
            return null;
        }
        else return topBox.getContent();
    }

    /** Adds E element on the top of the stack.*/
    public void push(E element) { topBox = new Box<>(element, topBox); }

    /** Checks if the stack is empty. */
    public boolean isEmpty() { return !topBox.hasLowerBox(); }


    /** 
     * A box has:
     * @param content   : which contains a generic object
     * @param lowerBox  : which remembers the box under this box
     * 
     * So we can stack boxes on boxes, without losing track.
     */

    private class Box <E> {

        private E content;
        private Box <E> lowerBox;

        private Box(E g, Box <E> b) {
            this.content = g;
            this.lowerBox = b;
        }

        private Box <E> getLowerBox() { return lowerBox; }

        private boolean hasLowerBox() { return lowerBox != null; }

        private E getContent() { return this.content; }
    }
}