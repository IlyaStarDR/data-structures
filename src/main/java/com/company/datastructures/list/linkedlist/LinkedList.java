package com.company.datastructures.list.linkedlist;

import com.company.datastructures.list.List;
import com.company.datastructures.helper.Objects;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {
    private Node<T> tail;
    private Node<T> head;
    private int size;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        Objects.throwIfIndexOutOfBound(index, size);

        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<T> currentNode = getNode(index - 1);
            newNode.prev = currentNode;
            newNode.next = currentNode.next;
            currentNode.next.prev = newNode;
            currentNode.next = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        Objects.throwIfIndexOutOfBound(index, size - 1);

        Node<T> currentNode = head;
        Node<T> removedNode;
        if (index == 0) {
            removedNode = head;
            if (size == 1) {
                tail = null;
                head = null;
            } else {
                head = currentNode.next;
                head.prev = null;
            }
        } else if (index == size - 1) {
            removedNode = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            currentNode = getNode(index - 1);
            removedNode = currentNode.next;
            currentNode.next = currentNode.next.next;
            currentNode.next.prev = currentNode;
            removedNode.prev = null;
            removedNode.next = null;
        }
        size--;
        return removedNode.data;
    }

    @Override
    public T get(int index) {
        Objects.throwIfIndexOutOfBound(index, size - 1);
        Node<T> toBeGot = getNode(index);
        return toBeGot.data;
    }

    @Override
    public T set(T value, int index) {
        Objects.throwIfIndexOutOfBound(index, size - 1);

        Node<T> toBeSet = getNode(index);
        T toBeUpdated = toBeSet.data;
        toBeSet.data = value;

        return toBeUpdated;
    }

    @Override
    public void clear() {
        tail = null;
        head = null;
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
                if (get(i) == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (get(i).equals(value)) {
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
                if (get(i) == null) {
                    return i;
                }
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            if (get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (T element : LinkedList.this) {
            stringJoiner.add(element.toString());
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private Node<T> getNode(int index) {
        Node<T> current;
        if (size / 2 <= index) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = 0; i < size - index - 1; i++) {
                current = current.prev;
            }
        }
        return current;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        private Node(T data) {
            this.data = data;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current;
        private Node<T> lastReturned;
        private boolean nextCalled;

        public LinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            lastReturned = current;
            current = current.next;
            nextCalled = true;
            return data;
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException();
            }
            if (lastReturned.prev == null && lastReturned.next == null) {
                head = null;
                tail = null;
            }
            else if (lastReturned.prev == null) {
                head = lastReturned.next;
                head.prev = null;
            } else if (lastReturned.next == null) {
                tail = lastReturned.prev;
                tail.next = null;
            } else {
                lastReturned.prev.next = lastReturned.next;
                lastReturned.next.prev = lastReturned.prev;
                lastReturned.prev = null;
                lastReturned.next = null;
            }
            size--;
            nextCalled = false;
        }
    }
}
