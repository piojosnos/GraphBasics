package org.cyrano.dsa.graph.interfaces;

import java.util.Iterator;

public interface Graph {

    int DEFAULT_WEIGHT = 1;

    // --------------------------------------------------------------------------------

    void insertNode(int node);

    void deleteNode(int node);

    // --------------------------------------------------------------------------------

    void insertEdge(int source, int target, double weight);

    void insertEdge(int source, int target);

    void deleteEdge(int source, int target);

    // --------------------------------------------------------------------------------

    Iterator<Integer> nodes();

    Iterator<Edge> adjacent(int node, Direction direction);

    // --------------------------------------------------------------------------------

    boolean isDirected();
}
