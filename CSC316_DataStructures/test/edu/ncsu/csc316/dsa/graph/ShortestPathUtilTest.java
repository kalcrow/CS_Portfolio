package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil
 * Checks the expected outputs of Dijksra's algorithm
 * and the shortest path tree construction method
 * Skeleton for this code copied from CSC316 Workshop 12 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class ShortestPathUtilTest {
	/** Undirected Graph to use for testing */
    private Graph<String, NumEdge> undirectedGraph;
    
    /**
     * Weighted object used to test ShortestPathUtil methods
     * 
     * @author Kal Corwin
     */
    private class NumEdge implements Weighted {
    	/** Weight of the NumEdge */
    	private int val;
    	
    	public NumEdge(int val) {
    		this.val = val;
    	}
    	
    	public int getWeight() {
    		return val;
    	}
    }
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyListGraph<String, NumEdge>();
    }
    
    /**
     * Test the output of Dijkstra's algorithm
     */ 
    @Test
    public void testDijkstra() {
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<NumEdge> e1 = undirectedGraph.insertEdge(v1, v2, new NumEdge(5));
        Edge<NumEdge> e2 = undirectedGraph.insertEdge(v1, v3, new NumEdge(10));
        Edge<NumEdge> e3 = undirectedGraph.insertEdge(v1, v4, new NumEdge(15));
        Edge<NumEdge> e4 = undirectedGraph.insertEdge(v1, v5, new NumEdge(20));
       // Edge<NumEdge> e5 = 
        undirectedGraph.insertEdge(v2, v3, new NumEdge(25));
       // Edge<NumEdge> e6 = 
        undirectedGraph.insertEdge(v2, v4, new NumEdge(30));
       // Edge<NumEdge> e7 = 
        undirectedGraph.insertEdge(v2, v5, new NumEdge(35));
       // Edge<NumEdge> e8 = 
        undirectedGraph.insertEdge(v3, v4, new NumEdge(40));
       // Edge<NumEdge> e9 = 
        undirectedGraph.insertEdge(v3, v5, new NumEdge(45));
       // Edge<NumEdge> e10 = 
        undirectedGraph.insertEdge(v4, v5, new NumEdge(50));
       
        Map<Vertex<String>, Integer> result = ShortestPathUtil.dijkstra(undirectedGraph, v1);
        assertEquals(5, result.size());
        assertEquals((Integer)e1.getElement().getWeight(), result.get(v2));
        assertEquals((Integer)e2.getElement().getWeight(), result.get(v3));
        assertEquals((Integer)e3.getElement().getWeight(), result.get(v4));
        assertEquals((Integer)e4.getElement().getWeight(), result.get(v5));
        
        // TODO Complete this test case
    }
    
    /**
     * Test the output of the shortest path tree construction method
     */ 
    @Test
    public void testShortestPathTree() {
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<NumEdge> e1 = undirectedGraph.insertEdge(v1, v2, new NumEdge(5));
        Edge<NumEdge> e2 = undirectedGraph.insertEdge(v1, v3, new NumEdge(10));
        Edge<NumEdge> e3 = undirectedGraph.insertEdge(v1, v4, new NumEdge(15));
        Edge<NumEdge> e4 = undirectedGraph.insertEdge(v1, v5, new NumEdge(20));
       // Edge<NumEdge> e5 = 
        		undirectedGraph.insertEdge(v2, v3, new NumEdge(25));
        //Edge<NumEdge> e6 = 
        		undirectedGraph.insertEdge(v2, v4, new NumEdge(30));
       // Edge<NumEdge> e7 = 
        		undirectedGraph.insertEdge(v2, v5, new NumEdge(35));
       // Edge<NumEdge> e8 = 
        		undirectedGraph.insertEdge(v3, v4, new NumEdge(40));
       // Edge<NumEdge> e9 = 
        		undirectedGraph.insertEdge(v3, v5, new NumEdge(45));
        //Edge<NumEdge> e10 = 
        		undirectedGraph.insertEdge(v4, v5, new NumEdge(50));
        
        Map<Vertex<String>, Integer> dijkstra = ShortestPathUtil.dijkstra(undirectedGraph, v1);
        Map<Vertex<String>, Edge<NumEdge>> result = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, dijkstra);
        assertEquals(4, result.size());
        assertEquals(e1.getElement().getWeight(), result.get(v2).getElement().getWeight());
        assertEquals(e2.getElement().getWeight(), result.get(v3).getElement().getWeight());
        assertEquals(e3.getElement().getWeight(), result.get(v4).getElement().getWeight());
        assertEquals(e4.getElement().getWeight(), result.get(v5).getElement().getWeight());
        // TODO Complete this test case
    }
    
}