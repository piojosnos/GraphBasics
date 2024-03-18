package org.cyrano.dsa.graph.impl;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyList;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestUndirected;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyListUndirectedTest extends GraphBaseTestUndirected {

    protected Graph createGraph() {
        return new GraphAdjacencyList(false);
    }
}
