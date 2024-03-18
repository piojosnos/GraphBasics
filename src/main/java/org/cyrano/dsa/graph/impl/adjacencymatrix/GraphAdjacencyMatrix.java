package org.cyrano.dsa.graph.impl.adjacencymatrix;

import lombok.Getter;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;

import java.util.Iterator;

import static org.cyrano.dsa.graph.impl.Homework.CAN_YOU_IMPLEMENT_THIS_AS_HOMEWORK;

public class GraphAdjacencyMatrix implements Graph {

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

    @Override
    public void insertNode(int node) {
        nodeExist(node, false);

        nodes[node] = true;
    }

    @Override
    public void deleteNode(int node) {
        throw new UnsupportedOperationException(CAN_YOU_IMPLEMENT_THIS_AS_HOMEWORK);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void insertEdge(int source, int target, double weight) {
        nodeExist(source, true);
        nodeExist(target, true);

        adjacencyMatrix[source][target] = weight;

        if (!isDirected()) {
            adjacencyMatrix[target][source] = weight;
        }
    }

    @Override
    public void insertEdge(int source, int target) {
        insertEdge(source, target, DEFAULT_WEIGHT);
    }

    @Override
    public void deleteEdge(int source, int target) {
        throw new UnsupportedOperationException(CAN_YOU_IMPLEMENT_THIS_AS_HOMEWORK);
    }

    // --------------------------------------------------------------------------------

    @Override
    public Iterator<Integer> nodes() {
        return new IteratorNode(nodes);
    }

    @Override
    public Iterator<Edge> adjacent(int node, Direction direction) {
        switch (direction) {
            case SOURCE_TO_TARGET:
                return new IteratorEdgeSource(adjacencyMatrix, node);
            case TARGET_TO_SOURCE:
                return new IteratorEdgeTarget(adjacencyMatrix, node);
        }

        throw new IllegalArgumentException(direction.toString());
    }

    // --------------------------------------------------------------------------------

    private boolean nodeExist(int node, boolean throwExceptionIfNot) {
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
