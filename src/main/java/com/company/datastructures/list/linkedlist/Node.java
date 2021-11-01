package com.company.datastructures.list.linkedlist;

public class Node {
    private Object data;
    private Node next;

    public Node() {
        this.data = new Object();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
