package com.company.datastructures.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTest {
    private ArrayStack arrayStack;

    @BeforeEach
    void init() {
        arrayStack = new ArrayStack();
    }

    @DisplayName("Ensure size auto increase")
    @Test
    public void testPushOverInitialCapacityAndPopWorkCorrectlyAndChangeSize() {
        ArrayStack arrayStack = new ArrayStack(2);
        arrayStack.push("A");
        arrayStack.push("B");
        arrayStack.push("C");

        assertEquals(3, arrayStack.size());
        assertEquals("C", arrayStack.pop());
        assertEquals("B", arrayStack.pop());
        assertEquals("A", arrayStack.pop());

        assertEquals(0, arrayStack.size());
        assertTrue(arrayStack.isEmpty());
    }

    @DisplayName("Push - changes size, peek returns last element")
    @Test
    public void testPushAndPeek() {
        arrayStack.push("A");
        arrayStack.push("B");

        assertEquals(2, arrayStack.size());
        assertEquals("B", arrayStack.peek());
        assertEquals("B", arrayStack.peek());
        assertEquals(2, arrayStack.size());
    }

    @DisplayName("isEmpty returns on empty stack")
    @Test
    public void testIsEmptyReturnTrueOnEmptyStack() {
        assertTrue(arrayStack.isEmpty());
    }

    @DisplayName("isEmpty returns false on not empty stack")
    @Test
    public void testIsEmptyReturnFalseOnNotEmptyStack() {
        arrayStack.push("A");
        assertFalse(arrayStack.isEmpty());
    }

    @DisplayName("isEmpty returns true after clear called")
    @Test
    public void testIsEmptyReturnTrueAfterClear() {
        arrayStack.push("A");
        arrayStack.push("B");
        arrayStack.clear();
        assertTrue(arrayStack.isEmpty());
    }

    @DisplayName("Contains returns true if such element is")
    @Test
    public void testContainsReturnTrueIfSuchElementIs() {
        arrayStack.push("A");
        arrayStack.push("B");

        assertTrue(arrayStack.contains("A"));
    }

    @DisplayName("Contains returns false if not such element")
    @Test
    public void testContainsReturnFalseIfNotSuchElement() {
        arrayStack.push("A");
        arrayStack.push("B");

        assertFalse(arrayStack.contains("C"));
    }

    @DisplayName("Contains returns false on empty stack")
    @Test
    public void testContainsReturnFalseOnEmptyStack() {
        assertFalse(arrayStack.contains("C"));
    }

    @DisplayName("Iterator hasNext on empty stack returns false")
    @Test
    public void testThrowIllegalStateExceptionWhenPopOnEmptyStack() {
        assertThrows(IllegalStateException.class, () -> {
            arrayStack.pop();
        });
    }

    @DisplayName("Iterator hasNext on empty stack returns false")
    @Test
    void testIteratorHasNextReturnFalseOnEmptyQueue() {
        Iterator iterator = arrayStack.iterator();
        assertFalse(iterator.hasNext(), "Stack has next value");
    }

    @DisplayName("Iterator hasNext returns true on not empty stack")
    @Test
    void testIteratorHasNextReturnTrueOnNotEmptyStack() {
        arrayStack.push(2);
        Iterator iterator = arrayStack.iterator();
        assertTrue(iterator.hasNext(), "Stack has not next value");
    }

    @DisplayName("Iterator next throws on empty stack")
    @Test
    void testIteratorNextThrowsOnEmptyStack() {
        Iterator iterator = arrayStack.iterator();
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @DisplayName("Iterator next returns element on not empty stack")
    @Test
    void testIteratorNextReturnsElementOnNotEmptyStack() {
        arrayStack.push(2);
        arrayStack.push(3);
        Iterator iterator = arrayStack.iterator();
        assertTrue(iterator.hasNext(), "Stack has not next value");
        assertEquals(2, iterator.next(), "Elements differ");
        assertTrue(iterator.hasNext(), "Stack has not next value");
        assertEquals(3, iterator.next(), "Elements differ");
        assertFalse(iterator.hasNext(), "Stack has next value");
    }

    @DisplayName("Iterator remove throws if next method not called")
    @Test
    void testIteratorRemoveThrowsIfNextMethodNotCalled() {
        Iterator iterator = arrayStack.iterator();
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @DisplayName("Iterator remove element if next method called")
    @Test
    void testIteratorRemoveElementIfNextMethodCalled() {
        arrayStack.push(2);
        arrayStack.push(3);
        Iterator iterator = arrayStack.iterator();
        assertTrue(iterator.hasNext(), "Stack has not next value");
        iterator.next();
        iterator.remove();
        assertEquals(1, arrayStack.size(), "Size differs");
        assertTrue(iterator.hasNext(), "Stack has not next value");
        iterator.next();
        iterator.remove();
        assertEquals(0, arrayStack.size(), "Size differs");
        assertFalse(iterator.hasNext(), "Stack has next value");
    }

    @DisplayName("Iterator remove throws if method called twice")
    @Test
    void testIteratorRemoveThrowsIfRemoveCalledTwice() {
        arrayStack.push(2);
        Iterator<Integer> iterator = arrayStack.iterator();
        assertTrue(iterator.hasNext(), "Stack has not next value");
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }
}