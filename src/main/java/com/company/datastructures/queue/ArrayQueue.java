package com.company.datastructures.queue;

import com.company.datastructures.helper.Objects;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class ArrayQueue implements Queue {
    private int size;
    Object[] queue;

    public ArrayQueue() {
        queue = new Object[5];
    }

    public ArrayQueue(int size) {
        queue = new Object[size];
    }

    @Override
    public void enqueue(Object value) {
        ensureCapacity();
        Object[] updatedQueue = new Object[size + 1];
        updatedQueue[0] = value;
        System.arraycopy(queue, 0, updatedQueue, 1, size);
        queue = updatedQueue;
        size++;
    }
    
    @Override
    public Object peek() {
        Objects.throwIfEmpty(isEmpty());
        return queue[size - 1];
    }

    @Override
    public Object dequeue() {
        Objects.throwIfEmpty(isEmpty());
        Object result = queue[size - 1];
        size--;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            if (queue[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            result.add(queue[i].toString());
        }
        return result.toString();
    }

    @Override
    public Iterator iterator() {
        return new ArrayQueueIterator();
    }

    private void ensureCapacity() {
        if (size == queue.length) {
            Object[] toBeResized = new Object[queue.length * 2];
            System.arraycopy(queue, 0, toBeResized, 0, queue.length);
            queue = toBeResized;
        }
    }


    private class ArrayQueueIterator implements Iterator {
        private int index;
        private boolean nextCalled;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            nextCalled = true;
            return queue[index++];
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException();
            }
            System.arraycopy(queue, index, queue, index, size - index);
            queue[size - 1] = null;
            index--;
            size--;
            nextCalled = false;
        }
    }
}
