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
//V extends Comparable<V>
public interface MapInterface<K, V> {
    
    /**
     * Add data into the ADT in key-value pairs
     * @param key the key to be stored
     * @param value the value to be associated with the key
     */
    public void put(K key, V value);
    
    /**
     * Search the key from the ADT based on the given value
     * @param value the value to search for
     * @return the key that maps to the given value
     */
    public K getKey(V value);
    
    /**
     * Retrieve the data from the ADT based on the given key
     * @param key the key search for
     * @return the value that map to the given key
     */
    public V getValue(K key);
    
    /**
     * Return all the values in insertion order
     * @return an array of values in the order they were inserted
     */
    public V[] getAllValues();
    
    /**
     * Remove the entry associated with the specified key from the map
     * @param key the key of the entry to be removed
     */
    public void remove(K key);
    
    /**
     * Check if the ADT contains entries with the given key or not
     * @param key the key search for
     * @return true if the key exist, else return false
     */
    public boolean containsKey(K key);
    
    /**
     * Check if the ADT contains entries with the given value or not
     * @param value the value search for
     * @return true if the value exist, else false
     */
    public boolean containsValue(V value); 
    
    /**
     * Determines whether the ADT is empty.
     * @return true if the ADT is empty, else false
     */
    public boolean isEmpty();
        
    /**
     * Find out the size of the ADT
     * @return the number of entries
     */
    public int size();
    
    /**
     * Return the last key from the ADT
     * @return return the last key of the ADT
     */
    public K getLastKey();
    
    /**
     * Returns an iterator that traverses all values stored in the map
     * @return an Iterator over the values of type V in this map
     */
    public Iterator<V> getIterator();
        
    /**
     * Sorting value of the ADT
     */
    public void sorting();
    
    /**
     * Clear the ADT
     */
    public void clear();
    
    /**
     * Return the value of the first key from the ADT
     * @return return the value of the first key of the ADT
     */
    public V getFront();
    
    /**
     * Remove the first note from the ADT
     * @return the First note value
     */
    public V removeFirst();
    
}
