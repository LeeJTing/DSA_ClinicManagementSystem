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
    
    public void put(K key, V value);
    
    public K[] getKey(V value);
    
    public V[] getValue(K key);
    
    public V[] getAllValues();
    
    public void remove(K key);
    
    public boolean containsKey(K key);
    
    public boolean containsValue(V value); 
    
    public boolean isEmpty();
        
    public int size();
        
}
