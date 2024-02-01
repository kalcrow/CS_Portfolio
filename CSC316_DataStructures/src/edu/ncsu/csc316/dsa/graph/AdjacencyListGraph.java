package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyListGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyListGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyListGraph vertices each maintain
 * lists of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyListGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * Skeleton for this code copied from CSC316 Workshop 11 guide
 * 
 * @author Dr. King
 * @author Kal Corwin
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyListGraph<V, E> extends EdgeListGraph<V, E> {

    /**
     * Represents an edge in an AdjacencyListGraph
     * 
     * @author Dr. King
     *
     */
    private class ALEdge extends GraphEdge {

        /** The position of the edge in a vertex's outgoing edge list */
        private Position<Edge<E>> outgoingListPosition;

        /** The position of the edge in a vertex's incoming edge list */
        private Position<Edge<E>> incomingListPosition;

        /**
         * Creates a new adjacency list edge
         * 
         * @param element the data to store in the edge
         * @param v1      an endpoint vertex
         * @param v2      an endpoint vertex
         */
        public ALEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            super(element, v1, v2);
        }

        /**
         * Returns the position of the edge in the associated vertex's incoming edge
         * list
         * 
         * @return the position of the edge in the associated vertex's incoming edge
         *         list
         */
        public Position<Edge<E>> getIncomingListPosition() {
            return incomingListPosition;
        }

        /**
         * Returns the position of the edge in the associated vertex's outgoing edge
         * list
         * 
         * @return the position of the edge in the associated vertex's outgoing edge
         *         list
         */
        public Position<Edge<E>> getOutgoingListPosition() {
            return outgoingListPosition;
        }

        /**
         * Sets the edge's position in the associated vertex's incoming edge list
         * 
         * @param incomingListPosition the position of the edge in the associated
         *                             vertex's incoming edge list
         */
        public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
            this.incomingListPosition = incomingListPosition;
        }

        /**
         * Sets the edge's position in the associated vertex's outgoing edge list
         * 
         * @param outgoingListPosition the position of the edge in the associated
         *                             vertex's outgoing edge list
         */
        public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
            this.outgoingListPosition = outgoingListPosition;
        }
    }

    /**
     * Represents a vertex in an AdjacencyListGraph
     * 
     * @author Dr. King
     *
     */
    private class ALVertex extends GraphVertex {

        /** A positional list of outgoing edges */
        private PositionalList<Edge<E>> outgoing;

        /** A positional list of incoming edges */
        private PositionalList<Edge<E>> incoming;

        /**
         * Creates a new adjacency list vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public ALVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new PositionalLinkedList<Edge<E>>();
            if (isDirected) {
                incoming = new PositionalLinkedList<Edge<E>>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a positional list of incomingEdges to the vertex. For an undirected
         * graph, returns the same as getOutgoing()
         * 
         * @return a positional list of incoming edges to the vertex
         */
        public PositionalList<Edge<E>> getIncoming() {
            return incoming;
        }

        /**
         * Returns a positional list of outgoingEdges from the vertex. For an undirected
         * graph, returns the same as getIncoming()
         * 
         * @return a positional list of outgoing edges from the vertex
         */
        public PositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }
    }

    /**
     * Creates a new undirected adjacency list graph
     */
    public AdjacencyListGraph() {
        this(false);
    }

    /**
     * Creates a new adjacency list graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    public AdjacencyListGraph(boolean directed) {
        super(directed);
    }

    protected Edge<E> createEdge(E data, Vertex<V> vertex1, Vertex<V> vertex2) {
        return new ALEdge(data, vertex1, vertex2);
    }

    protected Vertex<V> createVertex(V vertexData) {
        return new ALVertex(vertexData, isDirected());
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        ALVertex v1 = validate(vertex1);
        ALVertex v2 = validate(vertex2);
        for(Edge<E> edge : v1.getOutgoing()) {
            ALEdge e = validate(edge);
            Vertex<V>[] ends = e.getEndpoints();
            if (!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return e;
            }
            if (ends[0] == v1 && ends[1] == v2) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
    	ALVertex validVertex = validate(vertex);
    	return validVertex.getIncoming();
    }
    
    @Override
    public int inDegree(Vertex<V> vertex) {
    	ALVertex validVertex = validate(vertex);
    	return validVertex.getIncoming().size();
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
    	ALVertex validV1 = validate(vertex1);
    	ALVertex validV2 = validate(vertex2);
    	
    	
    	
    	Edge<E> temp = getEdge(vertex1, vertex2);
    	
    	if (temp != null) {
    		return null;
    	}
    	
    	ALEdge edge = new ALEdge(edgeData, vertex1, vertex2);
    	edge.setOutgoingListPosition(validV1.getOutgoing().addLast(edge));
        edge.setIncomingListPosition(validV2.getIncoming().addLast(edge));
    	
    	PositionalList<Edge<E>> edgeList = (PositionalList<Edge<E>>) this.edges();
    	edgeList.addLast(edge);
    	
    	return edge;
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
    	ALVertex validVertex = validate(vertex);
    	return validVertex.getOutgoing().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        ALVertex validVertex = validate(vertex);
    	return validVertex.getOutgoing();
    }

    @Override
    public void removeEdge(Edge<E> edge) {
        
    	ALEdge validEdge = validate(edge);
    	Position<Graph.Edge<E>> posIn = validEdge.getIncomingListPosition();
    	Position<Graph.Edge<E>> posOut = validEdge.getOutgoingListPosition();
    	Vertex<V>[] vertices = validEdge.getEndpoints();
    	ALVertex v1 = validate(vertices[0]);
    	ALVertex v2 = validate(vertices[1]);
    	
    	v1.getIncoming().remove(posIn);
    	v1.getOutgoing().remove(posOut);
    	v2.getIncoming().remove(posIn);
    	v2.getOutgoing().remove(posOut);


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
     * Safely casts an Edge to an adjacency list edge
     * 
     * @param e Edge to validate
     * @return an adjacency list edge representation of the given Edge
     * @throws IllegalArgumentException if the edge is not a valid adjacency list
     *                                  edge
     */
    protected ALEdge validate(Edge<E> e) {
        if (!(e instanceof AdjacencyListGraph.ALEdge)) {
            throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
        }
        return (ALEdge) e;
    }

    /**
     * Safely casts a Vertex to an adjacency list vertex
     * 
     * @param v Vertex to validate
     * @return an adjacency list vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency list
     *                                  vertex
     */
    protected ALVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyListGraph.ALVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
        }
        return (ALVertex) v;
    }
}