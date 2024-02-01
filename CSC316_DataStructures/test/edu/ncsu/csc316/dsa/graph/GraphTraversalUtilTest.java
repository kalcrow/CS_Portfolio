package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 * Skeleton for this code copied from CSC316 Workshop 12 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class GraphTraversalUtilTest {
	/** Undirected Graph to use for testing */
    private Graph<String, Integer> undirectedGraph;
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyListGraph<String, Integer>();
    }
    
    /**
     * Test the output of depth first search on a graph
     */ 
    @Test
    public void testDepthFirstSearch() {
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
    	
    	Map<Vertex<String>, Edge<Integer>> result = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);
    	assertEquals(4, result.size());
    	assertNull(result.get(v1));
    	assertEquals(e1, result.get(v2));
    	assertEquals(e5, result.get(v3));
    	assertEquals(e8, result.get(v4));
    	assertEquals(e10, result.get(v5));
    	
    	result = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v2);
    	assertEquals(4, result.size());
    	assertEquals(e1, result.get(v1));
    	assertNull(result.get(v2));
    	assertEquals(e2, result.get(v3));
    	assertEquals(e8, result.get(v4));
    	assertEquals(e10, result.get(v5));
    	
    	result = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v3);
    	assertEquals(4, result.size());
    	assertEquals(e2, result.get(v1));
    	assertEquals(e1, result.get(v2));
    	assertNull(result.get(v3));
    	assertEquals(e6, result.get(v4));
    	assertEquals(e10, result.get(v5));
    	
    	result = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v4);
    	assertEquals(4, result.size());
    	assertEquals(e3, result.get(v1));
    	assertEquals(e1, result.get(v2));
    	assertEquals(e5, result.get(v3));
    	assertNull( result.get(v4));
    	assertEquals(e9, result.get(v5));
    	
    	result = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v5);
    	assertEquals(4, result.size());
    	assertEquals(e4, result.get(v1));
    	assertEquals(e1, result.get(v2));
    	assertEquals(e5, result.get(v3));
    	assertEquals(e8, result.get(v4));
    	assertNull(result.get(v5));
    }
    
    /**
     * Test the output of the breadth first search
     */ 
    @Test
    public void testBreadthFirstSearch() {
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
    	
    	Map<Vertex<String>, Edge<Integer>> result = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
    	assertEquals(4, result.size());
    	assertNull(result.get(v1));
    	assertEquals(e1, result.get(v2));
    	assertEquals(e2, result.get(v3));
    	assertEquals(e3, result.get(v4));
    	assertEquals(e4, result.get(v5));
    	
    	result = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v2);
    	assertEquals(4, result.size());
    	assertEquals(e1, result.get(v1));
    	assertNull(result.get(v2));
    	assertEquals(e5, result.get(v3));
    	assertEquals(e6, result.get(v4));
    	assertEquals(e7, result.get(v5));
    	
    	result = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v3);
    	assertEquals(4, result.size());
    	assertEquals(e2, result.get(v1));
    	assertEquals(e5, result.get(v2));
    	assertNull(result.get(v3));
    	assertEquals(e8, result.get(v4));
    	assertEquals(e9, result.get(v5));
    	
    	result = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v4);
    	assertEquals(4, result.size());
    	assertEquals(e3, result.get(v1));
    	assertEquals(e6, result.get(v2));
    	assertEquals(e8, result.get(v3));
    	assertNull( result.get(v4));
    	assertEquals(e10, result.get(v5));
    	
    	result = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v5);
    	assertEquals(4, result.size());
    	assertEquals(e4, result.get(v1));
    	assertEquals(e7, result.get(v2));
    	assertEquals(e9, result.get(v3));
    	assertEquals(e10, result.get(v4));
    	assertNull(result.get(v5));
    }
    
}