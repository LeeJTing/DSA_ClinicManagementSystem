/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author User
 */
public interface MapInterface<K, V> {

    /**
     * Add data into the ChainBucket in key-value pairs.
     *
     * @param key the key to be stored
     * @param value the value to be associated with the key
     */
    public void put(K key, V value);

    /**
     * Search the key from the ChainBucket based on the given value.
     *
     * @param value the value to search for
     * @return first key whose value matches the given value based on insertion
     * order is returned or returns null if no value is found.
     */
    public K getKey(V value);

    /**
     * Retrieve the data from the ChainBucket based on the given key.
     *
     * @param key the key search for
     * @return the value that is associated with the given key or return null if
     * no value is found.
     */
    public V getValue(K key);

    /**
     * Retrieve all the values in insertion order.
     *
     * @return a list of values orderly and the length equal to the total number
     * of key-value pairs in the ChainBucket.
     */
    public V[] getAllValues();

    /**
     * Return all the keys in insertion order.
     *
     * @return a list of keys orderly and the length equal to the total number
     * of key-value pairs in the ChainBucket.
     */
    public K[] getAllKeys();

    /**
     * Removes the entry associated with the specified key from the ChainBucket.
     *
     * @param key the key of the entry to be removed
     */
    public void remove(K key);

    /**
     * Check if the ChainBucket contains entries with the given key or not.
     *
     * @param key the key search for
     * @return true if the key exists, otherwise returns false.
     */
    public boolean containsKey(K key);

    /**
     * Check if the ChainBucket contains entries with the given value or not.
     *
     * @param value the value search for
     * @return true if the value exists, otherwise returns false.
     */
    public boolean containsValue(V value);

    /**
     * Determines whether the ChainBucket is empty.
     *
     * @return true if the ChainBucket is empty, else return false
     */
    public boolean isEmpty();

    /**
     * Find out the number of key-value mappings currently stored in
     * ChainBucket.
     *
     * @return the integer value of the total number of entries as ChainBucket
     * size or return 0 if the ChainBucket is empty.
     */
    public int size();

    /**
     * Get the last key from the ChainBucket according insertion order.
     *
     * @return the last insertion key or return null if the ChainBucket contains
     * no entries.
     */
    public K getLastKey();

    /**
     * Get the first key from the ChainBucket according insertion order.
     *
     * @return the first insertion key or return null if the ChainBucket
     * contains no entries.
     */
    public K getFrontKey();

    /**
     * Provides an iterator to traverse all values stored in the ChainBucket,
     * typically in insertion order.
     *
     * @return an Iterator over the values of type V in this ChainBucket
     */
    public Iterator<V> getIterator();

    /**
     * Sort all the values in the ChainBucket from large to small values.
     */
    public void sorting();

    /**
     * Sort all the keys in the ChainBucket from small to large values.
     */
    public void keyReverseSorting();

    /**
     * Removes all key-value pairs from the ChainBucket, leaving it empty.
     */
    public void clear();

    /**
     * Get the value of the first key from the ChainBucket.
     *
     * @return the value of the first key or return null if the ChainBucket
     * contains no entries.
     */
    public V getFront();

    /**
     * Get the value of the last key from the ChainBucket.
     *
     * @return the value of the last key or return null if the ChainBucket
     * contains no entries.
     */
    public V getLast();

    /**
     * Get and remove the value of the first key from the ChainBucket.
     *
     * @return the value of the first key or return null if the ChainBucket
     * contains no entries.
     */
    public V removeFirst();

    /**
     * Get and remove the value of the last key from the ChainBucket.
     *
     * @return Return the value of the last key or return null if the
     * ChainBucket contains no entries.
     */
    public V removeLast();

    /**
     * Creates a new map containing only the key-value pairs that are present in
     * both the current map and the specified map.
     *
     * @param map
     * @return a new Map containing all key-value pairs whose values also exist
     * in the specified map. Else, returns an empty map if no matches are found.
     */
    public MapInterface<K, V> intersect(MapInterface<K, V> map);

    /**
     * Creates a new map containing only the key-value pairs that have equal
     * value with the given value.
     *
     * @param value
     * @return a new Map containing all key-value pairs whose values are equal
     * to the specified value. Else, returns an empty map if no matches are
     * found.
     *
     */
    public MapInterface<K, V> groupBy(V value);

}
