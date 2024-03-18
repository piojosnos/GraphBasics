package org.cyrano.dsa.graph.impl.base;

import org.junit.Test;

import static org.cyrano.dsa.graph.interfaces.Direction.SOURCE_TO_TARGET;

public abstract class GraphBaseTestDirected extends GraphBaseTest {

    @Test
    public void no_nodes() {
        assertNodes();
    }

    // --------------------------------------------------------------------------------

    @Test
    public void iterates_nodes_0_2_4_6() {
        insertNodes(0, 2, 4, 6);
        assertNodes(0, 2, 4, 6);
    }

    @Test
    public void iterates_nodes_1_3_5_7() {
        insertNodes(1, 3, 5, 7);
        assertNodes(1, 3, 5, 7);
    }

    @Test
    public void iterates_nodes_0_1_6_7() {
        insertNodes(0, 1, 6, 7);
        assertNodes(0, 1, 6, 7);
    }

    // --------------------------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void fails_when_adding_edge_for_nodes_that_doesnt_exist() {
        insertEdges(0, edge(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fails_when_adding_edge_for_source_that_doesnt_exist() {
        insertNodes(1);
        insertEdges(0, edge(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fails_when_adding_edge_for_target_that_doesnt_exist() {
        insertNodes(0);
        insertEdges(0, edge(1));
    }

    // --------------------------------------------------------------------------------

    @Test
    public void one_node_no_adjacent_0() {
        insertNodes(0);
        assertAdjacent(0, SOURCE_TO_TARGET);
    }

    @Test
    public void one_node_no_adjacent_7() {
        insertNodes(7);
        assertAdjacent(7, SOURCE_TO_TARGET);
    }

    @Test
    public void two_node_no_adjacent() {
        insertNodes(1, 6);
        assertAdjacent(1, SOURCE_TO_TARGET);
        assertAdjacent(6, SOURCE_TO_TARGET);
    }

    // --------------------------------------------------------------------------------

    // 0 --> 1
    @Test
    public void adds_two_nodes_and_one_edge() {
        insertNodes(0, 1);
        insertEdges(0, edge(1));

        assertNodes(0, 1);

        assertAdjacent(0, SOURCE_TO_TARGET, edge(1));

        assertAdjacent(1, SOURCE_TO_TARGET);
    }

    // 0 <-> 1
    @Test
    public void adds_two_nodes_and_one_edge_and_edges_back() {
        insertNodes(0, 1);
        insertEdges(0, edge(1));
        insertEdges(1, edge(0));

        assertNodes(0, 1);

        assertAdjacent(0, SOURCE_TO_TARGET, edge(1));

        assertAdjacent(1, SOURCE_TO_TARGET, edge(0));
    }

    // --------------------------------------------------------------------------------

    //    /> 1
    // 0 --> 2
    //    \> 3
    @Test
    public void adds_a_node_with_three_other_nodes_and_edges() {
        insertNodes(0, 1, 2, 3);
        insertEdges(0, edge(1), edge(2), edge(3));

        assertNodes(0, 1, 2, 3);

        assertAdjacent(0, SOURCE_TO_TARGET, edge(1), edge(2), edge(3));

        assertAdjacent(1, SOURCE_TO_TARGET);
        assertAdjacent(2, SOURCE_TO_TARGET);
        assertAdjacent(3, SOURCE_TO_TARGET);
    }

    //    /> 1
    // 0 <-> 2
    //    \> 3
    @Test
    public void adds_a_node_with_three_other_nodes_and_edge_and_edges_back() {
        insertNodes(0, 1, 2, 3);
        insertEdges(0, edge(1), edge(2), edge(3));

        insertEdges(1, edge(0));
        insertEdges(2, edge(0));
        insertEdges(3, edge(0));

        assertNodes(0, 1, 2, 3);

        assertAdjacent(0, SOURCE_TO_TARGET, edge(1), edge(2), edge(3));

        assertAdjacent(1, SOURCE_TO_TARGET, edge(0));
        assertAdjacent(2, SOURCE_TO_TARGET, edge(0));
        assertAdjacent(3, SOURCE_TO_TARGET, edge(0));
    }

    // --------------------------------------------------------------------------------

    // 0-1
    // |X|
    // 2-3
    @Test
    public void creates_all_connected_four_node_graph() {
        insertNodes(0, 1, 2, 3);

        insertEdges(0, edge(1), edge(2), edge(3));
        insertEdges(1, edge(2), edge(3));
        insertEdges(2, edge(3));

        assertNodes(0, 1, 2, 3);

        assertAdjacent(0, SOURCE_TO_TARGET, edge(1), edge(2), edge(3));
        assertAdjacent(1, SOURCE_TO_TARGET, edge(2), edge(3));
        assertAdjacent(2, SOURCE_TO_TARGET, edge(3));
        assertAdjacent(3, SOURCE_TO_TARGET);
    }

    // 0-1
    // |X|
    // 2-3
    @Test
    public void creates_all_connected_four_node_graph_and_edges_back() {
        insertNodes(0, 1, 2, 3);

        insertEdges(0, edge(1), edge(2), edge(3));
        insertEdges(1, edge(0), edge(2), edge(3));
        insertEdges(2, edge(0), edge(1), edge(3));
        insertEdges(3, edge(0), edge(1), edge(2));

        assertNodes(0, 1, 2, 3);

        assertAdjacent(0, SOURCE_TO_TARGET, edge(1), edge(2), edge(3));
        assertAdjacent(1, SOURCE_TO_TARGET, edge(0), edge(2), edge(3));
        assertAdjacent(2, SOURCE_TO_TARGET, edge(0), edge(1), edge(3));
        assertAdjacent(3, SOURCE_TO_TARGET, edge(0), edge(1), edge(2));
    }

    // --------------------------------------------------------------------------------

    // 0-1-2
    // |/ \|
    // 3   4
    // |\ /|
    // 5-6-7
    @Test
    public void creates_eight_node_graph() {
        insertNodes(0, 1, 2, 3, 4, 5, 6, 7);

        insertEdges(0, edge(1), edge(3));
        insertEdges(2, edge(1), edge(4));
        insertEdges(5, edge(3), edge(6));
        insertEdges(7, edge(4), edge(6));

        insertEdges(1, edge(3), edge(4));
        insertEdges(6, edge(3), edge(4));

        assertNodes(0, 1, 2, 3, 4, 5, 6, 7);

        assertAdjacent(0, SOURCE_TO_TARGET, edge(1), edge(3));
        assertAdjacent(2, SOURCE_TO_TARGET, edge(1), edge(4));
        assertAdjacent(5, SOURCE_TO_TARGET, edge(3), edge(6));
        assertAdjacent(7, SOURCE_TO_TARGET, edge(4), edge(6));

        assertAdjacent(1, SOURCE_TO_TARGET, edge(3), edge(4));

        assertAdjacent(3, SOURCE_TO_TARGET);
        assertAdjacent(4, SOURCE_TO_TARGET);

        assertAdjacent(6, SOURCE_TO_TARGET, edge(3), edge(4));
    }

    // 0-1-2
    // |/ \|
    // 3   4
    // |\ /|
    // 5-6-7
    @Test
    public void creates_eight_node_graph_and_edges_back() {
        insertNodes(0, 1, 2, 3, 4, 5, 6, 7);

        insertEdges(0, edge(1), edge(3));
        insertEdges(2, edge(1), edge(4));
        insertEdges(5, edge(3), edge(6));
        insertEdges(7, edge(4), edge(6));

        insertEdges(1, edge(0), edge(2), edge(3), edge(4));

        insertEdges(3, edge(0), edge(1), edge(5), edge(6));
        insertEdges(4, edge(1), edge(2), edge(6), edge(7));

        insertEdges(6, edge(3), edge(4), edge(5), edge(7));

        assertNodes(0, 1, 2, 3, 4, 5, 6, 7);

        assertAdjacent(0, SOURCE_TO_TARGET, edge(1), edge(3));
        assertAdjacent(2, SOURCE_TO_TARGET, edge(1), edge(4));
        assertAdjacent(5, SOURCE_TO_TARGET, edge(3), edge(6));
        assertAdjacent(7, SOURCE_TO_TARGET, edge(4), edge(6));

        assertAdjacent(1, SOURCE_TO_TARGET, edge(0), edge(2), edge(3), edge(4));

        assertAdjacent(3, SOURCE_TO_TARGET, edge(0), edge(1), edge(5), edge(6));
        assertAdjacent(4, SOURCE_TO_TARGET, edge(1), edge(2), edge(6), edge(7));

        assertAdjacent(6, SOURCE_TO_TARGET, edge(3), edge(4), edge(5), edge(7));
    }
}
