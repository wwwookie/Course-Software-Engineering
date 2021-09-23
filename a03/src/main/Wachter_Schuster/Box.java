/**@author Lukas Wachter, Alwin Schuster */

package Wachter_Schuster;

/** One box object saves its content and is connected to the next lower box. */
public class Box <E> {

    private E content;
    private Box <E> lowerBox; // pointer to lower Box

    public Box(E g) {
        this.content = g;
    }

    public Box(E g, Box <E> b) {
        this.content = g;
        this.lowerBox = b;
    }

    public Box <E> getLowerBox() {
        return lowerBox;
    }

    public boolean hasLowerBox() {
        return lowerBox != null;
    }

    public E getContent() {
        return this.content;
    }
}