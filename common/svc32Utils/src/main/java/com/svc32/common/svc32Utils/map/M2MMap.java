package com.svc32.common.svc32Utils.map;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class M2MMap<K, V> {
    private Map <K, Set<V>>m2mMap = new HashMap();

    public void put(K key, V value) {
        Set<V> set = m2mMap.get(key);
        if (set == null) {
            set = new HashSet<V>();
        }
        set.add(value);
        m2mMap.put(key, set);
    }

    public Set<V> get(K key) {
        return m2mMap.get(key);
    }

    public int getSize() {
        return m2mMap.size();
    }

    public int getSize(K key) {
        Set<V> set = m2mMap.get(key);
        return set == null ? 0 : set.size();
    }

    public Set<K> keySet() {
        return m2mMap.keySet();
    }

    public Set<V> valueSet() {
        Set<V> valueSet = new HashSet();
        Set<K> keySet = m2mMap.keySet();
        for (K key : keySet) {
            Set<V> vSet = m2mMap.get(key);
            valueSet.addAll(vSet);
        }

        return valueSet;
    }

    public Set<Map.Entry<K, Set<V>>> entrySet() {
        return m2mMap.entrySet();
    }

    public int getMaxValuesetCount() {
        int i = 0;
        for (K key : m2mMap.keySet()) {
            Set<V> vSet = m2mMap.get(key);
            int size = vSet.size();
            if (i < size)
                i = size;
        }
        return i;
    }

    public int getNoUniqueValuesetCount() {
        int i = 0;
        for (K key : m2mMap.keySet()) {
            Set<V> vSet = m2mMap.get(key);
            int size = vSet.size();
            if (size > 1)
                i++;
        }
        return i;
    }

    public void clear() {
        m2mMap.clear();
    }

}
