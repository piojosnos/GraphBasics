package org.cyrano.dsa.graph.impl.base;

import org.junit.Test;

@SuppressWarnings("unchecked")
public abstract class GraphBaseTestUndirected extends GraphBaseTest<Integer> {

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
        assertAdjacentUndirected(0);
    }

    @Test
    public void one_node_no_adjacent_7() {
        insertNodes(7);
        assertAdjacentUndirected(7);
    }

    @Test
    public void two_node_no_adjacent() {
        insertNodes(1, 6);
        assertAdjacentUndirected(1);
        assertAdjacentUndirected(6);
    }

    // --------------------------------------------------------------------------------

    // 0 --- 1
    @Test
    public void adds_two_nodes_and_one_edge() {
        insertNodes(0, 1);
        insertEdges(0, edge(1));

        assertNodes(0, 1);

        assertAdjacentUndirected(0, edge(1));

        assertAdjacentUndirected(1, edge(0));
    }

    // --------------------------------------------------------------------------------

    //    /- 1
    // 0 --- 2
    //    \- 3
    @Test
    public void adds_a_node_with_three_other_nodes_and_edges() {
        insertNodes(0, 1, 2, 3);
        insertEdges(0, edge(1), edge(2), edge(3));

        assertNodes(0, 1, 2, 3);

        assertAdjacentUndirected(0, edge(1), edge(2), edge(3));

        assertAdjacentUndirected(1, edge(0));
        assertAdjacentUndirected(2, edge(0));
        assertAdjacentUndirected(3, edge(0));
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

        assertAdjacentUndirected(0, edge(1), edge(2), edge(3));
        assertAdjacentUndirected(1, edge(0), edge(2), edge(3));
        assertAdjacentUndirected(2, edge(0), edge(1), edge(3));
        assertAdjacentUndirected(3, edge(0), edge(1), edge(2));
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

        assertAdjacentUndirected(0, edge(1), edge(3));
        assertAdjacentUndirected(2, edge(1), edge(4));
        assertAdjacentUndirected(5, edge(3), edge(6));
        assertAdjacentUndirected(7, edge(4), edge(6));

        assertAdjacentUndirected(1, edge(0), edge(2), edge(3), edge(4));

        assertAdjacentUndirected(3, edge(0), edge(1), edge(5), edge(6));
        assertAdjacentUndirected(4, edge(1), edge(2), edge(6), edge(7));

        assertAdjacentUndirected(6, edge(3), edge(4), edge(5), edge(7));
    }

    // --------------------------------------------------------------------------------

    // 1 - 3
    // |   |
    // 2 - 4
    @Test(expected = IllegalArgumentException.class)
    public void fails_when_deletes_node_that_doesnt_exist() {
        insertNodes(1, 2, 3, 4);

        insertEdges(1, edge(3));
        insertEdges(3, edge(4));
        insertEdges(4, edge(2));
        insertEdges(2, edge(1));

        assertNodes(1, 2, 3, 4);

        assertAdjacentUndirected(1, edge(2), edge(3));
        assertAdjacentUndirected(2, edge(1), edge(4));
        assertAdjacentUndirected(3, edge(1), edge(4));
        assertAdjacentUndirected(4, edge(2), edge(3));

        deleteNodes(0);
    }

    // 1 -\-/- 3
    // |   0   |
    // 2 -/-\- 4
    @Test
    public void deletes_center_node() {
        insertNodes(0, 1, 2, 3, 4);

        insertEdges(1, edge(0), edge(3));
        insertEdges(2, edge(0), edge(1));

        insertEdges(3, edge(4));
        insertEdges(4, edge(2));

        insertEdges(0, edge(3));
        insertEdges(0, edge(4));

        assertNodes(0, 1, 2, 3, 4);

        assertAdjacentUndirected(1, edge(0), edge(2), edge(3));
        assertAdjacentUndirected(2, edge(0), edge(1), edge(4));
        assertAdjacentUndirected(3, edge(0), edge(1), edge(4));
        assertAdjacentUndirected(4, edge(0), edge(2), edge(3));

        assertAdjacentUndirected(0, edge(1), edge(2), edge(3), edge(4));

        deleteNodes(0);

        assertNodes(1, 2, 3, 4);

        assertAdjacentUndirected(1, edge(2), edge(3));
        assertAdjacentUndirected(2, edge(1), edge(4));
        assertAdjacentUndirected(3, edge(1), edge(4));
        assertAdjacentUndirected(4, edge(2), edge(3));
    }
}
