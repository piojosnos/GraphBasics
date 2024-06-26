package org.cyrano.dsa.graph.impl.graph;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyListSimple;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestDirected;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyListSimpleDirectedTest extends GraphBaseTestDirected {

    protected Graph createGraph() {
        return new GraphAdjacencyListSimple(true);
    }
}
