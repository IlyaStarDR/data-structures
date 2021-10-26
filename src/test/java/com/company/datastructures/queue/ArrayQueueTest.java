package com.company.datastructures.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//enqueue A, B -> [A, B] B, A -> dequeue
class ArrayQueueTest {

    @Test
    void testEnqueueAndDequeueWorkCorrectlyChangingSize() {
        ArrayQueue queue = new ArrayQueue();

        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals(2, queue.size(), "Size differs");

        queue.dequeue();
        queue.dequeue();

        assertEquals(0, queue.size(), "Size differs");
    }

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

    @Test
    void testEnqueueDequeueAndPeek() {
        ArrayQueue queue = new ArrayQueue();

        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals(2, queue.size(), "Size differs");
        assertEquals("B", queue.peek(), "Elements differ");
        assertEquals("B", queue.peek(), "Elements differ");
        assertEquals(2, queue.size(), "Size differs");

        queue.dequeue();
        assertEquals("A", queue.peek(), "Elements differ");
        assertEquals(1, queue.size(), "Size differs");

    }

    @Test
    void testThrowIllegalStateExceptionWhenDequeueOnEmptyQueue() {
        ArrayQueue queue = new ArrayQueue();
        assertThrows(IllegalStateException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    void testThrowIllegalStateExceptionWhenPeekOnEmptyQueue() {
        ArrayQueue queue = new ArrayQueue();
        assertThrows(IllegalStateException.class, () -> {
            queue.peek();
        });
    }

    @Test
    void testIsEmptyReturnTrueOnNewQueue() {
        ArrayQueue queue = new ArrayQueue();

        assertTrue(queue.isEmpty(), "Queue is not empty");
    }

    @Test
    void testIsEmptyReturnFalseOnQueueWithData() {
        ArrayQueue queue = new ArrayQueue();

        queue.enqueue("C");

        assertFalse(queue.isEmpty(), "Queue is empty");
    }

    @Test
    void testIsEmptyReturnTrueOnQueueClear() {
        ArrayQueue queue = new ArrayQueue();

        queue.enqueue("C");
        queue.clear();

        assertTrue(queue.isEmpty(), "Queue is not empty");
    }

    @Test
    void testContainsReturnTrue() {
        ArrayQueue queue = new ArrayQueue();

        queue.enqueue("C");

        assertTrue(queue.contains("C"), "Queue doesn't contain such element");

    }

    @Test
    void testContainsReturnFalse() {
        ArrayQueue queue = new ArrayQueue();

        queue.enqueue("C");

        assertFalse(queue.contains("D"), "Queue contains such element");
    }
}