package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * The skeleton for this code was copied from the CSC316 Workshop 2 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    
	 @Override
	    public Iterable<Position<E>> inOrder() {
		 	PositionCollection traversal = new PositionCollection();
	        if (!isEmpty()) {
	            inOrderHelper(root(), traversal);
	        }
	        return traversal;
	    }

	 /**
	     * Helper method used to assist with inOrder traversals of a Binary Tree
	     * 
	     * @param p position to start at
	     * @param traversal PositionCollection of Positions that have already been traversed
	     */
	    private void inOrderHelper(Position<E> p, PositionCollection traversal) {

	    	if (left(p) != null) {
	    		inOrderHelper(left(p), traversal);
	    	}
	        traversal.add(p);
	        if (right(p) != null) {
	        	inOrderHelper(right(p), traversal);
	        }
	    }
    
    @Override
    public int numChildren(Position<E> p) {
    
    	validate(p);
    	
    	if (left(p) == null && right(p) == null) {
    		return 0;
    	}
    	if (left(p) == null || right(p) == null) {
    		return 1;
    	}
    	return 2;
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {
 
    	validate(p);
    	
    	if (parent(p) == null) {
    		return null;
    	}
    	
    	if (left(parent(p)).equals(p)) {
    		return right(parent(p));
    	} else if (right(parent(p)).equals(p)) {
    		return left(parent(p));
    	}
    	
    	return null;
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}