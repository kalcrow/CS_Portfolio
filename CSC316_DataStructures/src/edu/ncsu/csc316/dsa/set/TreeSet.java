package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.RedBlackTreeMap;

/**
 * The TreeSet is implemented as a [REPLACE THIS WITH THE DATA STRUCTURE TYPE YOU CHOSE
 * TO USE IN YOUR CONSTRUCTOR]
 * data structure to support efficient set abstract data type behaviors.
 * 
 * Using a [DATA STRUCTURE TYPE YOU CHOSE] tree ensures worst-case runtime of
 * O(logn) for {@link Set#add}, {@link Set#remove}, and {@link Set#contains};
 * O(nlogn) worst-case runtime for {@link Set#addAll}, {@link Set#removeAll},
 * and {@link Set#retainAll}; and O(1) worst-case runtime for {@link Set#size}
 * and {@link Set#isEmpty}.
 * 
 * The TreeSet class is based on the implementation developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * Skeleton of code copied from CSC316 Workshop 10 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <E> the type of elements stored in the set
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {
    // Since we will delegate to an existing balanced search tree, the entries will
    // be ordered.
    // As a result, we must also restrict our tree set to use Comparable elements.
    
	/** Map used to store set values */
    private Map<E, E> tree;

    /**
     * Constructs a new TreeSet
     */
    public TreeSet() {
        tree = new RedBlackTreeMap<E, E>();
    }

    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public void add(E value) {
        if (contains(value)) {
        	return;
        }
        
        tree.put(value, value);
    }

    @Override
    public boolean contains(E value) {
    	return !(tree.get(value) == null);
    }

    @Override
    public E remove(E value) {
    	return tree.remove(value);
    }

    @Override
    public int size() {
    	return tree.size();
    }
}