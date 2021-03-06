package com.company.datastructures.stack;

public interface Stack extends Iterable{

    void push(Object value);

    Object pop();

    Object peek();

    boolean contains(Object value);

    int size();

    boolean isEmpty();

    void clear();
}
