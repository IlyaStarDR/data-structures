package com.company.datastructures.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {
    private ArrayQueue queue;
    
    @BeforeEach
    void init() {
       queue = new ArrayQueue();
    }

    @DisplayName("Ensure size auto increase")
    @Test
    void testEnqueueAndDequeueOverflowSize() {
        ArrayQueue queue = new ArrayQueue(2);

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals(3, queue.size(), "Size differs");

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        assertEquals(0, queue.size(), "Size differs");
        assertTrue(queue.isEmpty());
    }

    @DisplayName("Enqueue, dequeue - change size, peek returns last element")
    @Test
    void testEnqueueDequeueAndPeekElements() {
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals(2, queue.size(), "Size differs");
        assertEquals("A", queue.peek(), "Elements differ");
        assertEquals("A", queue.peek(), "Elements differ");
        assertEquals(2, queue.size(), "Size differs");

        queue.dequeue();
        assertEquals("B", queue.peek(), "Elements differ");
        assertEquals(1, queue.size(), "Size differs");

    }

    @DisplayName("Dequeue throws on empty queue")
    @Test
    void testDequeueThrowsOnEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> {
            queue.dequeue();
        });
    }

    @DisplayName("isEmpty returns on empty queue")
    @Test
    void testPeekThrowsOnEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> {
            queue.peek();
        });
    }

    @DisplayName("isEmpty returns on empty queue")
    @Test
    void testIsEmptyReturnTrueOnEmptyQueue() {
        assertTrue(queue.isEmpty(), "Queue is not empty");
    }

    @DisplayName("isEmpty returns false on not empty queue")
    @Test
    void testIsEmptyReturnFalseOnNotEmptyQueue() {
        queue.enqueue("C");
        assertFalse(queue.isEmpty(), "Queue is empty");
    }

    @DisplayName("isEmpty returns true after clear called")
    @Test
    void testIsEmptyReturnTrueAfterClear() {
        queue.enqueue("C");
        queue.clear();
        assertTrue(queue.isEmpty(), "Queue is not empty");
    }

    @DisplayName("Contains returns true if such element is")
    @Test
    void testContainsReturnTrueIfSuchElementIs() {
        queue.enqueue("C");
        assertTrue(queue.contains("C"), "Queue doesn't contain such element");

    }

    @DisplayName("Contains returns false if not such element")
    @Test
    void testContainsReturnFalseIfNotSuchElement() {
        queue.enqueue("C");
        assertFalse(queue.contains("D"), "Queue contains such element");
    }

    @DisplayName("Iterator hasNext on empty queue returns false")
    @Test
    void testIteratorHasNextReturnFalseOnEmptyQueue() {
        Iterator iterator = queue.iterator();
        assertFalse(iterator.hasNext(), "Queue has next value");
    }

    @DisplayName("Iterator hasNext returns true on not empty queue")
    @Test
    void testIteratorHasNextReturnTrueOnNotEmptyQueue() {
        queue.enqueue(2);
        Iterator iterator = queue.iterator();
        assertTrue(iterator.hasNext(), "Queue has not next value");
    }

    @DisplayName("Iterator next throws on empty queue")
    @Test
    void testIteratorNextThrowsOnEmptyQueue() {
        Iterator<Integer> iterator = queue.iterator();
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @DisplayName("Iterator next returns element on not empty queue")
    @Test
    void testIteratorNextReturnsElementOnNotEmptyQueue() {
        queue.enqueue(2);
        queue.enqueue(3);
        Iterator iterator = queue.iterator();
        assertTrue(iterator.hasNext(), "Queue has not next value");
        assertEquals(3, iterator.next(), "Elements differ");
        assertTrue(iterator.hasNext(), "Queue has not next value");
        assertEquals(2, iterator.next(), "Elements differ");
        assertFalse(iterator.hasNext(), "Queue has next value");
    }

    @DisplayName("Iterator remove throws if next method not called")
    @Test
    void testIteratorRemoveThrowsIfNextMethodNotCalled() {
        Iterator iterator = queue.iterator();
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @DisplayName("Iterator remove element if next method called")
    @Test
    void testIteratorRemoveElementIfNextMethodCalled() {
        queue.enqueue(2);
        queue.enqueue(3);
        Iterator iterator = queue.iterator();
        assertTrue(iterator.hasNext(), "Queue has not next value");
        iterator.next();
        iterator.remove();
        assertEquals(1, queue.size(), "Size differs");
        assertTrue(iterator.hasNext(), "Queue has not next value");
        iterator.next();
        iterator.remove();
        assertEquals(0, queue.size(), "Size differs");
        assertFalse(iterator.hasNext(), "Queue has next value");
    }

    @DisplayName("Iterator remove throws if method called twice")
    @Test
    void testIteratorRemoveThrowsIfRemoveCalledTwice() {
        queue.enqueue(2);
        Iterator<Integer> iterator = queue.iterator();
        assertTrue(iterator.hasNext(), "Queue has not next value");
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }
}