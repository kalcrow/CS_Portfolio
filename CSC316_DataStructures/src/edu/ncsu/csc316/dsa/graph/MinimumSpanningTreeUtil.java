package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.disjoint_set.DisjointSetForest;
import edu.ncsu.csc316.dsa.disjoint_set.UpTreeDisjointSetForest;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * MinimumSpanningTreeUtil provides a collection of behaviors for computing
 * minimum spanning trees for a given graph.
 * 
 * The MinimumSpanningTreeUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * Skeleton for this code copied from CSC316 Workshop 12 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class MinimumSpanningTreeUtil {
    
    /**
     * Returns a positional list of minimum spanning tree edges for the given graph
     * using Kruskal's minimum spanning tree algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param g the graph for which to compute a minimum spanning tree
     * @return a positional list of minimum spanning tree edges
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> kruskal(Graph<V, E> g) {
        PositionalList<Edge<E>> edges = new PositionalLinkedList<Edge<E>>();
    	AdaptablePriorityQueue<Integer, Edge<E>> orderedEdges = new HeapAdaptablePriorityQueue<Integer, Edge<E>>();
    	DisjointSetForest<Vertex<V>> forest = new UpTreeDisjointSetForest<Vertex<V>>();
    	
    	for (Edge<E> e: g.edges()) {
    		orderedEdges.insert(e.getElement().getWeight(), e);
    	}
    	int components = g.numVertices();
    	for (Vertex<V> v: g.vertices()) {
    		forest.makeSet(v);
    	}
    	while (components > 1) {
    		Entry<Integer, Edge<E>> entry = orderedEdges.deleteMin();
    		Edge<E> edge = entry.getValue();
    		Vertex<V>[] endPoints = g.endVertices(edge);
    		Position<Vertex<V>> u = forest.find(endPoints[0]);
    		Position<Vertex<V>> v = forest.find(endPoints[1]);
    		
    		if (!u.equals(v)) {
    			forest.union(u, v);
    			edges.addLast(edge);
    			components--;
    		}
    	}
    	
    	return edges;
    }
    
    /**
     * Provided by CSC316 teaching staff
     * 
     * Returns a positional list of minimum spanning tree edges for the given graph
     * using Prim-Jarnik's minimum spanning tree algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param g the graph for which to compute a minimum spanning tree
     * @return a positional list of minimum spanning tree edges
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> primJarnik(Graph<V, E> g) {
        AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
        Map<Vertex<V>, Edge<E>> connectingEdges = new LinearProbingHashMap<>();
        
        PositionalList<Edge<E>> tree = new PositionalLinkedList<>();
        
        Vertex<V> src = g.vertices().iterator().next();
        
        for(Vertex<V> v : g.vertices()) {
            if(v == src) {
                weights.put(v, 0);
            } else {
                weights.put(v, Integer.MAX_VALUE);
            }
            pqEntries.put(v, q.insert(weights.get(v), v));
        }
        while(!q.isEmpty()) {
            Entry<Integer, Vertex<V>> entry = q.deleteMin();
            Vertex<V> u = entry.getValue();
            if(connectingEdges.get(u) != null) {
                tree.addLast(connectingEdges.get(u));
            }
            known.add(u);
            for(Edge<E> e : g.outgoingEdges(u)) {
                Vertex<V> z = g.opposite(u, e);
                int r = e.getElement().getWeight();
                if(!known.contains(z) && r < weights.get(z)) {
                    weights.put(z, r);
                    connectingEdges.put(z, e);
                    q.replaceKey(pqEntries.get(z), r);
                }
            }
        }
        return tree;
    }

}