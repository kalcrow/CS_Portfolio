package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * Abstract class that defines common functions for comparison sorters
 * All code copied from CSC 316 Workshop 1 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <E> type of object to be compared
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** comparator to determine how Objects are sorted */
	 private Comparator<E> comparator;
	    
	 /**
	  * Constructor which takes a comparator as parameter, which it then sets as the object's comparator by delegating
	  * to setComparator
	  * 
	  * @param comparator comparator to set
	  */
	    public AbstractComparisonSorter(Comparator<E> comparator) {
	        setComparator(comparator);
	    }
	    
	    /**
	     * Method that takes a comparator as parameter, which it sets as the object's comparator. If the input is null,
	     * this method defaults to the Natural Order comparator defined in the NaturalOrder inner class.
	     * 
	     * @param comparator comparator to set
	     */
	    private void setComparator(Comparator<E> comparator) {
	        if(comparator == null) {
	            this.comparator = new NaturalOrder();
	        } else {
	            this.comparator = comparator;
	        }
	    }   
	    
	    /**
	     * Inner private class which implements comparator, and is used to compare objects in term of natural order, 
	     * as defined by their compareTo method.
	     * 
	     * @author Dr. King
	     */
	    private class NaturalOrder implements Comparator<E> {
	        public int compare(E first, E second) {
	            return ((Comparable<E>) first).compareTo(second);
	        }
	    }
	    
	    /**
	     * Method which uses the object's comparator to compare the two input objects, and returns an int which is negative
	     * if the first input is less than the second, positive if the first input is greater than the second, and 0 if the
	     * two inputs are equal.
	     * 
	     * @param first first Object to compare
	     * @param second second Object to compare
	     * @return int reflecting comparison
	     */
	    public int compare(E first, E second) {
	        return comparator.compare(first,  second);
	    }
}
