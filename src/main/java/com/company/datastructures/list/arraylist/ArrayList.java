package com.company.datastructures.list.arraylist;

import com.company.datastructures.list.List;
import com.company.datastructures.helper.Objects;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class ArrayList implements List {
    private int size;
    Object[] list;

    public ArrayList() {
        list = new Object[5];
    }

    @Override
    public void add(Object value) {
        Objects.throwIfNull(value);
        ensureCapacity();
        list[size] = value;
        size++;
    }

    @Override
    public void add(Object value, int index) {
        Objects.throwIfEmpty(isEmpty());
        Objects.throwIfNull(value);
        Objects.throwIfIndexOutOfBound(index, size);
        ensureCapacity();
        System.arraycopy(list, index, list, index + 1, size - index);
        size++;
        list[index] = value;
    }

    @Override
    public Object remove(int index) {
        Objects.throwIfEmpty(isEmpty());
        Objects.throwIfIndexOutOfBound(index, size + 1);
        Object removedElement = list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        list[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public Object get(int index) {
        Objects.throwIfEmpty(isEmpty());
        Objects.throwIfIndexOutOfBound(index, size + 1);
        return list[index];
    }

    @Override
    public Object set(Object value, int index) {
        Objects.throwIfEmpty(isEmpty());
        Objects.throwIfNull(value);
        Objects.throwIfIndexOutOfBound(index, size + 1);
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
        Objects.throwIfNull(value);
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        Objects.throwIfNull(value);
        for (int i = 0; i < size; i++) {
            if (list[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        Objects.throwIfNull(value);
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

    private void ensureCapacity() {
        if (size == list.length) {
            Object[] toBeResized = new Object[(int) (list.length * 1.5)];
            System.arraycopy(list, 0, toBeResized, 0, list.length);
            list = toBeResized;
        }
    }

    private class ArrayListIterator implements Iterator {
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
            return list[index++];
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(index - 1);
            index--;
            nextCalled = false;
        }
    }
}
