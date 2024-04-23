package org.cyrano.dsa.graph.impl.traversals;

import org.cyrano.dsa.graph.impl.adjacencylist.SimpleGraphAdjacencyList;
import org.cyrano.dsa.graph.impl.base.GraphBaseTest;
import org.cyrano.dsa.graph.impl.traversals.serializable.GraphSerializer;
import org.cyrano.dsa.graph.impl.traversals.serializable.NodeMetadataList;
import org.cyrano.dsa.graph.impl.traversals.serializable.TraceVisitor;
import org.cyrano.dsa.graph.interfaces.Graph;
import org.cyrano.dsa.graph.traversals.impl.dfs.DFSRec;
import org.junit.Test;

public class DFSTest extends GraphBaseTest<Integer> {

    @Override
    protected Graph<Integer> createGraph() {
        return new SimpleGraphAdjacencyList(false);
    }

    @Test
    public void simple_test_1() {
        GraphSerializer<Integer> graphSerializer = new GraphSerializer<>();
        //graphSerializer.save("test_files/graphs/graph_1.json", getGraph());
        graphSerializer.load("test_files/graphs/graph_1.json", getGraph());

        DFSRec<Integer> dfs = new DFSRec<>();
        TraceVisitor<Integer> traceVisitor = new TraceVisitor<>();
        dfs.traverse(getGraph(), traceVisitor, 0);

        //traceVisitor.createTraceList("test_files/traversals/dfs_1_trace.json");
        traceVisitor.assertTraceList("test_files/traversals/dfs_1_trace.json");

        NodeMetadataList<Integer> nodeMetadataList = new NodeMetadataList<>(dfs.getNodeNodeMetadataMap());
        //nodeMetadataList.createNodeMetadataMapSer("test_files/traversals/dfs_1_map.json");
        nodeMetadataList.assertNodeMetadataMapSer("test_files/traversals/dfs_1_map.json");
    }

    @Test
    public void simple_test_2() {
        GraphSerializer<Integer> graphSerializer = new GraphSerializer<>();
        //graphSerializer.save("test_files/graphs/graph_2.json", getGraph());
        graphSerializer.load("test_files/graphs/graph_2.json", getGraph());

        DFSRec<Integer> dfs = new DFSRec<>();
        TraceVisitor<Integer> traceVisitor = new TraceVisitor<>();
        dfs.traverse(getGraph(), traceVisitor, 4);

        //traceVisitor.createTraceList("test_files/traversals/dfs_2_trace.json");
        traceVisitor.assertTraceList("test_files/traversals/dfs_2_trace.json");

        NodeMetadataList<Integer> nodeMetadataList = new NodeMetadataList<>(dfs.getNodeNodeMetadataMap());
        //nodeMetadataList.createNodeMetadataMapSer("test_files/traversals/dfs_2_map.json");
        nodeMetadataList.assertNodeMetadataMapSer("test_files/traversals/dfs_2_map.json");
    }

    @Test
    public void simple_test_3() {
        GraphSerializer<Integer> graphSerializer = new GraphSerializer<>();
        //graphSerializer.save("test_files/graphs/graph_3.json", getGraph());
        graphSerializer.load("test_files/graphs/graph_3.json", getGraph());

        DFSRec<Integer> dfs = new DFSRec<>();
        TraceVisitor<Integer> traceVisitor = new TraceVisitor<>();
        dfs.traverse(getGraph(), traceVisitor, 1);

        //traceVisitor.createTraceList("test_files/traversals/dfs_3_trace.json");
        traceVisitor.assertTraceList("test_files/traversals/dfs_3_trace.json");

        NodeMetadataList<Integer> nodeMetadataList = new NodeMetadataList<>(dfs.getNodeNodeMetadataMap());
        //nodeMetadataList.createNodeMetadataMapSer("test_files/traversals/dfs_3_map.json");
        nodeMetadataList.assertNodeMetadataMapSer("test_files/traversals/dfs_3_map.json");
    }
}
