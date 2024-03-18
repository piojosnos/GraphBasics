package org.cyrano.dsa.graph.impl;

import org.cyrano.dsa.graph.impl.adjacencymatrix.GraphAdjacencyMatrix;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestDirected;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyMatrixDirectedTest extends GraphBaseTestDirected {

    protected Graph createGraph() {
        return new GraphAdjacencyMatrix(true, 8);
    }
}
