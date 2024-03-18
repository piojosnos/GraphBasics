package org.cyrano.dsa.graph.impl;

import org.cyrano.dsa.graph.impl.adjacencymatrix.GraphAdjacencyMatrix;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestUndirected;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyMatrixUndirectedTest extends GraphBaseTestUndirected {

    protected Graph createGraph() {
        return new GraphAdjacencyMatrix(false, 8);
    }
}
