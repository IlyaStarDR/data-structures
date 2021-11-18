package com.company.datastructures.list.arraylist;

import com.company.datastructures.list.List;
import com.company.datastructures.helper.Objects;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {
    private int size;
    T[] list;

    public ArrayList() {
        list = (T[]) new Object[5];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        Objects.throwIfIndexOutOfBound(index, size + 1);
        ensureCapacity();
        System.arraycopy(list, index, list, index + 1, size - index);
        size++;
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        Objects.throwIfEmpty(isEmpty());
        Objects.throwIfIndexOutOfBound(index, size + 1);
        T removedElement = list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        list[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T get(int index) {
        Objects.throwIfEmpty(isEmpty());
        Objects.throwIfIndexOutOfBound(index, size + 1);
        return list[index];
    }

    @Override
    public T set(T value, int index) {
        Objects.throwIfEmpty(isEmpty());
        Objects.throwIfIndexOutOfBound(index, size + 1);
        T toBeSet = list[index];
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
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (list[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (list[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        if (value == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (list[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (list[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(",", "[", "]");
        for (T element : ArrayList.this) {
            result.add((CharSequence) element);
        }
        return result.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private void ensureCapacity() {
        if (size == list.length) {
            T[] toBeResized = (T[]) new Object[(int) (list.length * 1.5)];
            System.arraycopy(list, 0, toBeResized, 0, list.length);
            list = toBeResized;
        }
    }

    private class ArrayListIterator implements Iterator<T> {
        private int index;
        private boolean nextCalled;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
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
