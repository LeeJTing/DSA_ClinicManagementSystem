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
    
    // Add data into the ADT in key-value pairs
    public void put(K key, V value);
    
    // Search the key from the ADT based on the given value
    public K getKey(V value);
    
    // Retrieve the data from the ADT based on the given key
    public V getValue(K key);
    
    // Return all the values in insertion order
    public V[] getAllValues();
    
    // Remove the entry associated with the specified key from the map
    public void remove(K key);
    
    // Check if the ADT contains entries with the given key or not
    public boolean containsKey(K key);
    
    // Check if the ADT contains entries with the given value or not
    public boolean containsValue(V value); 
    
    // Determines whether the ADT is empty.
    public boolean isEmpty();
        
    // Find out the size of the ADT
    public int size();
    
    // Return the last key from the ADT
    public K getLastKey();
        
}
