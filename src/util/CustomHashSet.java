package util;

import java.util.HashMap;
import java.util.Set;

public class CustomHashSet<T> {
    private final HashMap<T, Boolean> map;
    private static final Boolean PRESENT = Boolean.TRUE;

    public CustomHashSet() {
        map = new HashMap<>();
    }

    public void put(T key) {
        map.put(key, PRESENT);
    }

    public boolean contains(T key) {
        return map.containsKey(key);
    }

    public void delete(T key) {
        map.remove(key);
    }

    public Set<T> iterate() {
        return map.keySet();
    }

    public int size() {
        return map.size();
    }
}
