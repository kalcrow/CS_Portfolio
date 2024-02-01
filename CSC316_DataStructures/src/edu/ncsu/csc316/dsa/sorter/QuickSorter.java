package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

import edu.ncsu.csc316.dsa.data.AbstractComparisonSorter;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * Skeleton for this code was copied from the CSC316 Workshop 4 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	 /**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

    /** Client's chosen PivotSelector */
    private PivotSelector selector;

	
	/**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }
	

	@Override
	public void sort(E[] data) {
		int size = 0;
		
		while (size < data.length && data[size] != null) {
			size++;
		}
		
		quickSort(data, 0, size - 1);
	}
	
	/**
	 * Method that calls itself recursively to sort the elements in the given array between 
	 * the two given indexes. QuickSort uses the helper method partition to acquire the pivotLocation
	 * 
	 * @param data array to sort
	 * @param low minimum index to sort
	 * @param high maximum index to sort
	 */
	private void quickSort(E[] data, int low, int high) {
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSort(data, low, pivotLocation - 1);
			quickSort(data, pivotLocation + 1, high);
		}
	}
	
	/**
	 * Helper method which uses the selector to select a pivot, which it then moves to the end of 
	 * the array before calling the helper method partitionHelper to begin sorting elements.
	 * 
	 * @param data array to sort
	 * @param low minimum index to sort
	 * @param high maximum index to sort
	 * @return index of the pivot value
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		
		E temp = data[pivotIndex];
		data[pivotIndex] = data[high];
		data[high] = temp;
		
		return partitionHelper(data, low, high); 
	}
	
	/**
	 * Helper method which sorts the values around the pivot, ending with the values in the array
	 * that are less than the pivot placed before the pivot, and values that are greater placed
	 * after the pivot.
	 * 
	 * @param data array to sort
	 * @param low minimum index to sort
	 * @param high maximum index to sort
	 * @return the index of the pivot value
	 */
	private int partitionHelper(E[] data, int low, int high) {
		
		E pivot = data[high];
		int separator = low;
		
		for (int j = low; j < high; j++) {
			if (compare(data[j], pivot) <= 0) {
				E temp = data[separator];
				data[separator] = data[j];
				data[j] = temp;
				
				separator++;
			}
		}
		
		E temp = data[separator];
		data[separator] = data[high];
		data[high] = temp;
		
		return separator;
	}

	/**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class FirstElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * This inner class chooses the last element of the array as the pivot value
     * 
     * @author Kal Corwin
     *
     */
    public static class LastElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return high;
		}
    	
    }
    
    /**
     * This inner class chooses the middle element of the array as the pivot value
     * 
     * @author Kal Corwin
     *
     */
    public static class MiddleElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return (high + low) / 2;
		}
    	
    }
    
    /**
     * This method chooses a random element in the list as the pivot value
     * 
     * @author Kal Corwin
     *
     */
    public static class RandomElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			Random rand = new Random();
			
			int random = rand.nextInt(high - low + 1);
			
			return low + random;
		}
    	
    }

}