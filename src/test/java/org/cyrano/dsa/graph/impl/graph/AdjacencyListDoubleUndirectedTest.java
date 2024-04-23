package org.cyrano.dsa.graph.impl.graph;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyListDouble;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestUndirected;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyListDoubleUndirectedTest extends GraphBaseTestUndirected {

    protected Graph createGraph() {
        return new GraphAdjacencyListDouble(false);
    }
}
