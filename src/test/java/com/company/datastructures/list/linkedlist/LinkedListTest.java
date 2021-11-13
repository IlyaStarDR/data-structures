package com.company.datastructures.list.linkedlist;

import com.company.datastructures.list.AbstractListTest;
import com.company.datastructures.list.List;

public class LinkedListTest extends AbstractListTest {

    @Override
    protected List<Integer> getList() {
        return new LinkedList<>();
    }
}
