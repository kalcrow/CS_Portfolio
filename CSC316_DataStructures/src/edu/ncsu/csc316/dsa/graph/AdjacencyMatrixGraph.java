package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyMatrixGraph is an implementation of the {@link Graph} abstract
 * data type. AdjacencyMatrixGraph maintains a list of vertices in the graph and
 * a list of edges in the graph. In addition, AdjacencyMatrixGraph maintains a
 * 2-dimensional array to store edges based on the endpoints of the edges
 * 
 * The AdjacencyMatrixGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * Skeleton for this code was copied from the CSC316 Workshop 11 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMatrixGraph<V, E> extends EdgeListGraph<V, E> {

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class MatrixVertex extends GraphVertex {

        /** The integer index of the vertex **/
        private int index;

        /**
         * Creates a new adjacency matrix vertex.
         * 
         * @param data       the data to store in the vertex
         */
        public MatrixVertex(V data) {
            super(data);
            index = getVertexIndex();
            //System.out.println("Vertex " + data + " has index " + index); 
        }

        /**
         * Returns the row/column index of the vertex in the matrix
         * 
         * @return the index of the vertex in the matrix
         */
        public int getIndex() {
            return index;
        }
    }
    
    /** 2D array of edges */
    private GraphEdge[][] matrix;
    /** Index to map vertices at */
    private int vertexIndexer;

    /**
     * Creates a new undirected adjacency matrix graph
     */
    public AdjacencyMatrixGraph() {
        this(false);
        vertexIndexer = 0;
    }

    /**
     * Creates a new adjacency matrix graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(boolean directed) {
        super(directed);
        matrix = (GraphEdge[][]) (new AbstractGraph.GraphEdge[0][0]);
    }

    protected Vertex<V> createVertex(V vertexData) {
        return new MatrixVertex(vertexData);
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
    	MatrixVertex validV1 = validate(vertex1);
    	MatrixVertex validV2 = validate(vertex2);
    	
    	return matrix[validV1.getIndex()][validV2.getIndex()];
    }

    private int getVertexIndex() {
        vertexIndexer++;
        return vertexIndexer - 1;
    }

    @SuppressWarnings("unchecked")
    private void growArray() {
        GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
    }

    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[i][v.getIndex()];
            if (e != null) {
                list.addLast(e);
            }
        }
        return list;
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
    	MatrixVertex valid1 = validate(vertex1);
    	MatrixVertex valid2 = validate(vertex2);
    	
    	
    	if (getEdge(vertex1, vertex2) != null) {
    		return null;
    	}
    	
    	GraphEdge edge = new GraphEdge(edgeData, vertex1, vertex2);
    	matrix[valid1.getIndex()][valid2.getIndex()] = edge;
    	
    	if (!isDirected()) {
    		matrix[valid2.getIndex()][valid1.getIndex()] = edge;
    	}
    	
    	PositionalList<Edge<E>> edgeList = (PositionalList<Edge<E>>) this.edges();
    	edgeList.addLast(edge);
    	
    	return edge;
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        growArray();
        return super.insertVertex(vertexData);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }

    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
    	MatrixVertex valid = validate(vertex);
    	
    	int idx = valid.getIndex();
    	List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
    	
    	for (int i = 0; i < matrix.length; i++) {
    		if (matrix[idx][i] != null) {
    			list.addLast(matrix[idx][i]);
    		}
    	}
    	
    	
    	return list;
    }
    
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }

    @Override
    public void removeEdge(Edge<E> edge) {
    	GraphEdge valid = validate(edge);
    	
    	Vertex<V> vertices[] = valid.getEndpoints();
    	int idx1 = validate(vertices[0]).getIndex();
    	int idx2 = validate(vertices[1]).getIndex();
    	
    	matrix[idx1][idx2] = null;
    	
    	if (!isDirected()) {
    		matrix[idx2][idx1] = null;
    	}
    	
    	//remove from edgeList
    	PositionalList<Edge<E>> edgeList = (PositionalList<Edge<E>>) this.edges();
    	Iterable<Position<Edge<E>>> positionIterator = edgeList.positions();
    	for (Position<Edge<E>> e: positionIterator) {
    		if (e.getElement() == edge) {
    			edgeList.remove(e);
    			return;
    		}
    	}
    	
    }
    
    /**
     * Safely casts a Vertex to a graph vertex
     * 
     * @param v Vertex to validate
     * @return a graph vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid graph vertex
     */
    private MatrixVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMatrixGraph.MatrixVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency matrix vertex.");
        }
        return (MatrixVertex) v;
    }
}