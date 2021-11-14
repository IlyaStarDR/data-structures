package com.company.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractListTest {
    private final List<Integer> list;

    protected AbstractListTest() {
        list = getList();
    }

    @BeforeEach
    protected abstract List<Integer> getList();

    @DisplayName("Test adding item to the list")
    @Test
    void testAddItemToList() {
        list.add(1);
        int expectedIndex = list.size() - 1;
        assertEquals(expectedIndex, list.lastIndexOf(1), "Element is not added to the end");
    }

    @DisplayName("Test adding null item to the end should throw IllegalStateException")
    @Test
    void testAddItemNullThrowsIllegalStateException() {
        assertThrows(IllegalStateException.class, () -> {
            list.add(null);
        });
    }

    @DisplayName("Test adding and removing item to the list with resizing")
    @Test
    void testAddAndRemoveChangingSizeOfList() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size(), "Size differs");
        list.add(3);
        list.add(4);
        list.add(5);
        Object actualFirstRemoved = list.remove(0);
        Object actualSecondRemoved = list.remove(1);
        Object actualThirdRemoved = list.remove(2);
        list.remove(0);
        list.remove(0);
        assertEquals(1, actualFirstRemoved, "Elements do not match");
        assertEquals(3, actualSecondRemoved, "Elements do not match");
        assertEquals(5, actualThirdRemoved, "Elements do not match");
        assertEquals(0, list.size(), "Size differs");
        assertTrue(list.isEmpty(), "List is not empty");
    }

    @DisplayName("Test adding null element by index throws IllegalStateException")
    @Test
    void testAddByIndexNullElementThrowIllegalStateException() {
        list.add(2);
        assertThrows(IllegalStateException.class, () -> {
            list.add(null, 1);
        });
    }

    @DisplayName("Test adding element by index out of bound range")
    @Test
    void testAddByIndexOutOfBoundRange() {

        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(5, -1);
            list.add(10, 2);
        });
    }

    @DisplayName("Test adding element in index already with element")
    @Test
    void testAddElementInIndexAlreadyWithElement() {
        list.add(3);
        list.add(1, 0);
        list.add(2, 1);
        list.add(4, 3);
        assertEquals(1, list.get(0), "Indexes differ");
        assertEquals(2, list.get(1), "Indexes differ");
        assertEquals(3, list.get(2), "Indexes differ");
        assertEquals(4, list.get(3), "Indexes differ");
        assertEquals(4, list.size(), "Size differs");
    }

    @DisplayName("Test adding element to the end of the list by index equals to size")
    @Test
    void testAddElementToEndByIndexEqualsToSize() {
        list.add(3);
        list.add(10, 1);
        list.add(20, 2);
        assertEquals(3, list.get(0), "Indexes differ");
        assertEquals(10, list.get(1), "Indexes differ");
        assertEquals(20, list.get(2), "Indexes differ");
        assertEquals(3, list.size(), "Size differs");
    }

    @DisplayName("Test removing element from out of bound range")
    @Test
    void testRemoveFromIndexThatIsOutOfBound() {

        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
            list.remove(1);
        });
    }

    @DisplayName("Test removing element from empty list")
    @Test
    void testRemoveElementFromEmptyListThrowsIllegalStateException() {

        assertTrue(list.isEmpty(), "List is not empty");
        assertThrows(IllegalStateException.class, () -> list.remove(0));
    }

    @DisplayName("Test removing element from not empty list")
    @Test
    void testRemoveElementFromNotEmptyList() {

        list.add(2);
        list.add(5, 1);
        assertEquals(2, list.size(), "Size differs");
        Object actualFirstRemoved = list.remove(0);
        Object actualSecondRemoved = list.remove(0);
        assertEquals(2, actualFirstRemoved, "Elements do not match");
        assertEquals(5, actualSecondRemoved, "Elements do not match");
        assertTrue(list.isEmpty(), "List is not empty");
    }

    @DisplayName("Test getting element from out of bound range")
    @Test
    void testGetElementFromIndexThatIsOutOfBound() {

        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
            list.get(1);
        });
    }

    @DisplayName("Test getting element from empty list")
    @Test
    void testGetElementFromEmptyListThrowsIllegalStateException() {

        assertThrows(IllegalStateException.class, () -> {
            list.get(0);
        });
    }

    @DisplayName("Test getting element from not empty list")
    @Test
    void testGetElementFromNotEmptyList() {

        list.add(2);
        Object expectedElement = list.get(0);
        assertEquals(2, expectedElement, "Elements do not match");
    }

    @DisplayName("Test not empty list contains element returns true")
    @Test
    void testNotEmptyListContainsElementReturnTrue() {

        list.add(1000);
        assertTrue(list.contains(1000), "List doesn't contain such element");
    }

    @DisplayName("Test not empty list contains element returns false")
    @Test
    void testNotEmptyListContainsElementReturnFalse() {

        list.add(1000);
        assertFalse(list.contains(100), "List contains such element");
    }

    @DisplayName("Test empty list contains element returns false")
    @Test
    void testEmptyListContainsElementReturnFalse() {

        assertFalse(list.contains(100), "List contains such element");
    }

    @DisplayName("Test not empty list contains null returns throws")
    @Test
    void testNotEmptyListContainsNullThrowsIllegalStateException() {

        list.add(5);
        assertThrows(IllegalStateException.class, () -> list.contains(null));
    }

    @DisplayName("Test after clear list size should be 0")
    @Test
    void testClearListSizeShouldBeZero() {

        list.add(1);
        list.add(1);
        list.add(1);
        assertEquals(3, list.size(), "Size differs");
        list.clear();
        assertTrue(list.isEmpty(), "List is not empty");
    }


    @DisplayName("Test set element in empty list throws IllegalStateException")
    @Test
    void testSetElementEmptyListThrowsIllegalStateException() {
        assertThrows(IllegalStateException.class, () -> {
            list.set(2, 0);
        });
    }

    @DisplayName("Test set null element in list throws IllegalStateException")
    @Test
    void testSetNullElementThrowsIllegalStateException() {

        list.add(2);
        assertThrows(IllegalStateException.class, () -> {
            list.set(null, 0);
        });

    }

    @DisplayName("Test set index out of bound in list throws IndexOutOfBoundsException")
    @Test
    void testSetOutOfBoundIndexThrowsIndexOutOfBoundsException() {

        list.add(22);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set(2, -1);
            list.set(2, 1);
        });
    }

    @DisplayName("Test set new value by index in list")
    @Test
    void testSetNewValueByIndexInList() {

        list.add(100);
        Object toBeSet = list.set(10, 0);
        assertEquals(100, toBeSet, "Elements differ");
        assertEquals(1, list.size(), "Size differs");
        assertEquals(10, list.get(0), "Elements differ");
    }

    @DisplayName("Test index of null element throws IllegalStateException")
    @Test
    void testIndexOfNullValueThrowsIllegalStateException() {

        assertThrows(IllegalStateException.class, () -> {
            list.indexOf(null);
        });
    }

    @DisplayName("Test index of on empty list returns -1")
    @Test
    void testIndexOfEmptyOnListReturnsMinusOne() {

        assertEquals(0, list.size(), "Size differs");
        assertEquals(-1, list.indexOf(2), "Indexes deffer");
    }


    @DisplayName("Test index of on not empty list returns index")
    @Test
    void testIndexOfOnNotEmptyListReturnsIndex() {

        list.add(2);
        list.add(4);
        list.add(2);
        list.add(4);
        assertEquals(0, list.indexOf(2));
        assertEquals(1, list.indexOf(4));
    }

    @DisplayName("Test index of returns -1 when element not found")
    @Test
    void testIndexOfOnNotEmptyListReturnsMinusOne() {

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(-1, list.indexOf(-1));
        assertEquals(-1, list.indexOf(7));
    }

    @DisplayName("Test last index of null element throws IllegalStateException")
    @Test
    void testLastIndexOfNullValueThrowsIllegalStateException() {

        assertThrows(IllegalStateException.class, () -> {
            list.lastIndexOf(null);
        });
    }

    @DisplayName("Test last index of on empty list returns -1")
    @Test
    void testLastIndexOfEmptyOnListReturnsMinusOne() {

        assertEquals(0, list.size(), "Size differs");
        assertEquals(-1, list.lastIndexOf(2), "Indexes deffer");
    }

    @DisplayName("Test last index of on not empty list returns index")
    @Test
    void testLastIndexOfOnNotEmptyListReturnsIndex() {

        list.add(2);
        list.add(4);
        list.add(2);
        list.add(4);
        assertEquals(2, list.lastIndexOf(2));
        assertEquals(3, list.lastIndexOf(4));
    }

    @DisplayName("Test last index of returns -1 when element not found")
    @Test
    void testLastIndexOfOnNotEmptyListReturnsMinusOne() {

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(-1, list.indexOf(-1));
        assertEquals(-1, list.indexOf(7));
    }

    @DisplayName("Iterator hasNext on empty list returns false")
    @Test
    void testIteratorHasNextReturnFalseOnEmptyList() {
        Iterator<Integer> iterator = list.iterator();
        assertFalse(iterator.hasNext(), "List has next value");
    }

    @DisplayName("Iterator hasNext returns true on not empty list")
    @Test
    void testIteratorHasNextReturnTrueOnNotEmptyList() {
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext(), "List has not next value");
    }

    @DisplayName("Iterator next throws on empty list")
    @Test
    void testIteratorNextThrowsOnEmptyList() {
        Iterator<Integer> iterator = list.iterator();
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    @DisplayName("Iterator next returns element on not empty list")
    @Test
    void testIteratorNextReturnsElementOnNotEmptyList() {
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext(), "List has not next value");
        assertEquals(list.get(0), iterator.next(), "Elements differ");
        assertTrue(iterator.hasNext(), "List has not next value");
        assertEquals(list.get(1), iterator.next(), "Elements differ");
        assertFalse(iterator.hasNext(), "List has next value");
    }

    @DisplayName("Iterator remove throws if next method not called")
    @Test
    void testIteratorRemoveThrowsIfNextMethodNotCalled() {
        Iterator<Integer> iterator = list.iterator();
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

    @DisplayName("Iterator remove element if next method called")
    @Test
    void testIteratorRemoveElementIfNextMethodCalled() {
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext(), "List has not next value");
        iterator.next();
        iterator.remove();
        assertEquals(1, list.size(), "Size differs");
        assertTrue(iterator.hasNext(), "List has not next value");
        iterator.next();
        iterator.remove();
        assertEquals(0, list.size(), "Size differs");
        assertFalse(iterator.hasNext(), "List has next value");
    }

    @DisplayName("Iterator remove throws if method called twice")
    @Test
    void testIteratorRemoveThrowsIfRemoveCalledTwice() {
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext(), "List has not next value");
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

}
