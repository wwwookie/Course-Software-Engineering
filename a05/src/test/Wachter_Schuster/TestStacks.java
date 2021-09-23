package Wachter_Schuster;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;

public class TestStacks {
    ListStack liStack;
    ArrayStack arrStack;

    String[] sampleInput = { "1.5", "2", "+", "3", "4", "-", "*" };
    String[] sampleInput2 = { "1.5", "2", "+", "3", "4", "-", "*", "1", "2", "/" };

    String[] invalidInput = { "", ":", "?", "3", "4", "!", "*" };
    String[] invalidInput2 = { "*", "*", "1", "1", "*", "*" };
    String[] invalidInputDiv = { "2", "0", "/" };

    Assignment3 list;

    @BeforeEach
    public void setup() {
        list = new Assignment3();
        arrStack = new ArrayStack(10);
        liStack = new ListStack();
    }

    /** Task 2 - test Calculator behavior **/
    @Test
    public void testCalc() {
        // advantage of assertAll: test doesn't abort after an assertion fails, shows in
        // report how many passed/failed
        assertAll("test calc() for both stacks with various inputs",
                () -> assertEquals(-3.5, list.calc(sampleInput, arrStack)),
                () -> assertEquals(-3.5, list.calc(sampleInput, liStack)),
                () -> assertEquals(0.5, list.calc(sampleInput2, arrStack)),
                () -> assertEquals(0.5, list.calc(sampleInput2, liStack)));
    }

    /** Task 2 - test Calculator behavior on invalid inputs **/
    @Test
    public void testCalcInputException() {
        /** Arraystack **/
        Assertions.assertThrows(NumberFormatException.class, () -> {
            list.calc(invalidInput, arrStack);
        });
        Assertions.assertThrows(EmptyStackException.class, () -> {
            list.calc(invalidInput2, arrStack);
        });
        Assertions.assertThrows(ArithmeticException.class, () -> {
            list.calc(invalidInputDiv, arrStack);
        });

        /** ListStack **/
        Assertions.assertThrows(NumberFormatException.class, () -> {
            list.calc(invalidInput, liStack);
        });
        Assertions.assertThrows(EmptyStackException.class, () -> {
            list.calc(invalidInput2, liStack);
        });
        Assertions.assertThrows(ArithmeticException.class, () -> {
            list.calc(invalidInputDiv, liStack);
        });
    }

    @Test
    public void testGetSize() {
        assertEquals(10, arrStack.getInitializedSize());
        assertEquals(0, arrStack.getSize());
        assertEquals(0, liStack.getSize());
    }

    // pop/peek on empty stack.
    @Test
    public void testPeekPop() {
        Assertions.assertThrows(EmptyStackException.class, () -> {
            liStack.peek();
        });
        Assertions.assertThrows(EmptyStackException.class, () -> {
            liStack.pop();
        });

        Assertions.assertThrows(EmptyStackException.class, () -> {
            arrStack.peek();
        });
        Assertions.assertThrows(EmptyStackException.class, () -> {
            arrStack.pop();
        });
    }

    // randomized Test, pushes 0 - 10 elements
    @Test
    public void testPush() {
        int counter = 0;

        assertEquals(0, liStack.getSize());
        assertEquals(0, arrStack.getSize());

        for (int i = 0; i < 10; i++) {
            double rand = Math.random();
            if (rand < 0.5) {
                liStack.push(i);
                assertEquals(i, liStack.peek());
                arrStack.push(i);
                assertEquals(i, arrStack.peek());

                counter++;
            }
        }
        assertEquals(counter, liStack.getSize());
        assertEquals(counter, arrStack.getSize());
    }

    @Test
    public void testIsEmpty() {
        // assertTrue/False seems more compact than Equals(true, ...)
        assertTrue(liStack.isEmpty());
        liStack.push(0);
        assertFalse(liStack.isEmpty());

        assertTrue(arrStack.isEmpty());
        arrStack.push(0);
        assertFalse(arrStack.isEmpty());
    }
}