package com.company.datastructures.list;

public class ArrayList implements List {
    private int size;
    Object[] list;

    public ArrayList(int capacity) {
        list = new Object[capacity];
    }

    public ArrayList() {
        list = new Object[5];
    }

    @Override
    public void add(Object value) {
        if (value == null) {
            throw new IllegalStateException("Null element is not supported");
        }
        resize();
        list[size] = value;
        size++;
    }

    @Override
    public void add(Object value, int index) {
        if (isEmpty()) {
            throw new IllegalStateException("List capacity is 0");
        }
        if (value == null) {
            throw new IllegalStateException("Null element is not supported");
        }
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        resize();
        for (int i = index; i < size; i++) {
            list[i + 1] = list[i];
        }
        size++;
        list[index] = value;
    }

    private void resize() {
        if (size == list.length) {
            Object[] toBeResized = new Object[(int) (list.length * 1.5)];
            System.arraycopy(list, 0, toBeResized, 0, list.length);
            list = toBeResized;
        }
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Object removedElement = list[index];
        size--;
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }

        return removedElement;
    }

    @Override
    public Object get(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    @Override
    public Object set(Object value, int index) {
        if (isEmpty()) {
            return null;
        }
        if (value == null) {
            throw new IllegalStateException("Null element is not supported");
        }
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException();
        }
        Object toBeSet = list[index];
        list[index] = value;
        return toBeSet;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            list[i] = null;
        }
        size = 0;
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
    public boolean contains(Object value) {
        if (isEmpty()) {
            return false;
        }
        if (value == null) {
            throw new IllegalStateException("Null element is not supported");
        }
        for (int i = 0; i < size; i++) {
            if (list[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        if (value == null) {
            throw new IllegalStateException("Null element is not supported");
        }
        for (int i = 0; i < size; i++) {
            if (list[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        if (value == null) {
            throw new IllegalStateException("Null element is not supported");
        }
        for (int i = size - 1; i >= 0; i--) {
            if (list[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < size; i++) {
            result.append(list[i]);
            if (i == size - 1) {
                return result.append(']').toString();
            }
            result.append(", ");
        }
        return result.toString();
    }
}
