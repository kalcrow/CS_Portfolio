package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }
    
    /**
     * Class defining the nodes used for PositionalLinkedList. They contain the data at their position,
     * the next node in the list, and the previous node in the list.
     * Skeleton for this code copied from CSC316 Workshop 2 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     * @param <E> type of data stored in the nodes
     */
    private static class PositionalNode<E> implements Position<E> {
    	/** element stored in the node */
        private E element;
        /** next node in the list */
        private PositionalNode<E> next;
        /** previous node in the list */
        private PositionalNode<E> previous;

        /**
         * Constructor that takes the valued to be stored, but no next or previous node
         * 
         * @param value value to be stored
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * Constructor that takes the value to be stored and the next node in the list, but
         * no previous node.
         * 
         * @param value value to be stored
         * @param next next node in the list
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * Constructor that takes the value to be stored, the next node in the list, and the 
         * previous node in the list.
         * 
         * @param value value to be stored
         * @param next next node in the list
         * @param prev previous node in the list
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * Method to set the previous node in the list
         * 
         * @param prev previous node to set
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Method to return the previous node in the list
         * 
         * @return the previous node in the list
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Method to set the next node in the lsit
         * 
         * @param next next node to set
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * Method to get the next node in the list
         * 
         * @return the next PositionalNode
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        /**
         * Method to return the element stored by the node
         * 
         * @return the node's element
         */
        public E getElement() {
            return element;
        }
        
        /**
         * Method to set the element stored by the node
         * 
         * @param element element to set
         */
        public void setElement(E element) {
            this.element = element;
        }
    }

    /**
     * Code copied from CSC316 Workshop 2 guide
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		
		PositionalNode<E> newNode = validate(p);
		return addBetween(element, newNode.getNext(), newNode);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		
		PositionalNode<E> newNode = validate(p);
		return addBetween(element, newNode, newNode.getPrevious());
	}

	@Override
	public Position<E> addFirst(E element) {

		return addBetween(element, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E element) {
		
		return addBetween(element, tail, tail.getPrevious());
	}

	@Override
	public Position<E> after(Position<E> p) {
	
		PositionalNode<E> pNode = validate(p);
		
		if (pNode.getNext().getElement() == null) {
			return null;
		}
		return pNode.getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {

		PositionalNode<E> pNode = validate(p);
		
		if (pNode.getPrevious().getElement() == null) {
			return null;
		}
		return pNode.getPrevious();
	}

	@Override
	public Position<E> first() {
		
		if (front.getNext().getElement() == null) {
			return null;
		}
		return front.getNext();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		if (tail.getPrevious().getElement() == null) {
			return null;
		}
		return tail.getPrevious();
	}

	@Override
	public Iterable<Position<E>> positions() {
		
		return new PositionIterable();
		
	}

	@Override
	public E remove(Position<E> p) {
		
		PositionalNode<E> nodeRemove = validate(p);
		
		nodeRemove.getNext().setPrevious(nodeRemove.getPrevious());
		nodeRemove.getPrevious().setNext(nodeRemove.getNext());
		
		size--;
		
		return nodeRemove.getElement();
	}

	@Override
	public E set(Position<E> p, E element) {
		
		PositionalNode<E> pNode = validate(p);
		
		E temp = pNode.getElement();
		pNode.setElement(element);
		
		return temp;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Helper method that assists with adding elements to the PositionalLinkedList. Adds the new node with the given element
	 * between the given previous and next nodes, and returns the added node.
	 * Skeleton copied from CSC316 Workshop 2 guide
	 * 
	 * @param element element of node to add
	 * @param next node after the new node
	 * @param prev node before the new node
	 * @return the added node
	 */
	private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
       
		PositionalNode<E> newNode = new PositionalNode<E>(element, next, prev);
		
		next.setPrevious(newNode);
		prev.setNext(newNode);
		
		size++;
		
		return newNode;
    }
	
	  /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
     * Class that is used to iterate through positions in a PositionalLinkedList, 
     * ensuring that it can be used in for-each loops
     * Skeleton of this code copied from CSC316 Workshop 2 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     */
    private class PositionIterator implements Iterator<Position<E>> {
    	/** current position of the iterator */
        private Position<E> current;
        /** boolean reflecting if it is possible to use .remove() */
        private boolean removeOK;

        /**
         * Constructor that initializes the iterator at the beginning of the list and
         * sets removeOK to false
         */
        public PositionIterator() {
        	
        	current = front;
        	removeOK = false;
        }

        @Override
        public boolean hasNext() {

        	PositionalNode<E> newNode = validate(current);
        	newNode = newNode.next;
        	
        	return newNode.getElement() != null;
        }

        @Override
        public Position<E> next() {
       
        	if (!hasNext()) {
        		throw new NoSuchElementException();
        	}
        	
        	PositionalNode<E> p = validate(current);
        	current = p.next;
        	removeOK = true;
        	return current;
        }

        @Override
        public void remove() {
           
        	if (!removeOK) {
        		throw new IllegalStateException();
        	}
        	PositionalLinkedList.this.remove(current);
        	removeOK = false;
        }
    }
    
    /**
     * Class that is used to iterate through elements in a PositionalLinkedList, 
     * ensuring that it can be used in for-each loops
     * Skeleton for this code was copied from the CSC316 Workshop 2 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     */
    private class ElementIterator implements Iterator<E> {

    	/** Iterator used to traverse list */
        private Iterator<Position<E>> it;

        /** Constructor that initializes iterator */
        public ElementIterator() {
            it = new PositionIterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
    
    /**
     * Wrapper class to adapt PositionIterator into an Iterable object
     * The skeleton for this code was copied from the CSC316 Workshop 2 guide
     * 
     * @author Dr. King
     * @author Kal Corwin
     *
     */
  private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

}