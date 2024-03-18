package org.cyrano.dsa.graph.impl.adjacencymatrix;

import java.util.Iterator;

public class IteratorNode implements Iterator<Integer> {

    private final boolean[] nodes;

    private int hasNextIndex = -1;
    private int curNextIndex = -1;

    // --------------------------------------------------------------------------------

    public IteratorNode(boolean[] nodes) {
        this.nodes = nodes;

        advancePointers();
    }

    // --------------------------------------------------------------------------------

    private void advancePointers() {
        hasNextIndex++;

        for (int j = hasNextIndex; j < nodes.length; j++) {
            if (nodes[j]) {
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
    public Integer next() {
        int ret = curNextIndex;

        advancePointers();

        return ret;
    }
}
