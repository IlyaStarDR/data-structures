package com.company.datastructures.helper;

import java.util.LinkedList;

public final class Objects {

    public static void throwIfIndexOutOfBound(int index, int size) {
        if (!(0 <= index  && index <= size)) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
