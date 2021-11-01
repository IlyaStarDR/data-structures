package com.company.datastructures.list.linkedlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @DisplayName("Test add null throws IllegalStateException")
    @Test
    public void testAddNullThrowIllegalStateException() {
        LinkedList linkedList = new LinkedList();
        assertThrows(IllegalStateException.class, () -> linkedList.add(null));
    }

    @DisplayName("Test add initial element to LinkedList")
    @Test
    public void testAddInitialElementSizeChange() {
        LinkedList linkedList = new LinkedList();
        assertTrue(linkedList.isEmpty(), "LinkedList is not empty");
        linkedList.add(10);
        linkedList.add(100);
        assertEquals(2, linkedList.size(), "LinkedList size differs");
        assertEquals(10, linkedList.get(0), "LinkedList expected and actual elements do not match");
        assertEquals(100, linkedList.get(1), "LinkedList expected and actual elements do not match");
    }

    @DisplayName("Test add and remove elements change LinkedList")
    @Test
    public void testAddAndRemoveElementsChangeList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(10);
        linkedList.add(100);
        assertEquals(2, linkedList.size(), "LinkedList size differs");
        Object actualRemovedByIndexOne = linkedList.remove(1);
        Object actualRemovedByIndexZero = linkedList.remove(0);
        assertEquals(100, actualRemovedByIndexOne, "Elements do not match");
        assertEquals(10, actualRemovedByIndexZero, "Elements do not match");
        assertTrue(linkedList.isEmpty(), "LinkedList is not empty");
    }

    @DisplayName("Test getting element from out of bound range")
    @Test
    void testGetElementByIndexThatIsOutOfBound() {
        LinkedList linkedList = new LinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.get(-1);
            linkedList.get(0);
            linkedList.get(1);
        });
    }

    @DisplayName("Test add null element by index throws IllegalStateException")
    @Test
    void testAddByIndexNullElementThrowsIllegalStateException() {
        LinkedList linkedList = new LinkedList();
        assertThrows(IllegalStateException.class, () -> {
            linkedList.add(null, 0);
            linkedList.add(null, 2);
        });
    }

    @DisplayName("Test add element by index out of bound range")
    @Test
    void testAddByIndexElementThatIsOutOfBound() {
        LinkedList linkedList = new LinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.add(1, -1);
            linkedList.add(5, 1);
        });
    }

    @DisplayName("Test add element by index to empty LinkedList")
    @Test
    void testAddByIndexElementToEmptyLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1, 0);
        assertEquals(1, linkedList.get(0), "LinkedList expected and actual elements do not match");
        assertEquals(1, linkedList.size(), "LinkedList size differs");
    }

    @DisplayName("Test add element by 0 index to not empty LinkedList")
    @Test
    void testAddByZeroIndexElementToNotEmptyLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(1, 0);
        assertEquals(1, linkedList.get(0), "LinkedList expected and actual elements do not match");
        assertEquals(2, linkedList.get(1), "LinkedList expected and actual elements do not match");
        assertEquals(3, linkedList.get(2), "LinkedList expected and actual elements do not match");
        assertEquals(3, linkedList.size(), "LinkedList size differs");
    }

    @DisplayName("Test add element by index to not empty LinkedList")
    @Test
    void testAddByIndexElementToNotEmptyLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(3);
        linkedList.add(2, 1);
        linkedList.add(4, 3);
        assertEquals(2, linkedList.get(1), "LinkedList expected and actual elements do not match");
        assertEquals(3, linkedList.get(2), "LinkedList expected and actual elements do not match");
        assertEquals(4, linkedList.get(3), "LinkedList expected and actual elements do not match");
        assertEquals(4, linkedList.size(), "LinkedList size differs");
    }

    @DisplayName("Test clear LinkedList changes size to 0")
    @Test
    void testClearChangesSizeToZero() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(3);
        linkedList.clear();
        assertEquals(0, linkedList.size(), "LinkedList size differs");
    }

    @DisplayName("Test remove element by index out of bound range")
    @Test
    void testRemoveElementByIndexThatIsOutOfBound() {
        LinkedList linkedList = new LinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.remove(-1);
            linkedList.remove(0);
            linkedList.remove(1);
        });
    }

    @DisplayName("Test remove element from head")
    @Test
    void testRemoveElementFromHead() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        Object actualRemovedByIndexOne = linkedList.remove(0);
        assertEquals(1, actualRemovedByIndexOne, "Elements do not match");
        assertEquals(0, linkedList.size(), "LinkedList size differs");
    }

    @DisplayName("Test remove elements by index")
    @Test
    void testRemoveElementsByIndex() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        Object actualRemovedByIndexTwo = linkedList.remove(2);
        Object actualRemovedByIndexOne = linkedList.remove(1);
        assertEquals(3, actualRemovedByIndexTwo, "Elements do not match");
        assertEquals(2, actualRemovedByIndexOne, "Elements do not match");
        assertEquals(1, linkedList.get(0), "LinkedList expected and actual elements do not match");
        assertEquals(1, linkedList.size(), "LinkedList size differs");
    }

    @DisplayName("Test set element by index out of bound range")
    @Test
    void testSetElementByIndexThatIsOutOfBound() {
        LinkedList linkedList = new LinkedList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            linkedList.set(2, -1);
            linkedList.set(5, 0);
            linkedList.set(10, 1);
        });
    }

    @DisplayName("Test set null throws IllegalStateException")
    @Test
    public void testSetNullThrowIllegalStateException() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(100);
        assertThrows(IllegalStateException.class, () -> linkedList.set(null, 0));
    }

    @DisplayName("Test set values to not empty LinkedList")
    @Test
    public void tesSetValuesToNotEmptyLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(100);
        linkedList.add(10, 1);
        Object setValueByZeroIndex = linkedList.set(200, 0);
        Object setValueByOneIndex = linkedList.set(20, 1);
        assertEquals(100, setValueByZeroIndex, "Elements do not match");
        assertEquals(10, setValueByOneIndex, "Elements do not match");
        assertEquals(200, linkedList.get(0), "LinkedList expected and actual elements do not match");
        assertEquals(20, linkedList.get(1), "LinkedList expected and actual elements do not match");
    }

    @DisplayName("Test index of null element throws IllegalStateException")
    @Test
    void testIndexOfNullValueThrowsIllegalStateException() {
        LinkedList linkedList = new LinkedList();
        assertThrows(IllegalStateException.class, () -> {
            linkedList.indexOf(null);
        });
    }

    @DisplayName("Test index of on empty LinkedList returns -1")
    @Test
    void testIndexOfEmptyOnLinkedListReturnsMinusOne() {
        LinkedList linkedList = new LinkedList();
        assertEquals(0, linkedList.size(), "Size differs");
        assertEquals(-1, linkedList.indexOf(2), "Indexes deffer");
    }

    @DisplayName("Test index of on not empty LinkedList returns index")
    @Test
    void testIndexOfOnNotEmptyLinkedListReturnsIndex() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(2);
        linkedList.add(4);
        assertEquals(0, linkedList.indexOf(2));
        assertEquals(1, linkedList.indexOf(4));
    }

    @DisplayName("Test index of returns -1 when element not found")
    @Test
    void testIndexOfOnNotEmptyLinkedListReturnsMinusOne() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        assertEquals(-1, linkedList.indexOf(-1));
        assertEquals(-1, linkedList.indexOf(7));
    }

    @DisplayName("Test last index of null element throws IllegalStateException")
    @Test
    void testLastIndexOfNullValueThrowsIllegalStateException() {
        LinkedList linkedList = new LinkedList();
        assertThrows(IllegalStateException.class, () -> {
            linkedList.lastIndexOf(null);
        });
    }

    @DisplayName("Test last index of on empty LinkedList returns -1")
    @Test
    void testLastIndexOfEmptyOnLinkedListReturnsMinusOne() {
        LinkedList linkedList = new LinkedList();
        assertEquals(0, linkedList.size(), "Size differs");
        assertEquals(-1, linkedList.lastIndexOf(2), "Indexes deffer");
    }

    @DisplayName("Test last index of on not empty LinkedList returns index")
    @Test
    void testLastIndexOfOnNotEmptyLinkedListReturnsIndex() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(2);
        linkedList.add(4);
        assertEquals(2, linkedList.lastIndexOf(2));
        assertEquals(3, linkedList.lastIndexOf(4));
    }

    @DisplayName("Test last index of returns -1 when element not found")
    @Test
    void testLastIndexOfOnNotEmptyLinkedListReturnsMinusOne() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        assertEquals(-1, linkedList.indexOf(-1));
        assertEquals(-1, linkedList.indexOf(7));
    }

    @DisplayName("Test not empty LinkedList contains element returns true")
    @Test
    void testNotEmptyLinkedListContainsElementReturnTrue() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1000);
        assertTrue(linkedList.contains(1000), "LinkedList doesn't contain such element");
    }

    @DisplayName("Test not empty LinkedList contains element returns false")
    @Test
    void testNotEmptyLinkedListContainsElementReturnFalse() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1000);
        assertFalse(linkedList.contains(100), "LinkedList contains such element");
    }

    @DisplayName("Test empty list contains element returns false")
    @Test
    void testEmptyLinkedListContainsElementReturnFalse() {
        LinkedList linkedList = new LinkedList();
        assertFalse(linkedList.contains(100), "LinkedList contains such element");
    }

    @DisplayName("Test not empty LinkedList contains null returns false")
    @Test
    void testNotEmptyLinkedListContainsNullThrowsIllegalStateException() {
        LinkedList linkedList = new LinkedList();
        assertThrows(IllegalStateException.class, () -> linkedList.contains(null));
    }
}