package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.data.AbstractComparisonSorter;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * A skeleton of this code was copied from the CSC316 Workshop 4 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

	@Override
	public void sort(E[] data) {
		
		if (data.length < 2) {
			return;
		}
		
		int mid = data.length / 2;
		
		@SuppressWarnings("unchecked")
		E[] left = (E[])(new Comparable[mid]);
		for (int i = 0; i < mid; i++) {
			left[i] = data[i];
		}
		
		@SuppressWarnings("unchecked")
		E[] right = (E[])(new Comparable[data.length - mid]);
		for (int i = mid; i < data.length; i++) {
			right[i - mid] = data[i];
		}
		
		sort(left);
		sort(right);
		
		merge(left, right, data);
		
	}
	
	/**
	 * Method used to merge two given sorted arrays, combining them in sorted order in 
	 * the third given array. This method is a helper method for sort().
	 * 
	 * @param left left array to combine
	 * @param right right array to combine
	 * @param data array to combine other arrays into
	 */
	private void merge(E[] left, E[] right, E[] data) {
	
		int leftIndex = 0;
		int rightIndex = 0;
		
		while (leftIndex + rightIndex < data.length) {
			if (rightIndex == right.length || leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0  ) {
				data[leftIndex + rightIndex] = left[leftIndex];
				leftIndex++;
			}
		
			else {
				data[leftIndex + rightIndex] = right[rightIndex];
				rightIndex++;
			}
		}
	}

}