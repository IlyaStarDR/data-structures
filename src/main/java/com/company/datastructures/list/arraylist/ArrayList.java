package com.company.datastructures.list.arraylist;

import com.company.datastructures.list.List;

import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayList implements List, Iterable {
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
        checkNull(value);
        resize();
        list[size] = value;
        size++;
    }

    @Override
    public void add(Object value, int index) {
        checkNull(value);
        checkIndex(index, size);
        if (isEmpty()) {
            throw new IllegalStateException("List capacity is 0");
        }
        resize();
        System.arraycopy(list, index, list, index + 1, size - index);
        size++;
        list[index] = value;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index, size + 1);
        Object removedElement = list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        list[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public Object get(int index) {
        checkIndex(index, size + 1);
        if (isEmpty()) {
            return null;
        }
        return list[index];
    }

    @Override
    public Object set(Object value, int index) {
        checkNull(value);
        checkIndex(index, size + 1);
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
        checkNull(value);
        if (isEmpty()) {
            return false;
        }
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        checkNull(value);
        for (int i = 0; i < size; i++) {
            if (list[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        checkNull(value);
        for (int i = size - 1; i >= 0; i--) {
            if (list[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            result.add(list[i].toString());
        }
        return result.toString();
    }

    @Override
    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private void resize() {
        if (size == list.length) {
            Object[] toBeResized = new Object[(int) (list.length * 1.5)];
            System.arraycopy(list, 0, toBeResized, 0, list.length);
            list = toBeResized;
        }
    }
    
    private static void checkNull(Object value) {
        if (value == null) {
            throw new IllegalStateException("Null element is not supported");
        }
    }
    
    private static void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    private class ArrayListIterator implements Iterator {
        private int count;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Object next() {
            return list[count++];
        }
    }
}
