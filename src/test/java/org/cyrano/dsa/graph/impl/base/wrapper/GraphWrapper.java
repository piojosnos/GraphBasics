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
public class GraphWrapper {

    private final Graph graph;

    // --------------------------------------------------------------------------------
    // TDL - Nodes
    // --------------------------------------------------------------------------------

    public void insertNodes(Integer... nodes) {
        for (Integer node : nodes) {
            graph.insertNode(node);
        }
    }

    // --------------------------------------------------------------------------------

    public void assertNodes(Integer... expNodes) {
        List<Integer> actNodes = nodes();

        assertEquals(expNodes.length, actNodes.size());

        for (Integer node : expNodes) {
            assertTrue("Expected to contain " + node, actNodes.contains(node));
        }
    }

    // --------------------------------------------------------------------------------

    public List<Integer> nodes() {
        List<Integer> ret = new ArrayList<>();

        Iterator<Integer> itt = graph.nodes();

        while (itt.hasNext()) {
            ret.add(itt.next());
        }

        return ret;
    }

    // --------------------------------------------------------------------------------
    // TDL - Edges
    // --------------------------------------------------------------------------------

    public EdgeSourceOrTarget edge(int node, int weight) {
        return new EdgeSourceOrTarget(node, weight);
    }

    public EdgeSourceOrTarget edge(int node) {
        return edge(node, Graph.DEFAULT_WEIGHT);
    }

    // --------------------------------------------------------------------------------

    public void insertEdges(Integer source, EdgeSourceOrTarget... edgeTargets) {
        for (EdgeSourceOrTarget edgeTarget : edgeTargets) {
            graph.insertEdge(source, edgeTarget.node(), edgeTarget.weight());
        }
    }

    // --------------------------------------------------------------------------------

    public void assertAdjacent(Integer node, Direction direction, EdgeSourceOrTarget... expEdges) {
        List<Edge> actEdges = adjacent(node, direction);

        assertEquals(expEdges.length, actEdges.size());

        for (EdgeSourceOrTarget edgeSourceOrTarget : expEdges) {
            Edge edge;

            switch (direction) {
                case SOURCE_TO_TARGET:
                    edge = new Edge(node, edgeSourceOrTarget.node(), edgeSourceOrTarget.weight());
                    break;
                case TARGET_TO_SOURCE:
                    edge = new Edge(edgeSourceOrTarget.node(), node, edgeSourceOrTarget.weight());
                    break;
                default:
                    throw new IllegalArgumentException(direction.toString());
            }

            assertTrue("Expected to contain " + edge, actEdges.contains(edge));
        }
    }

    // --------------------------------------------------------------------------------

    public List<Edge> adjacent(int node, Direction direction) {
        List<Edge> ret = new ArrayList<>();

        Iterator<Edge> itt = graph.adjacent(node, direction);

        while (itt.hasNext()) {
            ret.add(itt.next());
        }

        return ret;
    }
}
