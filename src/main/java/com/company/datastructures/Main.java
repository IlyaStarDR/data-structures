package com.company.datastructures;

import com.company.datastructures.list.linkedlist.LinkedList;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
