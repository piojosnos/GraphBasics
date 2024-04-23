package org.cyrano.dsa.graph.impl.adjacencylist;

import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GraphAdjacencyListSimpleEdgeIterator<NODE> implements Iterator<Edge<NODE>> {

    private final Direction direction;

    private final NODE node;

    private final Iterator<Edge<NODE>> itt;

    private Edge<NODE> next;

    // --------------------------------------------------------------------------------

    public GraphAdjacencyListSimpleEdgeIterator(Direction direction, NODE node, Iterator<Edge<NODE>> itt) {
        this.direction = direction;
        this.node = node;
        this.itt = itt;

        advancePointers();
    }

    // --------------------------------------------------------------------------------

    private void advancePointers() {
        next = null;

        while (itt.hasNext()) {
            Edge<NODE> curr = itt.next();

            if (direction == Direction.SOURCE_TO_TARGET) {
                if (curr.getSource() == node) {
                    next = curr;
                    break;
                }
            }

            if (direction == Direction.TARGET_TO_SOURCE) {
                if (curr.getTarget() == node) {
                    next = curr;
                    break;
                }
            }
        }
    }

    // --------------------------------------------------------------------------------

    @Override
    public boolean hasNext() {
        return next != null;
    }

    // --------------------------------------------------------------------------------

    @Override
    public Edge<NODE> next() {
        if (next == null) {
            throw new NoSuchElementException();
        }

        Edge<NODE> ret = next;

        advancePointers();

        return ret;
    }
}
