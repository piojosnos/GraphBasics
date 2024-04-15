package org.cyrano.dsa.graph.impl.traversals;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyList;
import org.cyrano.dsa.graph.impl.base.GraphBaseTest;
import org.cyrano.dsa.graph.impl.traversals.serializable.TraceVisitor;
import org.cyrano.dsa.graph.interfaces.Graph;
import org.cyrano.dsa.graph.traversals.impl.bfs.BFS;
import org.junit.Test;

public class SerStrTest extends GraphBaseTest<String> {

    @Override
    protected Graph<String> createGraph() {
        return new GraphAdjacencyList(false);
    }


    // 0-1-2
    // |/ \|
    // 3   4
    // |\ /|
    // 5-6-7
    @Test
    public void simple_test_1() {
        insertNodes("0", "1", "2", "3", "4", "5", "6", "7");

        insertEdges("0", edge("1"), edge("3"));
        insertEdges("2", edge("1"), edge("4"));
        insertEdges("5", edge("3"), edge("6"));
        insertEdges("7", edge("4"), edge("6"));

        insertEdges("1", edge("3"), edge("4"));
        insertEdges("6", edge("3"), edge("4"));


        BFS<String> bfs = new BFS<>();
        TraceVisitor<String> traceVisitor = new TraceVisitor<>();
        bfs.traverse(getGraph(), traceVisitor, "0");

//        GraphSerializableNo<String> graphSerializable2A = new GraphSerializableNo<>(getGraph());
//        graphSerializable2A.save("test_files/traversals/dfs_2a_graph.json");
//        GraphSerializableNo<String> graphSerializable2B = new GraphSerializableNo<>(getGraph());
//        graphSerializable2B.load("test_files/traversals/dfs_2a_graph.json");
//        Graph<String> graph2 = new GraphAdjacencyList(false);
//        graphSerializable2B.buildGraph(graph2);
//        GraphSerializableNo<String> graphSerializable2C = new GraphSerializableNo<>(graph2);
//        graphSerializable2C.save("test_files/traversals/dfs_2b_graph.json");

        //traceVisitor.createOperationList("test_files/traversals/bfs_1.json");
        //traceVisitor.assertOperationList("test_files/traversals/bfs_1.json");
    }
}
