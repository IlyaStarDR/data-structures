package com.company.datastructures.helper;

public final class Objects {

    public static void throwIfIndexOutOfBound(int index, int size) {
        if (!(0 <= index  && index <= size)) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
