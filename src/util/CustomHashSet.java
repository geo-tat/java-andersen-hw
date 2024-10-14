package util;

import java.util.Arrays;

public class CustomHashSet<T> {
    private static int capacity = 16;
    private Object[] array;
    private final double LOAD_COEFFICIENT = 0.8;
    private int size;

    public CustomHashSet() {
        array = new Object[capacity];
    }

    public boolean add(T element) {
        resize();
        int index = getIndex(element);
        while (array[index] != null) {
            if (array[index].equals(element)) {
                return false;
            }
            index = (index + 1) % array.length;
        }
        array[index] = element;
        size++;
        return true;
    }

    private int getIndex(Object o) {
        int hashCode = o.hashCode();
        return hashCode & (array.length - 1);
    }

    private void resize() {
        if (size == capacity * LOAD_COEFFICIENT) {
            capacity = array.length * 2;
            array = Arrays.copyOf(array, array.length * 2);
        }
    }


}
