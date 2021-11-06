package com.company.datastructures.list.linkedlist;

import com.company.datastructures.list.List;

import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList implements List {
    private Node tail;
    private Node head;
    private int size;

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
        newNode.setData(value);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            head.setPrev(newNode);
            newNode.setNext(head);
            head = newNode;
        } else if (index == size) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            Node currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }

            newNode.setPrev(currentNode);
            newNode.setNext(currentNode.getNext());
            currentNode.getNext().setPrev(newNode);
            currentNode.setNext(newNode);
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
                head = currentNode.getNext();
                head.setPrev(null);
            }
        } else if (index == size - 1) {
            removedNode = tail;
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            removedNode = currentNode.getNext();

            currentNode.setNext(currentNode.getNext().getNext());
            currentNode.getNext().setPrev(currentNode);
            removedNode.setPrev(null);
            removedNode.setNext(null);
        }
        size--;
        return removedNode.getData();
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node element = head;
        for (int i = 0; i < index; i++) {
            element = element.getNext();
        }
        return element.getData();
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
            element = element.getNext();
        }
        Object toBeUpdated = element.getData();
        element.setData(value);
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
        while (current != null) {
            stringJoiner.add(current.getData().toString());
            current = current.getNext();
        }

        return stringJoiner.toString();
    }
}
