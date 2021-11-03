package com.company.datastructures.list.linked.linkedlist;

import com.company.datastructures.list.List;
import com.company.datastructures.list.linked.Node;

import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList implements List {
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

        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            newNode.setNext(currentNode.getNext());
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
            head.setNext(currentNode.getNext());
        } else {
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            removedNode = currentNode.getNext();
            if (index == size - 1) {
                currentNode.setNext(null);
            } else {
                currentNode.setNext(currentNode.getNext().getNext());
            }
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
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
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
        StringJoiner result = new StringJoiner(",", "[", "]");
        Node currentNode = head;
        result.add(currentNode.toString());
        while (Objects.nonNull(currentNode.getNext())) {
            currentNode = currentNode.getNext();
            result.add(currentNode.toString());
        }
        return result.toString();
    }
}
