package com.andersen.hw.util;


import java.util.Arrays;

public class CustomArrayList<T> {
    private static int capacity = 10;
    private static int size = 0;
    private final double LOAD_COEFFICIENT = 0.8;
    private Object[] array;

    public CustomArrayList() {
        array = new Object[capacity];
    }

    public void put(Object object) {
        resize();
        array[size++] = object;
    }

    public Object get(int i) {
        if (i >= size) {
            throw new IndexOutOfBoundsException("Index:" + i + " is out of bounds");
        }
        return array[i];
    }

    public void delete(int i) {
        if (i >= size) {
            throw new IndexOutOfBoundsException("Index:" + i + " is out of bounds");
        }
        int numMoved = size - i - 1;
        if (numMoved > 0) {
            System.arraycopy(array, i + 1, array, i, numMoved);
        }
        array[--size] = null;
    }

    public int size() {
        return size;
    }

    private void resize() {
        if (size == capacity * LOAD_COEFFICIENT) {
            capacity = array.length * 2;
            array = Arrays.copyOf(array, array.length * 2);
        }
    }
}
