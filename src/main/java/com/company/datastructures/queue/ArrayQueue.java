package com.company.datastructures.queue;

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
        resize();
        Object[] updatedQueue = new Object[size + 1];
        updatedQueue[0] = value;
        for (int i = 0; i < size; i++) {
            updatedQueue[i + 1] = queue[i];
        }
        queue = updatedQueue;
        size++;
    }

    private void resize() {
        if (size == queue.length) {
            Object[] toBeResized = new Object[queue.length * 2];
            System.arraycopy(queue, 0, toBeResized, 0, queue.length);
            queue = toBeResized;
        }
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[size - 1];
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
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
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(queue[i]);
            result.append(" ");
        }

        return result.toString();
    }
}
