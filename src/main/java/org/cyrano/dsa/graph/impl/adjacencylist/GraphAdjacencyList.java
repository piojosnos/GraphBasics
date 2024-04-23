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
        // TODO: Horrible
        Set<Edge<NODE>> sourceAdjacent = getAdjacent(node, true);

        for (Edge<NODE> edge : sourceAdjacent) {
            Set<Edge<NODE>> targetAdjacent = getAdjacent(
                    edge.getSource() == node ?
                            edge.getTarget() : edge.getSource(), true);

            if (directed) {
                targetAdjacent.remove(new Edge<>(edge.getSource(), edge.getTarget(), -1));
            } else {
                targetAdjacent.remove(new Edge<>(edge.getTarget(), edge.getSource(), -1));
            }
        }

        edgeSetByNode.remove(node);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void insertEdge(NODE source, NODE target, double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("weight=" + weight);
        }

        // TODO: Horrible
        if (directed) {
            Set<Edge<NODE>> sourceAdjacent = getAdjacent(source, true);
            sourceAdjacent.add(new Edge<>(source, target, weight));

            Set<Edge<NODE>> targetAdjacent = getAdjacent(target, true);
            targetAdjacent.add(new Edge<>(source, target, weight));
        } else {
            Set<Edge<NODE>> sourceAdjacent = getAdjacent(source, true);
            sourceAdjacent.add(new Edge<>(source, target, weight));

            Set<Edge<NODE>> targetAdjacent = getAdjacent(target, true);
            targetAdjacent.add(new Edge<>(target, source, weight));
        }
    }

    @Override
    public void insertEdge(NODE source, NODE target) {
        insertEdge(source, target, DEFAULT_WEIGHT);
    }

    @Override
    public void deleteEdge(NODE source, NODE target) {
        // TODO: Horrible
        if (directed) {
            Set<Edge<NODE>> sourceAdjacent = getAdjacent(source, true);
            sourceAdjacent.remove(new Edge<>(source, target, -1));

            Set<Edge<NODE>> targetAdjacent = getAdjacent(target, true);
            targetAdjacent.remove(new Edge<>(source, target, -1));
        } else {
            Set<Edge<NODE>> sourceAdjacent = getAdjacent(source, true);
            sourceAdjacent.remove(new Edge<>(source, target, -1));

            Set<Edge<NODE>> targetAdjacent = getAdjacent(target, true);
            targetAdjacent.remove(new Edge<>(target, source, -1));
        }
    }

    // --------------------------------------------------------------------------------

    @Override
    public Iterator<NODE> nodes() {
        return edgeSetByNode.keySet().iterator();
    }

    @Override
    public Iterator<Edge<NODE>> adjacent(NODE node, Direction direction) {
        if (directed) {
            return new GraphAdjacencyListEdgeIterator<>(direction,
                    node, getAdjacent(node, true).iterator());
        } else {
            return new GraphAdjacencyListEdgeIterator<>(Direction.SOURCE_TO_TARGET,
                    node, getAdjacent(node, true).iterator());
        }
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
