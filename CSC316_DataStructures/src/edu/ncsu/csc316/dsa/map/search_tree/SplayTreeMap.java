package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree.BinaryTreeNode;

/**
 * The SplayTreeMap is implemented as a linked data structure to support
 * efficient Tree and Map abstract data type behaviors.
 * 
 * In a Splay tree, the splay operation is performed on each insertion, removal,
 * and retrieval. While the worst-case height of a splay tree is O(n), the splay
 * operation ensures a more efficient runtime over a series of operations. Over
 * a series of m {@see Map#put}, {@see Map#get}, and {@see Map#remove}
 * operations, the splay tree will provide O(mlogn) amortized cost.
 * 
 * SplayTreeMap uses sentinel leaves. Every leaf node should have 2 sentinel
 * children.
 * 
 * The SplayTreeMap class is based on the implementation developed for use with
 * the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * Skeleton for this code was copied from the CSC316 Workshop 7 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <K> the type of keys stored in the Splay tree
 * @param <V> the type of values associated with keys in the Splay tree
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

    /**
     * Constructs a new Splay tree map that uses natural ordering of keys when
     * performing comparisons
     */
    public SplayTreeMap() {
        super(null);
    }

    /**
     * Constructs a new Splay tree map that uses a provided {@link Comparator} when
     * performing comparisons of keys within the tree
     * 
     * @param compare comparator to use
     */
    public SplayTreeMap(Comparator<K> compare) {
        super(compare);
    }

    /**
     * The splay operation rotates the given position, p, to the root of the tree
     * 
     * @param p the position to splay to be the root of the tree
     */
    private void splay(Position<Entry<K, V>> p) {
        
    	BinaryTreeNode<Entry<K, V>> node = (BinaryTreeNode<Entry<K, V>>) p;
    	
    	while (!isRoot(node)) {
    		BinaryTreeNode<Entry<K, V>> parent = (BinaryTreeNode<Entry<K, V>>) parent(node);
    		BinaryTreeNode<Entry<K, V>> grandparent = (BinaryTreeNode<Entry<K, V>>) parent(parent);
    		
    		if (grandparent == null) {
    			rotate(node);
    		} else if (node.equals(right(parent)) && parent.equals(right(grandparent)) || node.equals(left(parent)) && parent.equals(left(grandparent))) {
    			rotate(parent);
    			rotate(node);
    		} else {
    			rotate(node);
    			rotate(node);
    		}
    	}
    }

    /**
     * {@inheritDoc} For a Splay tree, we must rotate position p to be the root of
     * the tree. If the position p is a leaf (sentinel) position, we splay the
     * parent of p.
     */
    protected void actionOnAccess(Position<Entry<K, V>> p) {
       BinaryTreeNode<Entry<K, V>> node = (BinaryTreeNode<Entry<K, V>>) p;
    	
    	if (isLeaf(node)) {
            node = (BinaryTreeNode<Entry<K, V>>) parent(node);
        }
        if (node != null) {
            splay(node);
        }
    }

    /**
     * {@inheritDoc} For a Splay tree, we must rotate position p to be the root of
     * the tree.
     */
    protected void actionOnInsert(Position<Entry<K, V>> p) {
        splay(p);
    }

    /**
     * {@inheritDoc} For a Splay tree, we must rotate the parent of position p to be
     * the root of the tree.
     */
    protected void actionOnDelete(Position<Entry<K, V>> p) {
        if (!isRoot(p)) {
            splay(parent(p));
        }
    }
}