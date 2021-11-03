package com.company.datastructures.stack;

public class ArrayStack implements Stack {
    private int size;
    private Object[] array;

    public ArrayStack() {
        array = new Object[10];
    }

    public ArrayStack(int initialCapacity) {
        array = new Object[initialCapacity];
    }

    @Override
    public void push(Object value) {
        resize();
        array[size] = value;
        size++;
    }

    private void resize() {
        if (array.length == size) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        Object result = array[size - 1];
        size--;
        return result;
    }

    @Override
    public Object peek() {
        return array[size - 1];
    }

    @Override
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            Object valueInStack = array[i];
            if (value.equals(valueInStack)) {
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
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }
}