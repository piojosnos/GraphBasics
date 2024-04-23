package org.cyrano.dsa.graph.impl.adjacencymatrix;

import lombok.Getter;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;

import java.util.Iterator;

public class GraphAdjacencyMatrix implements Graph<Integer> {

    private final double[][] adjacencyMatrix;

    private final boolean[] nodes;

    @Getter
    private final boolean directed;

    // --------------------------------------------------------------------------------

    public GraphAdjacencyMatrix(boolean directed, int maxNodes) {
        adjacencyMatrix = new double[maxNodes][];

        for (int i = 0; i < maxNodes; i++) {
            adjacencyMatrix[i] = new double[maxNodes];
        }

        nodes = new boolean[maxNodes];

        this.directed = directed;
    }

    // --------------------------------------------------------------------------------
    // NODES
    // --------------------------------------------------------------------------------

    @Override
    public void insertNode(Integer node) {
        nodeExist(node, false);

        nodes[node] = true;
    }

    @Override
    public void deleteNode(Integer node) {
        nodeExist(node, true);

        nodes[node] = false;

        for (int i = 0; i < nodes.length; i++) {
            adjacencyMatrix[node][i] = 0;
            adjacencyMatrix[i][node] = 0;
        }
    }

    // --------------------------------------------------------------------------------
    // EDGES
    // --------------------------------------------------------------------------------

    @Override
    public void insertEdge(Integer source, Integer target, double weight) {
        nodeExist(source, true);
        nodeExist(target, true);

        adjacencyMatrix[source][target] = weight;

        if (!isDirected()) {
            adjacencyMatrix[target][source] = weight;
        }
    }

    @Override
    public void insertEdge(Integer source, Integer target) {
        insertEdge(source, target, DEFAULT_WEIGHT);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void deleteEdge(Integer source, Integer target) {
        nodeExist(source, true);
        nodeExist(target, true);

        adjacencyMatrix[source][target] = 0;

        if (!isDirected()) {
            adjacencyMatrix[target][source] = 0;
        }
    }

    // --------------------------------------------------------------------------------
    // ITERATOR
    // --------------------------------------------------------------------------------

    @Override
    public Iterator<Integer> nodes() {
        return new IteratorNode(nodes);
    }

    @Override
    public Iterator<Edge<Integer>> adjacent(Integer node, Direction direction) {
        switch (direction) {
            case SOURCE_TO_TARGET:
                return new IteratorEdgeSource(adjacencyMatrix, node);
            case TARGET_TO_SOURCE:
                return new IteratorEdgeTarget(adjacencyMatrix, node);
        }

        throw new IllegalArgumentException(direction.toString());
    }

    // --------------------------------------------------------------------------------
    // MISC
    // --------------------------------------------------------------------------------

    private boolean nodeExist(Integer node, boolean throwExceptionIfNot) {
        if (node < 0 || node >= adjacencyMatrix.length) {
            throw new ArrayIndexOutOfBoundsException(
                    "node < 0 || node >= " + adjacencyMatrix.length + ", node=" + node);
        }

        if (throwExceptionIfNot && !nodes[node]) {
            throw new IllegalArgumentException("node=" + node + ", not found");
        }

        return nodes[node];
    }
}
