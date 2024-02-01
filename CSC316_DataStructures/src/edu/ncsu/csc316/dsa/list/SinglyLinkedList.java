package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * A skeleton of this code was copied from the CSC316 Workshop 2 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;

    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }
    
	@Override
	public void add(int index, E element) {

		checkIndexForAdd(index);
		
		if (index == size) {
			addLast(element);
			return;
		}
		
		LinkedListNode<E> temp = front;
		
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		
		LinkedListNode<E> newNode = new LinkedListNode<E>(element, temp.getNext());
		temp.next = newNode;
		
		size++;
		
	}

	@Override
	public E get(int index) {
		
		checkIndex(index);
		
		LinkedListNode<E> temp = front;
		
		for (int i = 0; i <= index; i++) {
			temp = temp.getNext();
		}
		
		return temp.getData();
		
		
	}

	@Override
	public E remove(int index) {
		
		checkIndex(index);
		
		LinkedListNode<E> temp = front;
		for (int i = 0; i < index; i++) {
			temp = temp.getNext();
		}
		E tempData = temp.getNext().getData();
		
		if (index == size - 1) {
			
			size--;
			 temp.setNext(null);
			 return tempData;
		}
		temp.setNext(temp.getNext().getNext());
		size--;
		return tempData;
	}

	@Override
	public E set(int index, E element) {
		
		checkIndex(index);
		LinkedListNode<E> temp = front;
		
		for (int i = 0; i <= index; i++) {
			temp = temp.getNext();
		}
		E tempData = temp.getData();
		temp.setData(element);
		
		return tempData;
	}

	@Override
	public int size() {
		return size;
	}

	/**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     * Code copied from CSC316 Workshop 2 guide
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.getData();
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     * Skeleton of code copied from CSC316 Workshop 2 guide
     */    
    @Override
    public void addLast(E element) {
      
    	LinkedListNode<E> newNode = new LinkedListNode<E>(element);
    	
    	if (size == 0) {
    		front.setNext(newNode);
    		tail = newNode;
    		size++;
    		return;
    	}
    	
    	
    	tail.setNext(newNode);
    	tail = newNode;
    	size++;
    }
	
    /**
     * This code was copied from the CSC316 Workshop 2 guide
     */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/**
	 * Class used as nodes in the singly-linked list. They each an element of the list and the next node in the list.
     * A skeleton of this code was copied from the CSC316 Workshop 2 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     * @param <E> type of data stored in the node
     */
    private static class LinkedListNode<E> {
        /** data stored in the LinkedListNode */
        private E data;
        /** next LinkedListNode in the SinglyLinkedList */
        private LinkedListNode<E> next;
        
        /**
         * Constructor that takes in and sets the data to be stored and automatically sets the next node as null
         * 
         * @param dataIn data to be stored
         */
        public LinkedListNode(E dataIn) {
        	setData(dataIn);
        	setNext(null);
        }

		/**
		 * Constructor that takes in and sets the data to be stored and the next node in the list
         * 
         * @param dataIn data to be stored
         * @param nextIn next node in the list
         */
        public LinkedListNode(E dataIn, LinkedListNode<E> nextIn) {
        	setData(dataIn);
        	setNext(nextIn);
        	
        }
        
        /**
         * Method that returns the data currently stored by the node
         * 
         * @return data stored in the node
         */
        public E getData() {
        	return data;
        }
        
        /**
         * Method to set the data in the node
         * 
         * @param data data to set
         */
        public void setData(E data) {
        	this.data = data;
        }
        
        /**
         * Method that returns the next node in the list
         * 
         * @return next node in the list
         */
        public LinkedListNode<E> getNext() {
        	return next;
        }
        
        /**
         * Method to set the next node in the list
         * 
         * @param next next to set
         */
        public void setNext(LinkedListNode<E> next) {
        	this.next = next;
        }
    }
    
    /**
     * Class that is used to iterate through a SinglyLinkedList, ensuring that it can be used in for-each loops
     * The skeleton of this code was copied from the CSC316 Workshop 2 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     */
    private class ElementIterator implements Iterator<E> {
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        
        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            
        	current = front;
        	
        }

        @Override
        public boolean hasNext() {
        	return current.getNext() != null;
        }

        @Override
        public E next() {
        	if (current.getNext() == null) {
        		throw new NoSuchElementException();
        	}
        	
        	current = current.getNext();
        	return current.getData();
        }
         
        @Override    
        public void remove() {
    	    // DO NOT CHANGE THIS METHOD
            throw new UnsupportedOperationException(
                "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
        }
    }
}