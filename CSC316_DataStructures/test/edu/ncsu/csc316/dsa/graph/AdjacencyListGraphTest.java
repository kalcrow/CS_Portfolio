package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for AdjacencyListGraph
 * Skeleton for this code copied from CSC316 Workshop 11 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 */
public class AdjacencyListGraphTest {


	/** Undirected Graph to use for testing */
    private Graph<String, Integer> undirectedGraph;
    /** Directed Graph to use for testing */
    private Graph<String, Integer> directedGraph;
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyListGraph<String, Integer>();
        directedGraph = new AdjacencyListGraph<String, Integer>(true);
    }
    
    private void buildUndirectedSample() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
    }
    
    private void buildDirectedSample() {
        Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = directedGraph.insertVertex("Asheville");
        Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = directedGraph.insertVertex("Durham");
        Vertex<String> v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
    }

    /**
     * Test the output of the numVertices() behavior
     */     
    @Test
    public void testNumVertices() {
    	assertEquals(0, undirectedGraph.numVertices());
        buildUndirectedSample();
        assertEquals(5, undirectedGraph.numVertices());
        
        assertEquals(0, directedGraph.numVertices());
        buildDirectedSample();
        assertEquals(6, directedGraph.numVertices());
    }

    /**
     * Test the output of the vertices() behavior
     */ 
    @Test
    public void testVertices() {
        // We cannot call buildUndirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        PositionalList<Vertex<String>> list = (PositionalList<Vertex<String>>)undirectedGraph.vertices();
        int i = 1;
        for (Vertex<String> p: list) {
        	if (i == 1) {
        		assertEquals(p, v1);
        	} else if (i == 2) {
        		assertEquals(p, v2);
        	} else if (i == 3) {
        		assertEquals(p, v3);
        	} else if (i == 4) {
        		assertEquals(p, v4);
        	} else if (i == 5) {
        		assertEquals(p, v5);
        	} else {
        		fail("Unexpected number of vertices in list");
        	}
        	i++;
        }
        
        
        
        
        // DIRECTED
        // We cannot call buildDirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing     
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        list = (PositionalList<Vertex<String>>)directedGraph.vertices();
        i = 1;
        for (Vertex<String> p: list) {
        	if (i == 1) {
        		assertEquals(p, v1);
        	} else if (i == 2) {
        		assertEquals(p, v2);
        	} else if (i == 3) {
        		assertEquals(p, v3);
        	} else if (i == 4) {
        		assertEquals(p, v4);
        	} else if (i == 5) {
        		assertEquals(p, v5);
        	} else if (i == 6) {
        		assertEquals(p, v6);
        	} else {
        		fail("Unexpected number of vertices in list");
        	}
        	i++;
        }
    }

    /**
     * Test the output of the numEdges() behavior
     */ 
    @Test
    public void testNumEdges() {
    	assertEquals(0, undirectedGraph.numEdges());
        buildUndirectedSample();
        assertEquals(10, undirectedGraph.numEdges());
        
        assertEquals(0, directedGraph.numEdges());
        buildDirectedSample();
        assertEquals(11, directedGraph.numEdges());
    }

    /**
     * Test the output of the edges() behavior
     */ 
    @Test
    public void testEdges() {
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
        
        PositionalList<Edge<Integer>> list = (PositionalList<Edge<Integer>>)undirectedGraph.edges();
        int i = 1;
        for (Edge<Integer> e: list) {
        	if (i == 1) {
        		assertEquals(e, e1);
        	} else if (i == 2) {
        		assertEquals(e, e2);
        	} else if (i == 3) {
        		assertEquals(e, e3);
        	} else if (i == 4) {
        		assertEquals(e, e4);
        	} else if (i == 5) {
        		assertEquals(e, e5);
        	} else if (i == 6) {
        		assertEquals(e, e6);
        	} else if (i == 7) {
        		assertEquals(e, e7);
        	} else if (i == 8) {
        		assertEquals(e, e8);
        	} else if (i == 9) {
        		assertEquals(e, e9);
        	} else if (i == 10) {
        		assertEquals(e, e10);
        	}  else {
        		fail("Unexpected number of vertices in list");
        	}
        	i++;
        }
        
        if (i < 11) {
        	fail("Not enough edges were added to the list");
        }
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        list = (PositionalList<Edge<Integer>>)directedGraph.edges();
        i = 1;
        for (Edge<Integer> e: list) {
        	if (i == 1) {
        		assertEquals(e, e1);
        	} else if (i == 2) {
        		assertEquals(e, e2);
        	} else if (i == 3) {
        		assertEquals(e, e3);
        	} else if (i == 4) {
        		assertEquals(e, e4);
        	} else if (i == 5) {
        		assertEquals(e, e5);
        	} else if (i == 6) {
        		assertEquals(e, e6);
        	} else if (i == 7) {
        		assertEquals(e, e7);
        	} else if (i == 8) {
        		assertEquals(e, e8);
        	} else if (i == 9) {
        		assertEquals(e, e9);
        	} else if (i == 10) {
        		assertEquals(e, e10);
        	} else if (i == 11) {
        		assertEquals(e, e11);
        	} else {
        		fail("Unexpected number of vertices in list");
        	}
        	i++;
        }
        
        if (i < 12) {
        	fail("Not enough edges were added to the list");
        }
    }

    /**
     * Test the output of the getEdge(v1,v2) behavior
     */ 
    @Test
    public void testGetEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        assertEquals(e1, undirectedGraph.getEdge(v1, v2));
        assertEquals(e2, undirectedGraph.getEdge(v1, v3));
        assertEquals(e3, undirectedGraph.getEdge(v1, v4));
        assertEquals(e4, undirectedGraph.getEdge(v1, v5));
        assertEquals(e5, undirectedGraph.getEdge(v2, v3));
        assertEquals(e6, undirectedGraph.getEdge(v2, v4));
        assertEquals(e7, undirectedGraph.getEdge(v2, v5));
        assertEquals(e8, undirectedGraph.getEdge(v3, v4));
        assertEquals(e9, undirectedGraph.getEdge(v3, v5));
        assertEquals(e10, undirectedGraph.getEdge(v4, v5));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(e1, directedGraph.getEdge(v1, v2));
        assertEquals(e2, directedGraph.getEdge(v1, v3));
        assertEquals(e3, directedGraph.getEdge(v1, v4));
        assertEquals(e4, directedGraph.getEdge(v1, v5));
        assertEquals(e5, directedGraph.getEdge(v2, v3));
        assertEquals(e6, directedGraph.getEdge(v2, v4));
        assertEquals(e7, directedGraph.getEdge(v2, v5));
        assertEquals(e8, directedGraph.getEdge(v3, v4));
        assertEquals(e9, directedGraph.getEdge(v3, v5));
        assertEquals(e10, directedGraph.getEdge(v4, v5));
        assertEquals(e11, directedGraph.getEdge(v5, v6));
    }

    /**
     * Test the output of the endVertices(e) behavior
     */ 
    @Test
    public void testEndVertices() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        Vertex<String>[] vertices = undirectedGraph.endVertices(e1);
        assertEquals(vertices[0], v1);
        assertEquals(vertices[1], v2);
        vertices = undirectedGraph.endVertices(e2);
        assertEquals(vertices[0], v1);
        assertEquals(vertices[1], v3);
        vertices = undirectedGraph.endVertices(e3);
        assertEquals(vertices[0], v1);
        assertEquals(vertices[1], v4);
        vertices = undirectedGraph.endVertices(e4);
        assertEquals(vertices[0], v1);
        assertEquals(vertices[1], v5);
        vertices = undirectedGraph.endVertices(e5);
        assertEquals(vertices[0], v2);
        assertEquals(vertices[1], v3);
        vertices = undirectedGraph.endVertices(e6);
        assertEquals(vertices[0], v2);
        assertEquals(vertices[1], v4);
        vertices = undirectedGraph.endVertices(e7);
        assertEquals(vertices[0], v2);
        assertEquals(vertices[1], v5);
        vertices = undirectedGraph.endVertices(e8);
        assertEquals(vertices[0], v3);
        assertEquals(vertices[1], v4);
        vertices = undirectedGraph.endVertices(e9);
        assertEquals(vertices[0], v3);
        assertEquals(vertices[1], v5);
        vertices = undirectedGraph.endVertices(e10);
        assertEquals(vertices[0], v4);
        assertEquals(vertices[1], v5);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        vertices = directedGraph.endVertices(e1);
        assertEquals(vertices[0], v1);
        assertEquals(vertices[1], v2);
        vertices = directedGraph.endVertices(e2);
        assertEquals(vertices[0], v1);
        assertEquals(vertices[1], v3);
        vertices = directedGraph.endVertices(e3);
        assertEquals(vertices[0], v1);
        assertEquals(vertices[1], v4);
        vertices = directedGraph.endVertices(e4);
        assertEquals(vertices[0], v1);
        assertEquals(vertices[1], v5);
        vertices = directedGraph.endVertices(e5);
        assertEquals(vertices[0], v2);
        assertEquals(vertices[1], v3);
        vertices = directedGraph.endVertices(e6);
        assertEquals(vertices[0], v2);
        assertEquals(vertices[1], v4);
        vertices = directedGraph.endVertices(e7);
        assertEquals(vertices[0], v2);
        assertEquals(vertices[1], v5);
        vertices = directedGraph.endVertices(e8);
        assertEquals(vertices[0], v3);
        assertEquals(vertices[1], v4);
        vertices = directedGraph.endVertices(e9);
        assertEquals(vertices[0], v3);
        assertEquals(vertices[1], v5);
        vertices = directedGraph.endVertices(e10);
        assertEquals(vertices[0], v4);
        assertEquals(vertices[1], v5);
        vertices = directedGraph.endVertices(e11);
        assertEquals(vertices[0], v5);
        assertEquals(vertices[1], v6);
    }

    /**
     * Test the output of the opposite(v, e) behavior
     */ 
    @Test
    public void testOpposite() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        assertEquals(v2, undirectedGraph.opposite(v1, e1));
        assertEquals(v1, undirectedGraph.opposite(v2, e1));
        
        assertEquals(v3, undirectedGraph.opposite(v1, e2));
        assertEquals(v1, undirectedGraph.opposite(v3, e2));
        
        assertEquals(v4, undirectedGraph.opposite(v1, e3));
        assertEquals(v1, undirectedGraph.opposite(v4, e3));
        
        assertEquals(v5, undirectedGraph.opposite(v1, e4));
        assertEquals(v1, undirectedGraph.opposite(v5, e4));
        
        assertEquals(v3, undirectedGraph.opposite(v2, e5));
        assertEquals(v2, undirectedGraph.opposite(v3, e5));
        
        assertEquals(v4, undirectedGraph.opposite(v2, e6));
        assertEquals(v2, undirectedGraph.opposite(v4, e6));
        
        assertEquals(v5, undirectedGraph.opposite(v2, e7));
        assertEquals(v2, undirectedGraph.opposite(v5, e7));
        
        assertEquals(v4, undirectedGraph.opposite(v3,  e8));
        assertEquals(v3, undirectedGraph.opposite(v4, e8));
        
        assertEquals(v5, undirectedGraph.opposite(v3, e9));
        assertEquals(v3, undirectedGraph.opposite(v5, e9));
        
        assertEquals(v5, undirectedGraph.opposite(v4,  e10));
        assertEquals(v4, undirectedGraph.opposite(v5, e10));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(v2, directedGraph.opposite(v1, e1));
        assertEquals(v1, directedGraph.opposite(v2, e1));
        
        assertEquals(v3, directedGraph.opposite(v1, e2));
        assertEquals(v1, directedGraph.opposite(v3, e2));
        
        assertEquals(v4, directedGraph.opposite(v1, e3));
        assertEquals(v1, directedGraph.opposite(v4, e3));
        
        assertEquals(v5, directedGraph.opposite(v1, e4));
        assertEquals(v1, directedGraph.opposite(v5, e4));
        
        assertEquals(v3, directedGraph.opposite(v2, e5));
        assertEquals(v2, directedGraph.opposite(v3, e5));
        
        assertEquals(v4, directedGraph.opposite(v2, e6));
        assertEquals(v2, directedGraph.opposite(v4, e6));
        
        assertEquals(v5, directedGraph.opposite(v2, e7));
        assertEquals(v2, directedGraph.opposite(v5, e7));
        
        assertEquals(v4, directedGraph.opposite(v3,  e8));
        assertEquals(v3, directedGraph.opposite(v4, e8));
        
        assertEquals(v5, directedGraph.opposite(v3, e9));
        assertEquals(v3, directedGraph.opposite(v5, e9));
        
        assertEquals(v5, directedGraph.opposite(v4,  e10));
        assertEquals(v4, directedGraph.opposite(v5, e10));
        
        assertEquals(v6, directedGraph.opposite(v5,  e11));
        assertEquals(v5, directedGraph.opposite(v6, e11));
    }

    /**
     * Test the output of the outDegree(v) behavior
     */ 
    @Test
    public void testOutDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        //Edge<Integer> e1 = 
        		undirectedGraph.insertEdge(v1, v2, 5);
        //Edge<Integer> e2 = 
        		undirectedGraph.insertEdge(v1, v3, 10);
        //Edge<Integer> e3 = 
        		undirectedGraph.insertEdge(v1, v4, 15);
       // Edge<Integer> e4 = 
        		undirectedGraph.insertEdge(v1, v5, 20);
        //Edge<Integer> e5 = 
        		undirectedGraph.insertEdge(v2, v3, 25);
        //Edge<Integer> e6 = 
        		undirectedGraph.insertEdge(v2, v4, 30);
        //Edge<Integer> e7 = 
        		undirectedGraph.insertEdge(v2, v5, 35);
        //Edge<Integer> e8 = 
        		undirectedGraph.insertEdge(v3, v4, 40);
       // Edge<Integer> e9 = 
        		undirectedGraph.insertEdge(v3, v5, 45);
        //Edge<Integer> e10 = 
        		undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.outDegree(v1));
        assertEquals(4, undirectedGraph.outDegree(v2));
        assertEquals(4, undirectedGraph.outDegree(v3));
        assertEquals(4, undirectedGraph.outDegree(v4));
        assertEquals(4, undirectedGraph.outDegree(v5));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        //e1 = 
        		directedGraph.insertEdge(v1, v2, 5);
        //e2 = 
        		directedGraph.insertEdge(v1, v3, 10);
        //e3 = 
        		directedGraph.insertEdge(v1, v4, 15);
        //e4 = 
        		directedGraph.insertEdge(v1, v5, 20);
        //e5 = 
        		directedGraph.insertEdge(v2, v3, 25);
        //e6 = 
        		directedGraph.insertEdge(v2, v4, 30);
        //e7 = 
        		directedGraph.insertEdge(v2, v5, 35);
        //e8 = 
        		directedGraph.insertEdge(v3, v4, 40);
        //e9 = 
        		directedGraph.insertEdge(v3, v5, 45);
        //e10 = 
        		directedGraph.insertEdge(v4, v5, 50);
       // Edge<Integer> e11 = 
        		directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(4, directedGraph.outDegree(v1));
        assertEquals(3, directedGraph.outDegree(v2));
        assertEquals(2, directedGraph.outDegree(v3));
        assertEquals(1, directedGraph.outDegree(v4));
        assertEquals(1, directedGraph.outDegree(v5));
        assertEquals(0, directedGraph.outDegree(v6));
    }

    /**
     * Test the output of the inDegree(v) behavior
     */ 
    @Test
    public void testInDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
       // Edge<Integer> e1 = 
        		undirectedGraph.insertEdge(v1, v2, 5);
      //  Edge<Integer> e2 = 
        		undirectedGraph.insertEdge(v1, v3, 10);
     //   Edge<Integer> e3 = 
        		undirectedGraph.insertEdge(v1, v4, 15);
     //   Edge<Integer> e4 = 
        		undirectedGraph.insertEdge(v1, v5, 20);
      //  Edge<Integer> e5 = 
        		undirectedGraph.insertEdge(v2, v3, 25);
      //  Edge<Integer> e6 = 
        		undirectedGraph.insertEdge(v2, v4, 30);
      //  Edge<Integer> e7 = 
        		undirectedGraph.insertEdge(v2, v5, 35);
      //  Edge<Integer> e8 = 
        		undirectedGraph.insertEdge(v3, v4, 40);
      //  Edge<Integer> e9 = 
        		undirectedGraph.insertEdge(v3, v5, 45);
      //  Edge<Integer> e10 = 
        		undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.inDegree(v1));
        assertEquals(4, undirectedGraph.inDegree(v2));
        assertEquals(4, undirectedGraph.inDegree(v3));
        assertEquals(4, undirectedGraph.inDegree(v4));
        assertEquals(4, undirectedGraph.inDegree(v5));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        //e1 = 
        		directedGraph.insertEdge(v1, v2, 5);
        //e2 = 
        		directedGraph.insertEdge(v1, v3, 10);
        ///e3 = 
        		directedGraph.insertEdge(v1, v4, 15);
        //e4 = 
        		directedGraph.insertEdge(v1, v5, 20);
        //e5 = 
        		directedGraph.insertEdge(v2, v3, 25);
        //e6 = 
        		directedGraph.insertEdge(v2, v4, 30);
        //e7 = 
        		directedGraph.insertEdge(v2, v5, 35);
        //e8 = 
        		directedGraph.insertEdge(v3, v4, 40);
        //e9 = 
        		directedGraph.insertEdge(v3, v5, 45);
        //e10 = 
        		directedGraph.insertEdge(v4, v5, 50);
        //Edge<Integer> e11 = 
        		directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(0, directedGraph.inDegree(v1));
        assertEquals(1, directedGraph.inDegree(v2));
        assertEquals(2, directedGraph.inDegree(v3));
        assertEquals(3, directedGraph.inDegree(v4));
        assertEquals(4, directedGraph.inDegree(v5));
        assertEquals(1, directedGraph.inDegree(v6));
    }

    /**
     * Test the output of the outgoingEdges(v) behavior
     */ 
    //@SuppressWarnings("unchecked")
    @Test
    public void testOutgoingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Iterable<Edge<Integer>> temp = undirectedGraph.outgoingEdges(v1);
        //int count = 0;
      //  Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
      //  assertTrue(it.hasNext());
       // temp[count] = it.next();
      //  count++;
      //  temp[count] = it.next();
      //  count++;
      //  temp[count] = it.next();
      //  count++;
      //  temp[count] = it.next();
      //  count++;
       // assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        temp = undirectedGraph.outgoingEdges(v2);
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e7));
        temp = undirectedGraph.outgoingEdges(v3);
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e8));
        assertTrue(arrayContains(temp, e9));
        temp = undirectedGraph.outgoingEdges(v4);
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e8));
        assertTrue(arrayContains(temp, e10));
        temp = undirectedGraph.outgoingEdges(v5);
        assertTrue(arrayContains(temp, e4));
        assertTrue(arrayContains(temp, e7));
        assertTrue(arrayContains(temp, e9));
        assertTrue(arrayContains(temp, e10));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        temp = directedGraph.outgoingEdges(v1);
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        temp = directedGraph.outgoingEdges(v2);
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e7));
        temp = directedGraph.outgoingEdges(v3);
        assertTrue(arrayContains(temp, e8));
        assertTrue(arrayContains(temp, e9));
        temp = directedGraph.outgoingEdges(v4);
        assertTrue(arrayContains(temp, e10));
        temp = directedGraph.outgoingEdges(v5);
        assertTrue(arrayContains(temp, e11));
        temp = directedGraph.outgoingEdges(v6);
        assertFalse(arrayContains(temp, e1));
        assertFalse(arrayContains(temp, e2));
        assertFalse(arrayContains(temp, e3));
        assertFalse(arrayContains(temp, e4));
        assertFalse(arrayContains(temp, e5));
        assertFalse(arrayContains(temp, e6));
        assertFalse(arrayContains(temp, e7));
        assertFalse(arrayContains(temp, e8));
        assertFalse(arrayContains(temp, e9));
        assertFalse(arrayContains(temp, e10));
        assertFalse(arrayContains(temp, e11));
    }
    
    // Helper method to check that an array contains a certain target.
    // This is helpful for testing graph ADT behaviors where an order
    // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
    private boolean arrayContains(Iterable<Edge<Integer>> temp, Edge<Integer> target) {
        for(Edge<Integer> e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test the output of the incomingEdges(v) behavior
     */ 
    //@SuppressWarnings("unchecked")
    @Test
    public void testIncomingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        Iterable<Edge<Integer>> current = undirectedGraph.incomingEdges(v1);
        assertTrue(arrayContains(current, e1));
        assertTrue(arrayContains(current, e2));
        assertTrue(arrayContains(current, e3));
        assertTrue(arrayContains(current, e4));
        assertFalse(arrayContains(current, e5));
        assertFalse(arrayContains(current, e6));
        assertFalse(arrayContains(current, e7));
        assertFalse(arrayContains(current, e8));
        assertFalse(arrayContains(current, e9));
        assertFalse(arrayContains(current, e10));
        current = undirectedGraph.incomingEdges(v2);
        assertTrue(arrayContains(current, e1));
        assertFalse(arrayContains(current, e2));
        assertFalse(arrayContains(current, e3));
        assertFalse(arrayContains(current, e4));
        assertTrue(arrayContains(current, e5));
        assertTrue(arrayContains(current, e6));
        assertTrue(arrayContains(current, e7));
        assertFalse(arrayContains(current, e8));
        assertFalse(arrayContains(current, e9));
        assertFalse(arrayContains(current, e10));
        current = undirectedGraph.incomingEdges(v3);
        assertFalse(arrayContains(current, e1));
        assertTrue(arrayContains(current, e2));
        assertFalse(arrayContains(current, e3));
        assertFalse(arrayContains(current, e4));
        assertTrue(arrayContains(current, e5));
        assertFalse(arrayContains(current, e6));
        assertFalse(arrayContains(current, e7));
        assertTrue(arrayContains(current, e8));
        assertTrue(arrayContains(current, e9));
        assertFalse(arrayContains(current, e10));
        current = undirectedGraph.incomingEdges(v4);
        assertFalse(arrayContains(current, e1));
        assertFalse(arrayContains(current, e2));
        assertTrue(arrayContains(current, e3));
        assertFalse(arrayContains(current, e4));
        assertFalse(arrayContains(current, e5));
        assertTrue(arrayContains(current, e6));
        assertFalse(arrayContains(current, e7));
        assertTrue(arrayContains(current, e8));
        assertFalse(arrayContains(current, e9));
        assertTrue(arrayContains(current, e10));
        current = undirectedGraph.incomingEdges(v5);
        assertFalse(arrayContains(current, e1));
        assertFalse(arrayContains(current, e2));
        assertFalse(arrayContains(current, e3));
        assertTrue(arrayContains(current, e4));
        assertFalse(arrayContains(current, e5));
        assertFalse(arrayContains(current, e6));
        assertTrue(arrayContains(current, e7));
        assertFalse(arrayContains(current, e8));
        assertTrue(arrayContains(current, e9));
        assertTrue(arrayContains(current, e10));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        current = directedGraph.incomingEdges(v1);
        assertFalse(arrayContains(current, e1));
        assertFalse(arrayContains(current, e2));
        assertFalse(arrayContains(current, e3));
        assertFalse(arrayContains(current, e4));
        assertFalse(arrayContains(current, e5));
        assertFalse(arrayContains(current, e6));
        assertFalse(arrayContains(current, e7));
        assertFalse(arrayContains(current, e8));
        assertFalse(arrayContains(current, e9));
        assertFalse(arrayContains(current, e10));
        assertFalse(arrayContains(current, e11));
        current = directedGraph.incomingEdges(v2);
        assertTrue(arrayContains(current, e1));
        assertFalse(arrayContains(current, e2));
        assertFalse(arrayContains(current, e3));
        assertFalse(arrayContains(current, e4));
        assertFalse(arrayContains(current, e5));
        assertFalse(arrayContains(current, e6));
        assertFalse(arrayContains(current, e7));
        assertFalse(arrayContains(current, e8));
        assertFalse(arrayContains(current, e9));
        assertFalse(arrayContains(current, e10));
        assertFalse(arrayContains(current, e11));
        current = directedGraph.incomingEdges(v3);
        assertFalse(arrayContains(current, e1));
        assertTrue(arrayContains(current, e2));
        assertFalse(arrayContains(current, e3));
        assertFalse(arrayContains(current, e4));
        assertTrue(arrayContains(current, e5));
        assertFalse(arrayContains(current, e6));
        assertFalse(arrayContains(current, e7));
        assertFalse(arrayContains(current, e8));
        assertFalse(arrayContains(current, e9));
        assertFalse(arrayContains(current, e10));
        assertFalse(arrayContains(current, e11));
        current = directedGraph.incomingEdges(v4);
        assertFalse(arrayContains(current, e1));
        assertFalse(arrayContains(current, e2));
        assertTrue(arrayContains(current, e3));
        assertFalse(arrayContains(current, e4));
        assertFalse(arrayContains(current, e5));
        assertTrue(arrayContains(current, e6));
        assertFalse(arrayContains(current, e7));
        assertTrue(arrayContains(current, e8));
        assertFalse(arrayContains(current, e9));
        assertFalse(arrayContains(current, e10));
        assertFalse(arrayContains(current, e11));
        current = directedGraph.incomingEdges(v5);
        assertFalse(arrayContains(current, e1));
        assertFalse(arrayContains(current, e2));
        assertFalse(arrayContains(current, e3));
        assertTrue(arrayContains(current, e4));
        assertFalse(arrayContains(current, e5));
        assertFalse(arrayContains(current, e6));
        assertTrue(arrayContains(current, e7));
        assertFalse(arrayContains(current, e8));
        assertTrue(arrayContains(current, e9));
        assertTrue(arrayContains(current, e10));
        assertFalse(arrayContains(current, e11));
        current = directedGraph.incomingEdges(v6);
        assertFalse(arrayContains(current, e1));
        assertFalse(arrayContains(current, e2));
        assertFalse(arrayContains(current, e3));
        assertFalse(arrayContains(current, e4));
        assertFalse(arrayContains(current, e5));
        assertFalse(arrayContains(current, e6));
        assertFalse(arrayContains(current, e7));
        assertFalse(arrayContains(current, e8));
        assertFalse(arrayContains(current, e9));
        assertFalse(arrayContains(current, e10));
        assertTrue(arrayContains(current, e11));
    }

    /**
     * Test the output of the insertVertex(x) behavior
     */ 
    @Test
    public void testInsertVertex() {
        assertEquals(0, undirectedGraph.numVertices());
        Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
        assertEquals(1, undirectedGraph.numVertices());
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertFalse(it.hasNext());      
        //TODO complete this test case
    }

    /**
     * Test the output of the insertEdge(v1, v2, x) behavior
     */ 
    @Test
    public void testInsertEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        
        assertEquals(0, undirectedGraph.numEdges());
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
        assertEquals(1, undirectedGraph.numEdges());
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertFalse(it.hasNext());
        //TODO complete this test case
    }

    /**
     * Test the output of the removeVertex(v) behavior
     */ 
    @Test
    public void testRemoveVertex() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        //Edge<Integer> e1 = 
        		undirectedGraph.insertEdge(v1, v2, 5);
        //Edge<Integer> e2 = 
        		undirectedGraph.insertEdge(v1, v3, 10);
       // Edge<Integer> e3 = 
        		undirectedGraph.insertEdge(v1, v4, 15);
       // Edge<Integer> e4 = 
        		undirectedGraph.insertEdge(v1, v5, 20);
       // Edge<Integer> e5 = 
        		undirectedGraph.insertEdge(v2, v3, 25);
       // Edge<Integer> e6 = 
        		undirectedGraph.insertEdge(v2, v4, 30);
       // Edge<Integer> e7 = 
        		undirectedGraph.insertEdge(v2, v5, 35);
       // Edge<Integer> e8 = 
        		undirectedGraph.insertEdge(v3, v4, 40);
       // Edge<Integer> e9 = 
        		undirectedGraph.insertEdge(v3, v5, 45);
        //Edge<Integer> e10 = 
        		undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(5, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v5);
        assertEquals(4, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        //TODO complete this test case
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        //e1 = 
        		directedGraph.insertEdge(v1, v2, 5);
        //e2 = 
        		directedGraph.insertEdge(v1, v3, 10);
       // e3 = 
        		directedGraph.insertEdge(v1, v4, 15);
        //e4 = 
        		directedGraph.insertEdge(v1, v5, 20);
        //e5 = 
        		directedGraph.insertEdge(v2, v3, 25);
        //e6 =
        		directedGraph.insertEdge(v2, v4, 30);
       // e7 =
        		directedGraph.insertEdge(v2, v5, 35);
       // e8 = 
        		directedGraph.insertEdge(v3, v4, 40);
       // e9 = 
        		directedGraph.insertEdge(v3, v5, 45);
       // e10 = 
        		directedGraph.insertEdge(v4, v5, 50);
        //Edge<Integer> e11 = 
        		directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(11, directedGraph.numEdges());
        directedGraph.removeVertex(v6);
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        //TODO complete this test case
    }

    /**
     * Test the output of the removeEdge(e) behavior
     */ 
    @Test
    public void testRemoveEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        //Vertex<String> v6 = 
        		undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = 
        		undirectedGraph.insertEdge(v1, v2, 5);
        //Edge<Integer> e2 = 
        		undirectedGraph.insertEdge(v1, v3, 10);
        //Edge<Integer> e3 = 
        		undirectedGraph.insertEdge(v1, v4, 15);
        //Edge<Integer> e4 = 
        		undirectedGraph.insertEdge(v1, v5, 20);
        //Edge<Integer> e5 = 
        		undirectedGraph.insertEdge(v2, v3, 25);
        //Edge<Integer> e6 = 
        		undirectedGraph.insertEdge(v2, v4, 30);
        //Edge<Integer> e7 = 
        		undirectedGraph.insertEdge(v2, v5, 35);
        //Edge<Integer> e8 = 
        		undirectedGraph.insertEdge(v3, v4, 40);
        //Edge<Integer> e9 = 
        		undirectedGraph.insertEdge(v3, v5, 45);
        //Edge<Integer> e10 = 
        		undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e1);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(9, undirectedGraph.numEdges());
        
        //undirectedGraph.removeEdge(e1);
        //assertEquals(6, undirectedGraph.numVertices());
        //assertEquals(9, undirectedGraph.numEdges());
        //TODO complete this test case
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        //v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        //e2 = 
        		directedGraph.insertEdge(v1, v3, 10);
      //  e3 = 
        		directedGraph.insertEdge(v1, v4, 15);
       // e4 = 
        		directedGraph.insertEdge(v1, v5, 20);
       // e5 = 
        		directedGraph.insertEdge(v2, v3, 25);
      //  e6 = 
        		directedGraph.insertEdge(v2, v4, 30);
      //  e7 = 
        		directedGraph.insertEdge(v2, v5, 35);
       // e8 = 
        		directedGraph.insertEdge(v3, v4, 40);
       // e9 = 
        		directedGraph.insertEdge(v3, v5, 45);
        //e10 = 
        		directedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeEdge(e1);
        assertEquals(5, directedGraph.numVertices());
        assertEquals(9, directedGraph.numEdges());
        
        //directedGraph.removeEdge(e1);
        //assertEquals(6, directedGraph.numVertices());
       // assertEquals(9, directedGraph.numEdges());
        //TODO complete this test case
    }
}
