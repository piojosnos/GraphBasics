package org.cyrano.dsa.graph.impl.adjacencymatrix;

import org.cyrano.dsa.graph.interfaces.Edge;

import java.util.Iterator;

public class IteratorEdgeTarget implements Iterator<Edge> {

    private final double[][] adjacencyMatrix;

    private final int target;

    private int hasNextIndex = -1;
    private int curNextIndex = -1;

    // --------------------------------------------------------------------------------

    public IteratorEdgeTarget(double[][] adjacencyMatrix, int target) {
        this.adjacencyMatrix = adjacencyMatrix;

        this.target = target;

        advancePointers();
    }

    // --------------------------------------------------------------------------------

    private void advancePointers() {
        hasNextIndex++;

        for (int j = hasNextIndex; j < adjacencyMatrix.length; j++) {
            if (adjacencyMatrix[j][target] != 0) {
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
    public Edge next() {
        Edge ret = new Edge(curNextIndex, target, adjacencyMatrix[curNextIndex][target]);

        advancePointers();

        return ret;
    }
}
