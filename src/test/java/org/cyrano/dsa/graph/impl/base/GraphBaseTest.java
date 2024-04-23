package org.cyrano.dsa.graph.impl.base;

import lombok.RequiredArgsConstructor;
import org.cyrano.dsa.graph.impl.base.wrapper.EdgeSourceOrTarget;
import org.cyrano.dsa.graph.impl.base.wrapper.GraphWrapper;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Graph;
import org.junit.Before;

@RequiredArgsConstructor
public abstract class GraphBaseTest<NODE> {

    private GraphWrapper<NODE> wrapper;

    // --------------------------------------------------------------------------------

    protected Graph<NODE> getGraph() {
        return wrapper.getGraph();
    }

    // --------------------------------------------------------------------------------
    // Init
    // --------------------------------------------------------------------------------

    protected abstract Graph<NODE> createGraph();

    @Before
    public void before() {
        wrapper = new GraphWrapper<>(createGraph());
    }

    // --------------------------------------------------------------------------------
    // TDL - Nodes
    // --------------------------------------------------------------------------------

    protected void insertNodes(NODE... nodes) {
        wrapper.insertNodes(nodes);
    }

    // --------------------------------------------------------------------------------

    protected void deleteNodes(NODE... nodes) {
        wrapper.deleteNodes(nodes);
    }

    // --------------------------------------------------------------------------------

    protected void assertNodes(NODE... expNodes) {
        wrapper.assertNodes(expNodes);
    }

    // --------------------------------------------------------------------------------
    // TDL - Edges
    // --------------------------------------------------------------------------------

    protected EdgeSourceOrTarget<NODE> edge(NODE node) {
        return wrapper.edge(node);
    }

    // --------------------------------------------------------------------------------

    protected void insertEdges(NODE source, EdgeSourceOrTarget<NODE>... edgeTargets) {
        wrapper.insertEdges(source, edgeTargets);
    }

    // --------------------------------------------------------------------------------

    protected void deleteEdges(NODE source, NODE... targets) {
        wrapper.deleteEdges(source, targets);
    }

    // --------------------------------------------------------------------------------

    protected void assertAdjacent(
            NODE node, Direction direction, EdgeSourceOrTarget<NODE>... expEdges) {

        wrapper.assertAdjacent(node, direction, expEdges);
    }
}
