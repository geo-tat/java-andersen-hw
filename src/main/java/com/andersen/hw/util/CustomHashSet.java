package com.andersen.hw.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashSet<T> {
    private int capacity = 10;
    private int size = 0;
    private final double LOAD_COEFFICIENT = 0.8;
    private Object[] array;

    public CustomHashSet() {
        array = new Object[capacity];
    }

    private void resize() {
        capacity = array.length * 2;
        Object[] newArray = new Object[capacity];
        for (Object element : array) {
            if (element != null) {
                int newHash = Math.abs(element.hashCode() % capacity);
                newArray[newHash] = element;
            }
        }
        array = newArray;
    }

    public int size() {
        return size;
    }

    public boolean put(T object) {
        if (object == null) {
            if (contains(null)) {
                return false;
            }
        } else {

            if (size >= capacity * LOAD_COEFFICIENT) {
                resize();
            }
        }
        int hash = getHashIndex(object);
        if (array[hash] == null) {
            array[hash] = object;
            size++;
            return true;
        } else if (array[hash].equals(object)) {
            return false;
        } else {

            int originalHash = hash;
            while (array[hash] != null) {
                if (array[hash].equals(object)) {
                    return false;
                }
                hash = (hash + 1) % capacity;
                if (hash == originalHash) {
                    return false;
                }
            }
            array[hash] = object;
            size++;
            return true;
        }
    }

    private int getHashIndex(T object) {
        return Math.abs(object.hashCode() % capacity);
    }

    public boolean contains(T object) {
        int hash = getHashIndex(object);
        int originalHash = hash;
        while (array[hash] != null) {
            if (array[hash].equals(object)) {
                return true;
            }
            hash = (hash + 1) % capacity;
            if (hash == originalHash) {
                return false;
            }
        }
        return false;
    }

    public boolean delete(T object) {
        if (object == null) {
            return false;
        }
        int hash = getHashIndex(object);
        int tempHash = hash;
        while (array[hash] != null) {
            if (array[hash].equals(object)) {
                array[hash] = null;
                size--;

                hash = (hash + 1) % capacity;
                while (array[hash] != null) {
                    T rehashObject = (T) array[hash];
                    array[hash] = null;
                    size--;
                    put(rehashObject);
                    hash = (hash + 1) % capacity;
                }
                return true;
            }
            hash = (hash + 1) % capacity;
            if (hash == tempHash) {
                return false;
            }
        }
        return false;
    }


    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                while (index < array.length && array[index] == null) {
                    index++;
                }
                return index < array.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[index++];
            }
        };
    }
}
