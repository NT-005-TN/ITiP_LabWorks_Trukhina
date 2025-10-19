package Laba3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class HashTable<K, V> {
    private List<LinkedList<Entry<K, V>>> table;
    private int size;

    public HashTable() {
        table = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            table.add(null);
        }
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.size();
    }

    public void put(K key, V value) {
        int index = hash(key);

        if (table.get(index) == null) {
            table.set(index, new LinkedList<Entry<K, V>>());
        }

        for (Entry<K, V> entry : table.get(index)) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        table.get(index).add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table.get(index);

        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table.get(index);

        if (bucket != null) {
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> entry = bucket.get(i);
                if (entry.key.equals(key)) {
                    V removedValue = entry.value;
                    bucket.remove(i);
                    size--;
                    return removedValue;
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
