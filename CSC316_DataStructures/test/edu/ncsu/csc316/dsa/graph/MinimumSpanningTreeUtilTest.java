package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for MinimumSpanningTreeUtil
 * Checks the expected outputs of Prim-Jarnik's algorithm
 * and Kruskal's algorithm
 * Skeleton for this code copied from CSC316 Workshop 12 guide
 *
 * @author Dr. King
 * @author Kal Corwin
 *
 */
public class MinimumSpanningTreeUtilTest {

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
     * Test the output of Prim-Jarnik's algorithm
     */ 
    @Test
    public void testPrimJarnik() {
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<NumEdge> e1 = undirectedGraph.insertEdge(v1, v2, new NumEdge(5));
        Edge<NumEdge> e2 = undirectedGraph.insertEdge(v1, v3, new NumEdge(10));
        Edge<NumEdge> e3 = undirectedGraph.insertEdge(v1, v4, new NumEdge(15));
        Edge<NumEdge> e4 = undirectedGraph.insertEdge(v1, v5, new NumEdge(20));
        //Edge<NumEdge> e5 = 
        		undirectedGraph.insertEdge(v2, v3, new NumEdge(25));
        //Edge<NumEdge> e6 = 
        		undirectedGraph.insertEdge(v2, v4, new NumEdge(30));
        //Edge<NumEdge> e7 = 
        		undirectedGraph.insertEdge(v2, v5, new NumEdge(35));
        //Edge<NumEdge> e8 = 
        		undirectedGraph.insertEdge(v3, v4, new NumEdge(40));
        //Edge<NumEdge> e9 = 
        		undirectedGraph.insertEdge(v3, v5, new NumEdge(45));
        //Edge<NumEdge> e10 = 
        		undirectedGraph.insertEdge(v4, v5, new NumEdge(50));
        
        PositionalList<Edge<NumEdge>> result = MinimumSpanningTreeUtil.primJarnik(undirectedGraph);
        assertEquals(4, result.size());
        Position<Edge<NumEdge>> pos = result.first();
        assertEquals(pos.getElement().getElement().getWeight(), e1.getElement().getWeight());
        pos = result.after(pos);
        assertEquals(pos.getElement().getElement().getWeight(), e2.getElement().getWeight());
        pos = result.after(pos);
        assertEquals(pos.getElement().getElement().getWeight(), e3.getElement().getWeight());
        pos = result.after(pos);
        assertEquals(pos.getElement().getElement().getWeight(), e4.getElement().getWeight());
        
        //TODO Complete this test case
    }
    
    /**
     * Test the output of Kruskal's algorithm
     */ 
    @Test
    public void testKruskal() {
    	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<NumEdge> e1 = undirectedGraph.insertEdge(v1, v2, new NumEdge(5));
        Edge<NumEdge> e2 = undirectedGraph.insertEdge(v1, v3, new NumEdge(10));
        Edge<NumEdge> e3 = undirectedGraph.insertEdge(v1, v4, new NumEdge(15));
        Edge<NumEdge> e4 = undirectedGraph.insertEdge(v1, v5, new NumEdge(20));
        //Edge<NumEdge> e5 = 
        		undirectedGraph.insertEdge(v2, v3, new NumEdge(25));
        //Edge<NumEdge> e6 = 
        		undirectedGraph.insertEdge(v2, v4, new NumEdge(30));
        //Edge<NumEdge> e7 = 
        		undirectedGraph.insertEdge(v2, v5, new NumEdge(35));
        //Edge<NumEdge> e8 = 
        		undirectedGraph.insertEdge(v3, v4, new NumEdge(40));
        //Edge<NumEdge> e9 = 
        		undirectedGraph.insertEdge(v3, v5, new NumEdge(45));
        //Edge<NumEdge> e10 = 
        		undirectedGraph.insertEdge(v4, v5, new NumEdge(50));
        
        PositionalList<Edge<NumEdge>> result = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
        assertEquals(4, result.size());
        Position<Edge<NumEdge>> pos = result.first();
        assertEquals(pos.getElement().getElement().getWeight(), e1.getElement().getWeight());
        pos = result.after(pos);
        assertEquals(pos.getElement().getElement().getWeight(), e2.getElement().getWeight());
        pos = result.after(pos);
        assertEquals(pos.getElement().getElement().getWeight(), e3.getElement().getWeight());
        pos = result.after(pos);
        assertEquals(pos.getElement().getElement().getWeight(), e4.getElement().getWeight());
        
        // TODO Complete this test case
    }
    
}