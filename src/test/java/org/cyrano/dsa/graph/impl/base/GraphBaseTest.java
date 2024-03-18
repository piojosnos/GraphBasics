package org.cyrano.dsa.graph.impl.base;

import lombok.RequiredArgsConstructor;
import org.cyrano.dsa.graph.impl.base.wrapper.EdgeSourceOrTarget;
import org.cyrano.dsa.graph.impl.base.wrapper.GraphWrapper;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Graph;
import org.junit.Before;

@RequiredArgsConstructor
public abstract class GraphBaseTest {

    private GraphWrapper wrapper;

    // --------------------------------------------------------------------------------
    // Init
    // --------------------------------------------------------------------------------

    protected abstract Graph createGraph();

    @Before
    public void before() {
        wrapper = new GraphWrapper(createGraph());
    }

    // --------------------------------------------------------------------------------
    // TDL - Nodes
    // --------------------------------------------------------------------------------

    protected void insertNodes(Integer... nodes) {
        wrapper.insertNodes(nodes);
    }

    // --------------------------------------------------------------------------------

    protected void assertNodes(Integer... expNodes) {
        wrapper.assertNodes(expNodes);
    }

    // --------------------------------------------------------------------------------
    // TDL - Edges
    // --------------------------------------------------------------------------------

    protected EdgeSourceOrTarget edge(int node) {
        return wrapper.edge(node);
    }

    // --------------------------------------------------------------------------------

    protected void insertEdges(Integer source, EdgeSourceOrTarget... edgeTargets) {
        wrapper.insertEdges(source, edgeTargets);
    }

    // --------------------------------------------------------------------------------

    protected void assertAdjacent(
            Integer node, Direction direction, EdgeSourceOrTarget... expEdges) {

        wrapper.assertAdjacent(node, direction, expEdges);
    }
}
