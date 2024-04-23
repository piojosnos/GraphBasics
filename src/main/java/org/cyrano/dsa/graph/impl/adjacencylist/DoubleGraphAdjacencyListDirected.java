package org.cyrano.dsa.graph.impl.adjacencylist;

import lombok.RequiredArgsConstructor;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class DoubleGraphAdjacencyListDirected<NODE> implements Graph<NODE> {

    private final Map<NODE, Edges<NODE>> edgesByNode = new HashMap<>();

    // --------------------------------------------------------------------------------

    private static class Edges<NODE> {
        Set<Edge<NODE>> sourceEdges = new HashSet<>();
        Set<Edge<NODE>> targetEdges = new HashSet<>();
    }

    // --------------------------------------------------------------------------------

    @Override
    public void insertNode(NODE node) {
        edgesByNode.put(node, new Edges<>());
    }

    @Override
    public void deleteNode(NODE node) {
        Edges<NODE> edges = getAdjacent(node, true);

        for (Edge<NODE> edge : edges.sourceEdges) {
            Edges<NODE> edgesOtherSide = getAdjacent(edge.getTarget(), true);
            edgesOtherSide.targetEdges.remove(edge);
        }

        for (Edge<NODE> edge : edges.targetEdges) {
            Edges<NODE> edgesOtherSide = getAdjacent(edge.getSource(), true);
            edgesOtherSide.sourceEdges.remove(edge);
        }

        edgesByNode.remove(node);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void insertEdge(NODE source, NODE target, double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("weight=" + weight);
        }

        Edge<NODE> edge = new Edge<>(source, target, weight);

        Edges<NODE> sourceAdjacent = getAdjacent(source, true);
        sourceAdjacent.sourceEdges.add(edge); // --->

        Edges<NODE> targetAdjacent = getAdjacent(target, true);
        targetAdjacent.targetEdges.add(edge); // <---
    }

    @Override
    public void insertEdge(NODE source, NODE target) {
        insertEdge(source, target, DEFAULT_WEIGHT);
    }

    @Override
    public void deleteEdge(NODE source, NODE target) {
        Edge edge = new Edge<>(source, target, -1);

        Edges<NODE> edgesSource = getAdjacent(source, true);
        edgesSource.sourceEdges.remove(edge);

        Edges<NODE> edgesTarget = getAdjacent(target, true);
        edgesTarget.targetEdges.remove(edge);
    }

    // --------------------------------------------------------------------------------

    @Override
    public Iterator<NODE> nodes() {
        return edgesByNode.keySet().iterator();
    }

    @Override
    public Iterator<Edge<NODE>> adjacent(NODE node, Direction direction) {
        switch (direction) {
            case SOURCE_TO_TARGET:
                return getAdjacent(node, true).sourceEdges.iterator();
            case TARGET_TO_SOURCE:
                return getAdjacent(node, true).targetEdges.iterator();
        }

        throw new IllegalArgumentException(direction.toString());
    }

    // --------------------------------------------------------------------------------

    private Edges<NODE> getAdjacent(NODE node, boolean throwExceptionIfNot) {
        Edges<NODE> nodeEdges = edgesByNode.get(node);

        if (throwExceptionIfNot && nodeEdges == null) {
            throw new IllegalArgumentException("node=" + node + ", not found");
        }

        return nodeEdges;
    }

    // --------------------------------------------------------------------------------

    @Override
    public boolean isDirected() {
        return true;
    }
}
