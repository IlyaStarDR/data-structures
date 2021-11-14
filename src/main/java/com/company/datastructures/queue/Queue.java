package com.company.datastructures.queue;

public interface Queue extends Iterable{

    void enqueue(Object value);

    Object peek();

    Object dequeue();

    boolean isEmpty();

    boolean contains(Object value);

    int size();

    void clear();
}
