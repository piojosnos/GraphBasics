package org.cyrano.dsa.graph.impl.graph;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyList;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestDirected;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyListDirectedTest extends GraphBaseTestDirected {

    protected Graph createGraph() {
        return new GraphAdjacencyList(true);
    }
}
