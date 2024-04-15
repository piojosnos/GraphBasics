package org.cyrano.dsa.graph.impl.traversals;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyList;
import org.cyrano.dsa.graph.impl.base.GraphBaseTest;
import org.cyrano.dsa.graph.impl.traversals.serializable.TraceVisitor;
import org.cyrano.dsa.graph.interfaces.Graph;
import org.cyrano.dsa.graph.traversals.impl.bfs.BFS;
import org.junit.Test;

public class SerIntTest extends GraphBaseTest<Integer> {

    @Override
    protected Graph<Integer> createGraph() {
        return new GraphAdjacencyList(false);
    }

    // 0-1-2
    // |/ \|
    // 3   4
    // |\ /|
    // 5-6-7
    @Test
    public void simple_test_1() {
        insertNodes(0, 1, 2, 3, 4, 5, 6, 7);

        insertEdges(0, edge(1), edge(3));
        insertEdges(2, edge(1), edge(4));
        insertEdges(5, edge(3), edge(6));
        insertEdges(7, edge(4), edge(6));

        insertEdges(1, edge(3), edge(4));
        insertEdges(6, edge(3), edge(4));


        BFS<Integer> bfs = new BFS<>();
        TraceVisitor<Integer> traceVisitor = new TraceVisitor<>();
        bfs.traverse(getGraph(), traceVisitor, 0);

        //traceVisitor.createOperationList("test_files/traversals/bfs_1.json");
        traceVisitor.assertTraceList("test_files/traversals/bfs_1.json");
    }

    @Test
    public void simple_test_2() {
        insertNodes(1, 2, 3, 4, 5, 6, 7, 8);

        insertEdges(1, edge(2), edge(4));
        insertEdges(2, edge(3), edge(5), edge(6));
        insertEdges(3, edge(4), edge(8));

        insertEdges(5, edge(7), edge(8));

        insertEdges(6, edge(7));
        insertEdges(7, edge(8));

        BFS<Integer> bfs = new BFS<>();
        TraceVisitor<Integer> traceVisitor = new TraceVisitor<>();
        bfs.traverse(getGraph(), traceVisitor, 4);

        //traceVisitor.createOperationList("test_files/traversals/bfs_2.json");
        traceVisitor.assertTraceList("test_files/traversals/bfs_2.json");
    }

    @Test
    public void simple_test_3() {
        insertNodes(1, 2, 3, 4, 5, 6, 7, 8, 9);

        insertEdges(1, edge(2), edge(3), edge(4));
        insertEdges(2, edge(5));
        insertEdges(3, edge(5), edge(9));

        insertEdges(4, edge(8), edge(9));

        insertEdges(5, edge(6), edge(7));
        insertEdges(6, edge(7), edge(8));

        BFS<Integer> bfs = new BFS<>();
        TraceVisitor<Integer> traceVisitor = new TraceVisitor<>();
        bfs.traverse(getGraph(), traceVisitor, 1);

        //traceVisitor.createOperationList("test_files/traversals/bfs_3.json");
        traceVisitor.assertTraceList("test_files/traversals/bfs_3.json");
    }
}
