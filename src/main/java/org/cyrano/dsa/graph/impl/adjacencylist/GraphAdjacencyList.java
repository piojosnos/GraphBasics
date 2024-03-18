package org.cyrano.dsa.graph.impl.adjacencylist;

import lombok.Getter;
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
public class GraphAdjacencyList implements Graph {

    private final Map<Integer, Set<Edge>> nodeById = new HashMap<>();

    @Getter
    private final boolean directed;

    // --------------------------------------------------------------------------------

    @Override
    public void insertNode(int node) {
        nodeById.put(node, new HashSet<>());
    }

    @Override
    public void deleteNode(int node) {
        throw new UnsupportedOperationException(CAN_YOU_IMPLEMENT_THIS_AS_HOMEWORK);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void insertEdge(int source, int target, double weight) {
        Set<Edge> sourceAdjacent = getAdjacent(source, true);
        Set<Edge> targetAdjacent = getAdjacent(target, true);

        if (weight <= 0) {
            throw new IllegalArgumentException("weight=" + weight);
        }

        sourceAdjacent.add(new Edge(source, target, weight));

        if (!directed) {
            targetAdjacent.add(new Edge(target, source, weight));
        }
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
                return getAdjacent(node, true).iterator();
            case TARGET_TO_SOURCE:
                throw new UnsupportedOperationException();
        }

        throw new IllegalArgumentException(direction.toString());
    }

    // --------------------------------------------------------------------------------

    private Set<Edge> getAdjacent(int node, boolean throwExceptionIfNot) {
        Set<Edge> nodeAdjacent = nodeById.get(node);

        if (throwExceptionIfNot && nodeAdjacent == null) {
            throw new IllegalArgumentException("node=" + node + ", not found");
        }

        return nodeAdjacent;
    }
}
