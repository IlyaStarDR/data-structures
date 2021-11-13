package com.company.datastructures.list.linkedlist;

import com.company.datastructures.list.List;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList implements List, Iterable {
    private Node tail;
    private Node head;
    private int size;

    private static class Node {
        private Object data;
        private Node next;
        private Node prev;

        private Node() {
            this.data = new Object();
        }
    }


    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        if (Objects.isNull(value)) {
            throw new IllegalStateException("Null element is not supported");
        }
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node newNode = new Node();
        newNode.data = value;
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
            Node currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }

            newNode.prev = currentNode;
            newNode.next = currentNode.next;
            currentNode.next.prev = newNode;
            currentNode.next = newNode;
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node currentNode = head;
        Node removedNode;
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
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
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
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node element = head;
        for (int i = 0; i < index; i++) {
            element = element.next;
        }
        return element.data;
    }

    @Override
    public Object set(Object value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (Objects.isNull(value)) {
            throw new IllegalStateException("Null is not supported");
        }
        Node element = head;
        for (int i = 0; i < index; i++) {
            element = element.next;
        }
        Object toBeUpdated = element.data;
        element.data = value;
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
    public boolean contains(Object value) {
        if (Objects.isNull(value)) {
            throw new IllegalStateException("Null is not supported");
        }
        if (isEmpty()) {
            return false;
        }
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        if (Objects.isNull(value)) {
            throw new IllegalStateException("Null is not supported");
        }

        for (int i = 0; i < size; i++) {
            if (get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        if (Objects.isNull(value)) {
            throw new IllegalStateException("Null is not supported");
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
        Node current = head;
        while (current.next != null) {
            stringJoiner.add(current.next.toString());
            current = current.next;
        }

        return stringJoiner.toString();
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object data = current.data;
            current = current.next;
            return data;
        }
    }
}
