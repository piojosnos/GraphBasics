package org.cyrano.dsa.graph.impl.graph;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyListDirectedDouble;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestDirected;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyListDirectedDoubleTest extends GraphBaseTestDirected {

    protected Graph createGraph() {
        return new GraphAdjacencyListDirectedDouble();
    }
}
