package com.company.datastructures.helper;

public final class DataStructureHelper {
    public static void throwIfNull(Object value) {
        if (value == null) {
            throw new IllegalStateException("Null element is not supported");
        }
    }

    public static void throwIfIndexOutOfBound(int index, int size) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public static void throwIfEmpty(boolean isEmpty) {
        if (isEmpty) {
            throw new IllegalStateException("Collection is empty");
        }
    }
}
