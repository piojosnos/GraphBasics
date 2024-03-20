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
public class GraphAdjacencyList<NODE> implements Graph<NODE> {

    private final Map<NODE, Set<Edge<NODE>>> edgeSetByNode = new HashMap<>();

    @Getter
    private final boolean directed;

    // --------------------------------------------------------------------------------

    @Override
    public void insertNode(NODE node) {
        edgeSetByNode.put(node, new HashSet<>());
    }

    @Override
    public void deleteNode(NODE node) {
        throw new UnsupportedOperationException(CAN_YOU_IMPLEMENT_THIS_AS_HOMEWORK);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void insertEdge(NODE source, NODE target, double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("weight=" + weight);
        }

        Set<Edge<NODE>> sourceAdjacent = getAdjacent(source, true);
        sourceAdjacent.add(new Edge<>(source, target, weight));

        if (directed) {
            return;
        }

        Set<Edge<NODE>> targetAdjacent = getAdjacent(target, true);
        targetAdjacent.add(new Edge<>(target, source, weight));
    }

    @Override
    public void insertEdge(NODE source, NODE target) {
        insertEdge(source, target, DEFAULT_WEIGHT);
    }

    @Override
    public void deleteEdge(NODE source, NODE target) {
        throw new UnsupportedOperationException(CAN_YOU_IMPLEMENT_THIS_AS_HOMEWORK);
    }

    // --------------------------------------------------------------------------------

    @Override
    public Iterator<NODE> nodes() {
        return edgeSetByNode.keySet().iterator();
    }

    @Override
    public Iterator<Edge<NODE>> adjacent(NODE node, Direction direction) {
        switch (direction) {
            case SOURCE_TO_TARGET:
                return getAdjacent(node, true).iterator();
            case TARGET_TO_SOURCE:
                throw new UnsupportedOperationException();
        }

        throw new IllegalArgumentException(direction.toString());
    }

    // --------------------------------------------------------------------------------

    private Set<Edge<NODE>> getAdjacent(NODE node, boolean throwExceptionIfNot) {
        Set<Edge<NODE>> nodeAdjacent = edgeSetByNode.get(node);

        if (throwExceptionIfNot && nodeAdjacent == null) {
            throw new IllegalArgumentException("node=" + node + ", not found");
        }

        return nodeAdjacent;
    }
}
