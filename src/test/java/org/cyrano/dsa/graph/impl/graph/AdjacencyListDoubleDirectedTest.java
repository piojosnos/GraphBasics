package org.cyrano.dsa.graph.impl.graph;

import org.cyrano.dsa.graph.impl.adjacencylist.DoubleGraphAdjacencyListDirected;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestDirected;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyListDoubleDirectedTest extends GraphBaseTestDirected {

    protected Graph createGraph() {
        return new DoubleGraphAdjacencyListDirected();
    }
}
