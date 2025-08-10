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
    private final int CAPACITY = 16;
    private Entry<K, V> head, tail;

    public LinkedHashMap() {
        entries = new Entry[CAPACITY];
        size = 0;
        head = null;
        tail = null;
    }

    private static class Entry<K, V> {

        private K key;
        private V value;
        private Entry<K, V> before;
        private Entry<K, V> after;
        private Entry<K, V> next;

        public Entry() {
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Entry(K key, V value, Entry<K, V> before, Entry<K, V> after, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.before = before;
            this.after = after;
            this.next = next;
        }
    }

    private class LinkedHashMapValueIterator implements Iterator<V> {

        private Entry<K, V> currentHead = head;
        private Entry<K, V> currentTail = tail;

        @Override
        public boolean hasNext() {
            return currentHead != null;
        }

        @Override
        public V next() {
            V next = null;
            if (hasNext()) {
                next = currentHead.value;
                currentHead = currentHead.after;
            }
            return next;
        }

        public boolean hasPrev() {
            return currentTail != null;
        }

        public V prev() {
            V prev = null;
            if (hasPrev()) {
                prev = currentTail.value;
                currentTail = currentHead.before;
            }
            return prev;
        }
    }

    public Iterator<V> getIterator() {
        return new LinkedHashMapValueIterator();
    }

    @Override
    public void put(K key, V value) {
        int hashValue = hashing(key); // passing the key to return an integer
        Entry<K, V> newEntry = new Entry(key, value); // create a newEntry
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
        return Math.abs(key.hashCode()) % CAPACITY; // return a positive integer to indicate the index
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
    public K getLastKey() {
        return (tail != null) ? tail.key : null;
    }

    @Override
    public V getFront() {
        return (head != null) ? head.value : null;
    }

    @Override
    public V getValue(K key) {
        //directly point to the value in the entries based on the hash value (key -> index) and keep looping
        int index = hashing(key);
        Entry<K, V> current = entries[index];

        while (current != null) {
            if (current.key.equals(key)) {
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
    public K[] getAllKeys() {
        K[] keys = (K[]) new Object[size];
        Entry<K, V> current = head;
        int i = 0;
        if (!isEmpty()) {
            while (current != null) {
                keys[i] = current.key;
                current = current.after;
                i++;
            }
            return keys;
        }
        return null;
    }

    @Override
    public V removeFirst() {
        V value = null;
        if (head != null) {
            value = head.value;
            remove(head.key);
        }
        return value;
    }

    @Override
    public void remove(K key) {
        int index = hashing(key);
        Entry<K, V> current = entries[index];
        Entry<K, V> previous = null;

        if (key == null) {
            return; // null safety
        }

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

                // Remove from hash table chain
                if (previous != null) {
                    previous.next = current.next;
                } else {
                    entries[index] = current.next;
                }
                size--;
                return;  // Stop after removal
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

    @Override
    public void sorting() {

        if (isEmpty() || size == 1) {
            return;
        } else {
            // find the largest one
            int cSize = size;
            Entry<K, V>[] largeToSmall = new Entry[cSize];
            Entry<K, V> current, largest;

            for (int i = 0; i < cSize; i++) {
                // largest to store the largest value, current is a pointer
                largest = head;
                current = head;
                while (current != null) {
                    if (largest.value instanceof Comparable && current.value instanceof Comparable) {
                        Comparable<V> largestCmp = (Comparable<V>) largest.value;
                        if (largestCmp.compareTo(current.value) < 0) {
                            largest = current;
                        }
                    }
                    current = current.after;
                }
                // store the largest entry inside the array
                largeToSmall[i] = largest;
                // remove stored entry
                remove(largest.key);
            }

            // put back the note from largest to smallest
            for (int i = 0; i < cSize; i++) {
                if (largeToSmall[i] != null) {
                    put(largeToSmall[i].key, largeToSmall[i].value);
                }
            }
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        entries = (Entry<K, V>[]) new Entry[CAPACITY];
        size = 0;
    }

    @Override
    public void addFirst(K key, V value) {
        int hashValue = hashing(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        // when the ADT is empty set the head and tail as newEntry and set the entries[hashValue] = newEntries
        if (isEmpty()) {
            tail = newEntry;

        } else {
            // if the key already exist, will remove it first and move it to the front
            if (containsKey(key)) {
                remove(key);
            }

            // newEntry will be head and it linked to the previous head and previous head link to head
            head.before = newEntry;
            newEntry.after = head;

            // the first note of the bucket
            Entry<K, V> current = entries[hashValue];

            // have note inside the bucket
            if (current != null) {
                newEntry.next = current;
            }
            // no any note inside the bucket
        }
        head = newEntry;
        entries[hashValue] = newEntry;
        size++;
    }

    @Override
    public MapInterface<K, V> intersect(MapInterface<K, V> map) {
        MapInterface<K, V> intersectMap = new LinkedHashMap<>();
        if (isEmpty()) {
            return null;
        } else {
            Entry<K, V> current = head;
            Object[] otherMapValues = map.getAllValues();
            for (int i = 0; i < size; i++) {
                if (current != null) {
                    boolean found = false;
                    for (int j = 0;!found && j<otherMapValues.length;j++) {
                        V vl = (V) otherMapValues[j];
                        if (current.value.equals(vl)) {
                            intersectMap.put(current.key, current.value);
                            otherMapValues[j] = null;
                            found = true;
                        }

                    }
                    current = current.after;
                }
            }
        }

        return intersectMap;
    }
    
//    public static void main(String[] args) {
//        MapInterface<Integer, String> str1 = new LinkedHashMap<>();
//        MapInterface<Integer, String> str2 = new LinkedHashMap<>();
//        
//        str1.put(1, "I");
//        str1.put(5, "I");
//        str1.put(2, "Love");
//        str1.put(3, "You");
//        str1.put(4, "Hee");
//        
//        str2.put(1, "I");
//        str2.put(5, "I");
//        str2.put(2, "Love");
//        str2.put(3, "You");
//        str2.put(4, "Hellow");
//        MapInterface<Integer, String> str3 = str1.intersect(str2);
//        
//        Object[] obj = str3.getAllValues();
//        for(Object o: obj){
//            String str = (String) o;
//            System.out.println(str);
//            
//        }
//    }
}
