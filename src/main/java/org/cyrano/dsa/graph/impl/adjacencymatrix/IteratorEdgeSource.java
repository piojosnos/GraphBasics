package org.cyrano.dsa.graph.impl.adjacencymatrix;

import org.cyrano.dsa.graph.interfaces.Edge;

import java.util.Iterator;

public class IteratorEdgeSource implements Iterator<Edge<Integer>> {

    private final double[][] adjacencyMatrix;

    private final int source;

    private int hasNextIndex = -1;
    private int curNextIndex = -1;

    // --------------------------------------------------------------------------------

    public IteratorEdgeSource(double[][] adjacencyMatrix, int source) {
        this.adjacencyMatrix = adjacencyMatrix;

        this.source = source;

        advancePointers();
    }

    // --------------------------------------------------------------------------------

    private void advancePointers() {
        hasNextIndex++;

        for (int j = hasNextIndex; j < adjacencyMatrix.length; j++) {
            if (adjacencyMatrix[source][j] != 0) {
                hasNextIndex = j;
                curNextIndex = j;

                return;
            }
        }
    }

    // --------------------------------------------------------------------------------

    @Override
    public boolean hasNext() {
        return hasNextIndex == curNextIndex;
    }

    // --------------------------------------------------------------------------------

    @Override
    public Edge<Integer> next() {
        Edge<Integer> ret = new Edge<>(source, curNextIndex, adjacencyMatrix[source][curNextIndex]);

        advancePointers();

        return ret;
    }
}
