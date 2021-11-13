package com.company.datastructures.list.linkedlist;

import com.company.datastructures.list.List;
import com.company.datastructures.helper.DataStructureHelper;

import java.util.Iterator;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T>, Iterable<T> {
    private Node<T> tail;
    private Node<T> head;
    private int size;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        DataStructureHelper.throwIfNull(value);
        DataStructureHelper.throwIfIndexOutOfBound(index, size);

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
        DataStructureHelper.throwIfEmpty(isEmpty());
        DataStructureHelper.throwIfIndexOutOfBound(index, size + 1);

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
        DataStructureHelper.throwIfEmpty(isEmpty());
        DataStructureHelper.throwIfIndexOutOfBound(index, size + 1);
        Node<T> toBeGot = getNode(index);
        return toBeGot.data;
    }

    @Override
    public T set(T value, int index) {
        DataStructureHelper.throwIfEmpty(isEmpty());
        DataStructureHelper.throwIfIndexOutOfBound(index, size + 1);
        DataStructureHelper.throwIfNull(value);

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
        DataStructureHelper.throwIfNull(value);

        if (isEmpty()) {
            return false;
        }
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        DataStructureHelper.throwIfNull(value);

        for (int i = 0; i < size; i++) {
            if (get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        DataStructureHelper.throwIfNull(value);

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
        Node<T> current = head;
        while (current.next != null) {
            stringJoiner.add(current.next.toString());
            current = current.next;
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
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
