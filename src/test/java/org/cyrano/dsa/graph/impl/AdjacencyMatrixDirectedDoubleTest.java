package org.cyrano.dsa.graph.impl;

import org.cyrano.dsa.graph.impl.adjacencymatrix.GraphAdjacencyMatrix;
import org.cyrano.dsa.graph.impl.base.GraphBaseTestDirectedDouble;
import org.cyrano.dsa.graph.interfaces.Graph;

public class AdjacencyMatrixDirectedDoubleTest extends GraphBaseTestDirectedDouble {

    protected Graph createGraph() {
        return new GraphAdjacencyMatrix(true, 8);
    }
}
