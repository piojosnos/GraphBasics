package org.cyrano.dsa.graph.impl.graph;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyListSimple;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestUndirected;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyListSimpleUndirectedTest extends GraphBaseTestUndirected {

    protected Graph createGraph() {
        return new GraphAdjacencyListSimple(false);
    }
}
