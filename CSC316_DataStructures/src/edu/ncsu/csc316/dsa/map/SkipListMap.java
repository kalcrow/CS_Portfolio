package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    /**
     * Method used to find a node with the given key in the list. If no node with that key exists, 
     * the method returns the node which it should be placed after.
     * Code copied from CSC316 Workshop 2 guide
     * 
     * @param key key to search for
     * @return the SkipListNode with the given key
     */
    private SkipListNode<K, V> lookUp(K key) {
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public V get(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        
        if (temp.getEntry() == null || !temp.getEntry().getKey().equals(key)) {
        	return null;
        }
        
        return temp.getEntry().getValue();
    }

    /**
     * Method used to add the given entry after/above the given nodes, then update references 
     * in surrounding nodes as necessary 
     * 
     * @param prev previous Node in the list
     * @param down lower Node in the list
     * @param entry entry to be stored in new Node
     * @return the new Node
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
    	
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
        
        newNode.setBelow(down);
        newNode.setPrevious(prev);
        
        if (prev != null) {
        	newNode.setNext(prev.getNext());
        	newNode.getPrevious().setNext( newNode);
        }
        if (newNode.getNext() != null) {
        	newNode.getNext().setPrevious(newNode);
        }
        if (down != null) {
        	down.setAbove(newNode);
        }
        return newNode;
    }

    @Override
    public V put(K key, V value) {
        SkipListNode<K, V> temp = lookUp(key);
        
        if (temp.getEntry() != null && temp.getEntry().getKey() == key) {
        	V originalValue = temp.getEntry().getValue();
        	while (temp != null) {
        		((MapEntry<K, V>)temp.getEntry()).setValue(value);
        		temp = temp.getAbove();
        	}
        	return originalValue;
        }
        
        SkipListNode<K, V> q = null;
        int currentLevel = -1;
        
        Boolean coinResult = true;
        
        while (coinResult) {
        	currentLevel++;
        	if (currentLevel >= height) {
        		height++;
        		SkipListNode<K, V> tail = start.getNext();
        		
        		start = insertAfterAbove(null, start, null);
        		
        		insertAfterAbove(start, tail, null);
        	}
        	
        	q = insertAfterAbove(temp, q, new MapEntry<K, V>(key, value));
        	
        	while (temp.getAbove() == null) {
        		temp = temp.getPrevious();
        	}
        	temp = temp.getAbove();
        	
        	coinResult = coinToss.nextBoolean();
        }
        	
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        
        if (temp.getEntry() == null || !temp.getEntry().getKey().equals(key)) {
        	return null;
        }
        V tempVal = temp.getEntry().getValue();

        while (temp != null) {
        	temp.getPrevious().setNext(temp.getNext());
        	temp.getNext().setPrevious(temp.getPrevious());
        	temp = temp.getAbove();
        }
        
        return tempVal;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
        }
        current = current.next;
        while (!isSentinel(current)) {
            set.add(current.getEntry());
            current = current.next;
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
    
    //public String toFullString() {
    //    StringBuilder sb = new StringBuilder("SkipListMap[\n");
    //    SkipListNode<K, V> cursor = start;
    //    SkipListNode<K, V> firstInList = start;
    //    while (cursor != null) {
    //        firstInList = cursor;
    //        sb.append("-INF -> ");
    //        cursor = cursor.next;
    //        while (cursor != null && !isSentinel(cursor)) {
    //            sb.append(cursor.getEntry().getKey() + " -> ");
    //            cursor = cursor.next;
    //        }
    //        sb.append("+INF\n");
    //        cursor = firstInList.below;
    //    }
    //    sb.append("]");
    //    return sb.toString();
    //}
    
    /**
     * This class defines the nodes used to store information in a skip list, which have references to the nodes above,
     * below, before and after themselves.
     * The skeleton for this code was copied from the CSC316 Workshop 5 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     * @param <K> Entry key type
     * @param <V> Entry value type
     */
    private static class SkipListNode<K, V> {
        /** entry stored by the node*/
        private Entry<K, V> entry;
        /** upper node in the list */
        private SkipListNode<K, V> above;
        /** lower node in the list */
        private SkipListNode<K, V> below;
        /** previous node in the list */
        private SkipListNode<K, V> prev;
        /** next node in the list */
        private SkipListNode<K, V> next;

        /**
         * Constructor which initializes the node with the given entry, and null values for all other attributes
         * 
         * @param entry entry to store
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }

        /**
         * Method that returns the node above this node. If there is no node above it, the method returns null
         * 
         * @return the node above
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }

        /**
         * Method that returns the entry stored within this node
         * 
         * @return the entry being stored
         */
        public Entry<K, V> getEntry() {
            return entry;
        }

        /**
         * Method that returns the next node in the list. If there is no next node, the method returns null.
         * 
         * @return the next node in the list
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }

        /**
         * Method that returns the previous node in the list. If there is no previous node, the method returns null.
         * 
         * @return the previous node in the list
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }

        /**
         * Method that sets the above attribute within this node to the given node.
         * 
         * @param up node to set
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }

        /**
         * Method that sets the below attribute in this node to the given node
         * 
         * @param down node to set
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }

        /**
         * Method to set the entry stored within this node
         * 
         * @param entry entry to set
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        /**
         * Method that sets the next attribute in this node to the given node
         * 
         * @param next node to set
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }

        /**
         * Method that sets the previous attribute in this node to the give node
         * 
         * @param prev previous node to set
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
}