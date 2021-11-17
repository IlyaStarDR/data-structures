package com.company.datastructures.list.arraylist;

import com.company.datastructures.list.AbstractListTest;
import com.company.datastructures.list.List;

public class ArrayListTest extends AbstractListTest {

    @Override
    protected List<Integer> getList() {
        return new ArrayList<>();
    }
}
