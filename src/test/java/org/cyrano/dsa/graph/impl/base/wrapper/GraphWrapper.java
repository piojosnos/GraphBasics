package org.cyrano.dsa.graph.impl.base.wrapper;

import lombok.RequiredArgsConstructor;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RequiredArgsConstructor
public class GraphWrapper<NODE> {

    private final Graph<NODE> graph;

    // --------------------------------------------------------------------------------
    // TDL - Nodes
    // --------------------------------------------------------------------------------

    public void insertNodes(NODE... nodes) {
        for (NODE node : nodes) {
            graph.insertNode(node);
        }
    }

    // --------------------------------------------------------------------------------

    public void assertNodes(NODE... expNodes) {
        List<NODE> actNodes = nodes();

        assertEquals(expNodes.length, actNodes.size());

        for (NODE node : expNodes) {
            assertTrue("Expected to contain " + node, actNodes.contains(node));
        }
    }

    // --------------------------------------------------------------------------------

    public List<NODE> nodes() {
        List<NODE> ret = new ArrayList<>();

        Iterator<NODE> itt = graph.nodes();

        while (itt.hasNext()) {
            ret.add(itt.next());
        }

        return ret;
    }

    // --------------------------------------------------------------------------------
    // TDL - Edges
    // --------------------------------------------------------------------------------

    public EdgeSourceOrTarget<NODE> edge(NODE node, int weight) {
        return new EdgeSourceOrTarget<>(node, weight);
    }

    public EdgeSourceOrTarget<NODE> edge(NODE node) {
        return edge(node, Graph.DEFAULT_WEIGHT);
    }

    // --------------------------------------------------------------------------------

    public void insertEdges(NODE source, EdgeSourceOrTarget<NODE>... edgeTargets) {
        for (EdgeSourceOrTarget<NODE> edgeTarget : edgeTargets) {
            graph.insertEdge(source, edgeTarget.node(), edgeTarget.weight());
        }
    }

    // --------------------------------------------------------------------------------

    public void assertAdjacent(
            NODE node, Direction direction, EdgeSourceOrTarget<NODE>... expEdges) {

        List<Edge<NODE>> actEdges = adjacent(node, direction);

        assertEquals(expEdges.length, actEdges.size());

        for (EdgeSourceOrTarget<NODE> edgeSourceOrTarget : expEdges) {
            Edge<NODE> edge;

            switch (direction) {
                case SOURCE_TO_TARGET:
                    edge = new Edge<>(node, edgeSourceOrTarget.node(), edgeSourceOrTarget.weight());
                    break;
                case TARGET_TO_SOURCE:
                    edge = new Edge<>(edgeSourceOrTarget.node(), node, edgeSourceOrTarget.weight());
                    break;
                default:
                    throw new IllegalArgumentException(direction.toString());
            }

            assertTrue("Expected to contain " + edge, actEdges.contains(edge));
        }
    }

    // --------------------------------------------------------------------------------

    public List<Edge<NODE>> adjacent(NODE node, Direction direction) {
        List<Edge<NODE>> ret = new ArrayList<>();

        Iterator<Edge<NODE>> itt = graph.adjacent(node, direction);

        while (itt.hasNext()) {
            ret.add(itt.next());
        }

        return ret;
    }

    // --------------------------------------------------------------------------------

    public Graph<NODE> getGraph() {
        return graph;
    }
}
