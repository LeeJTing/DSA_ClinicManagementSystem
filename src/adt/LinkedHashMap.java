/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author User
 */
public class LinkedHashMap<K, V> implements MapInterface<K, V> {

    private Entry<K, V>[] entries;
    private int size;
    private int capacity = 16;
    private Entry<K, V> head, tail;

    private static class Entry<K, V> {

        private K key;
        private V value;
        private Entry<K, V> before;
        private Entry<K, V> after;
        private Entry<K, V> next;
        private int hash;

        public Entry() {
        }

        public Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public Entry(K key, V value, Entry<K, V> before, Entry<K, V> after, Entry<K, V> next, int hash) {
            this.key = key;
            this.value = value;
            this.before = before;
            this.after = after;
            this.next = next;
            this.hash = hash;
        }
    }

    @Override
    public void put(K key, V value) {
        int hashValue = hashing(key); // passing the key to return an integer
        Entry<K, V> newEntry = new Entry(key, value, hashValue); // create a newEntry
        Entry<K, V> current = entries[hashValue]; // pass the hashValue to track the current entries

        // Insert into entries
        if (current == null) {
            entries[hashValue] = newEntry;
        } else {
            Entry<K, V> prev = null;
            while (current != null) { // if current bucket has values
                if (current.key.equals(key)) {
                    current.value = value; // replace current value
                    return;
                }
                prev = current;
                current = current.next; // move to the next entry in the entries chain
            }
            prev.next = newEntry; // append new entry at end of the chain
        }

        updateHeadAndTail(newEntry);
        size++;
    }

    private void updateHeadAndTail(Entry<K, V> newEntry) {
        // Insert into linked list (order tracking)
        if (head == null) {
            head = tail = newEntry;
        } else {
            tail.after = newEntry;
            newEntry.before = tail;
            tail = newEntry;
        }
    }

    private int hashing(K key) {
        return Math.abs(key.hashCode()) % capacity; // return a positive integer to indicate the index
    }

    @Override
    public K getKey(V value) {
        //linear searching from beginning is there have any values match
        Entry<K, V> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return current.key;
            }
            current = current.after;
        }
        return null;
    }

    @Override
    public V getValue(K key) {
        //directly point to the value in the entries based on the hash value (key -> index) and keep looping
        int index = hashing(key);
        Entry<K, V> current = entries[index];

        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public V[] getAllValues() {
        //search from beginning and store one value by one value become a generic type to return 
        V[] values = (V[]) new Object[size];
        Entry<K, V> current = head;

        int i = 0;
        if (!isEmpty()) {
            while (current != null) {
                values[i] = current.value;
                current = current.after;
                i++;
            }
            return values;
        }
        return null;
    }

    @Override
    public void remove(K key) {
        int index = hashing(key);
        Entry<K, V> current = entries[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (head == current) {
                    head = current.after;
                    if (head != null) {
                        head.before = null;
                    }
                } else if (tail == current) {
                    tail = current.before;
                    if (tail != null) {
                        tail.after = null;
                    }
                } else {
                    current.before.after = current.after;
                    current.after.before = current.before;
                }

                if (previous != null) {
                    previous.next = current.next;
                } else {
                    entries[index] = current.next;
                }
                size--;
            }
            previous = current;
            current = current.next;
        }
    }

    @Override
    public boolean containsKey(K key) {
        int index = hashing(key);
        Entry<K, V> current = entries[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        Entry<K, V> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.after;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
