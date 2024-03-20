package org.cyrano.dsa.graph.interfaces;

import java.util.Iterator;

public interface Graph<NODE> {

    int DEFAULT_WEIGHT = 1;

    // --------------------------------------------------------------------------------

    void insertNode(NODE node);

    void deleteNode(NODE node);

    // --------------------------------------------------------------------------------

    void insertEdge(NODE source, NODE target, double weight);

    void insertEdge(NODE source, NODE target);

    void deleteEdge(NODE source, NODE target);

    // --------------------------------------------------------------------------------

    Iterator<NODE> nodes();

    Iterator<Edge<NODE>> adjacent(NODE node, Direction direction);

    // --------------------------------------------------------------------------------

    boolean isDirected();
}
