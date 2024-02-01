package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;
//import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * Skeleton for this code was copied from CSC316 Workshop 8 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {
	/** Array of TableEntries used to store data */
    private TableEntry<K, V>[] table;
    /** Numeber of items in the HashMap */
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        
    	EntryCollection collection = new EntryCollection();
    	
    	for (TableEntry<K, V> entry : table) {
    		if (!(entry == null) && !entry.isDeleted()) {
    			collection.add(entry);
    		}
    	}	
    	
    	return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return table[index] == null || table[index].isDeleted();
    }

    @Override
    public V bucketGet(int hash, K key) {
    	int index = findBucket(hash, key);
    	
    	
    	if (index < 0) {
    		return null;
    	}
    	
    	return table[index].getValue();
    }

    @Override
    public V bucketPut(int hash, K key, V value) {

    	int index = findBucket(hash, key);
    
    	if (index < 0) {
    		index = -(index + 1);
    		
    		//add element at index
    		table[index] = new TableEntry<K, V>(key, value);
    		size++;
    		return null;
    	} 
    	
    	V val = table[index].getValue();
    	
    	if (table[index].isDeleted()) {
    		table[index].setDeleted(false);
    		size++;
    	}
    	
    	//Update with new value
    	table[index].setValue(value);
    	return val;
    }

    private int findBucket(int index, K key) {
    	int avail = -1;
    	int j = index;
    	
    	if (isAvailable(j)) {
			if (avail == -1) {
				avail = j;
			}
			
			if (table[j] == null) {
				return -(avail + 1);
			}
			
		} else if (table[j].getKey().equals(key)) {
			return j;
		}
		
		j = (j + 1) % table.length;
		
    	while (j != index) {
    		if (isAvailable(j)) {
    			if (avail == -1) {
    				avail = j;
    			}
    			if (table[j] == null) {
    				return -(avail + 1);
    			}
    		} else if (table[j].getKey().equals(key)) {
    			return j;
    		}
    		
    		j = (j + 1) % table.length;
    	}
    	
    	return -(avail + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) {
    	
    	int index = findBucket(hash, key);
    	
    	if (index < 0) {
    		return null;
    	}
    	
    	table[index].setDeleted(true);
    	size--;
    	return table[index].getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	/** true if the TableEntry object has been deleted, false otherwise */
        private boolean isDeleted;

        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}