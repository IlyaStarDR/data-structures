package com.company.datastructures.list;

public interface List {

    void add(Object value);

    void add(Object value, int index);

    Object remove(int index);

    Object get(int index);

    Object set(Object value, int index);

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(Object value);

    // [A, B, A, C] indexOf(A) -> 0
    // -1 if not exist
    int indexOf(Object value);

    // [A, B, A, C] lastIndexOf(A) -> 2
    int lastIndexOf(Object value);
}
