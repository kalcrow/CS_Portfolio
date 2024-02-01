package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * Skeleton for this code was copied from the CSC316 Workshop 5 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * MapEntry implements the Entry abstract data type.
     * 
     * @author Dr. King
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {

    	/** Key identifying the MapEntry object */
        private K key;
        /**Value contained in the MapEntry object */
        private V value;

        /**
         * Constructs a MapEntry with a provided key and a provided value
         * 
         * @param key   the key to store in the entry
         * @param value the value to store in the entry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * Set the key of the entry to the provided key
         * 
         * @param key the key to store in the entry
         */
        private void setKey(K key) {
            this.key = key;
        }

        /**
         * Set the value of the entry to the provided value
         * 
         * @param value the value to store in the entry
         */
        public void setValue(V value) {
            this.value = value;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>)this.key).compareTo(o.getKey());
        }       
    }
    
    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     * Skeleton for this class was copied from the CSC316 Workshop 5 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {

    	/** List used to store added entries  */
        private List<Entry<K, V>> list;

        /**
         * Constructor for EntryCollection, which initializes the list
         */
        public EntryCollection() {
            list = new SinglyLinkedList<Entry<K, V>>();
        }

        /**
         * Method that adds the given entry to the list
         * 
         * @param entry entry to add
         */
        public void add(Entry<K, V> entry) {
            list.addLast(entry);
        }

        /**
         * Method to create an iterator that will iterate through the entries in
         * the EntryCollection's list
         * 
         * @return the created iterator
         */
        public Iterator<Entry<K, V>> iterator() {
            return new EntryCollectionIterator();
        }

        /**
         * Iterator that is used to move through every entry in an EntryCollection.
         * Skeleton for this code was copied from the CSC316 Workshop 5 guide
         * 
         * @author Dr. King
         * @author Kal Corwin
         *
         */
        private class EntryCollectionIterator implements Iterator<Entry<K, V>> {

        	/** Iterator used to iterate through values in the list */
            private Iterator<Entry<K, V>> it;

            /**
             * Constructor that initializes the EntryCollectionIterator's iterator object
             */
            public EntryCollectionIterator() {
                it = list.iterator();
            }

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Entry<K, V> next() {
                return it.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }       

    /**
     * KeyIterator implements the {@link Iterator} interface to allow traversing
     * through the keys stored in the map
     * 
     * @author Dr. King
     *
     */
    protected class KeyIterator implements Iterator<K> {

    	/** Iterator used to iterate through values in the list */
        private Iterator<Entry<K, V>> it;
        
        public KeyIterator() {
            it = entrySet().iterator();
        }
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public K next() {
            return it.next().getKey();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }

    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map
     * Skeleton for this class was copied from the CSC316 Workshop 5 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     */
    protected class ValueIterator implements Iterator<V> {

    	/** Iterator used to iterate through values in the list  */
        private Iterator<Entry<K, V>> it;
        
        public ValueIterator() {
            it = entrySet().iterator();
        }
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public V next() {
            return it.next().getValue();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    /**
     * Inner class used to wrap a ValueIterator into an Iterable object
     * Skeleton for this class was copied from the CSC316 Workshop 5 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     */
    private class ValueIterable implements Iterable<V> {

    	@Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

}