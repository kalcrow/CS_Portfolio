package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * The skeleton for this code was copied from the CSC316 Workshop 5 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** list to store entries in  */
    private PositionalList<Entry<K, V>> list;
    
    /** 
     * Constructor that initializes the list attribute
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    private Position<Entry<K, V>> lookUp(K key)
    {
    	Position<Entry<K, V>> temp = list.first();
    	for (int i = 0; i < list.size(); i++) {
    		if (temp.getElement().getKey().equals(key)) {
    			return temp;
    		}
    		temp = list.after(temp);
    	}
    	return null;
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        
        if (p == null) {
        	return null;
        }
        
        moveToFront(p);
        return p.getElement().getValue();
    }
    
    private void moveToFront(Position<Entry<K, V>> position) {
    	
    	list.addFirst(list.remove(position));
    }

    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        
    	if (p == null) {
    		Entry<K, V> e;
    		e = new MapEntry<K, V>(key, value);
    		list.addFirst(e);
    		return null;
    	}
    	moveToFront(p);
    	V temp = p.getElement().getValue();
    	((MapEntry<K, V>) p.getElement()).setValue(value);
        return temp;
    }
    
    @Override
    public V remove(K key) {
       
    	//problem with p being null when its not supposed to be.
    	Position<Entry<K, V>> p = lookUp(key);
       
       if (p == null) {
    	   return null;
       }
       
       list.remove(p);
       return p.getElement().getValue();
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}