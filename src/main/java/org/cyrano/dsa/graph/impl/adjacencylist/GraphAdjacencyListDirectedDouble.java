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

import static org.cyrano.dsa.graph.impl.Homework.CAN_YOU_IMPLEMENT_THIS_AS_HOMEWORK;

@RequiredArgsConstructor
public class GraphAdjacencyListDirectedDouble implements Graph {

    private final Map<Integer, Edges> nodeById = new HashMap<>();

    // --------------------------------------------------------------------------------

    private static class Edges {
        Set<Edge> sourceEdges = new HashSet<>();
        Set<Edge> targetEdges = new HashSet<>();
    }

    // --------------------------------------------------------------------------------

    @Override
    public void insertNode(int node) {
        nodeById.put(node, new Edges());
    }

    @Override
    public void deleteNode(int node) {
        throw new UnsupportedOperationException(CAN_YOU_IMPLEMENT_THIS_AS_HOMEWORK);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void insertEdge(int source, int target, double weight) {
        Edges sourceAdjacent = getAdjacent(source, true);
        Edges targetAdjacent = getAdjacent(target, true);

        if (weight <= 0) {
            throw new IllegalArgumentException("weight=" + weight);
        }

        Edge edge = new Edge(source, target, weight);

        sourceAdjacent.sourceEdges.add(edge); // --->
        targetAdjacent.targetEdges.add(edge); // <---
    }

    @Override
    public void insertEdge(int source, int target) {
        insertEdge(source, target, DEFAULT_WEIGHT);
    }

    @Override
    public void deleteEdge(int source, int target) {
        throw new UnsupportedOperationException(CAN_YOU_IMPLEMENT_THIS_AS_HOMEWORK);
    }

    // --------------------------------------------------------------------------------

    @Override
    public Iterator<Integer> nodes() {
        return nodeById.keySet().iterator();
    }

    @Override
    public Iterator<Edge> adjacent(int node, Direction direction) {
        switch (direction) {
            case SOURCE_TO_TARGET:
                return getAdjacent(node, true).sourceEdges.iterator();
            case TARGET_TO_SOURCE:
                return getAdjacent(node, true).targetEdges.iterator();
        }

        throw new IllegalArgumentException(direction.toString());
    }

    // --------------------------------------------------------------------------------

    private Edges getAdjacent(int node, boolean throwExceptionIfNot) {
        Edges nodeEdges = nodeById.get(node);

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
