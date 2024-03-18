package org.cyrano.dsa.graph.impl;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyListDirectedDouble;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestDirectedDouble;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyListDirectedDoubleTest extends GraphBaseTestDirectedDouble {

    protected Graph createGraph() {
        return new GraphAdjacencyListDirectedDouble();
    }
}
