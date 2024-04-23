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
public class GraphAdjacencyListSimple<NODE> implements Graph<NODE> {

    private final Map<NODE, Set<Edge<NODE>>> edgeSetByNode = new HashMap<>();

    @Getter
    private final boolean directed;

    // --------------------------------------------------------------------------------
    // NODES
    // --------------------------------------------------------------------------------

    @Override
    public void insertNode(NODE node) {
        edgeSetByNode.put(node, new HashSet<>());
    }

    @Override
    public void deleteNode(NODE node) {
        Set<Edge<NODE>> sourceAdjacent = getAdjacent(node, true);

        for (Edge<NODE> edge : sourceAdjacent) {
            if (edge.getSource() == node) { // node IS SOURCE, remove from TARGET
                Set<Edge<NODE>> adjacent = getAdjacent(edge.getTarget(), true);
                adjacent.remove(edge);
            }

            if (edge.getTarget() == node) { // node IS TARGET, remove from SOURCE
                Set<Edge<NODE>> adjacent = getAdjacent(edge.getSource(), true);
                adjacent.remove(edge);
            }
        }

        sourceAdjacent.clear();

        edgeSetByNode.remove(node);
    }

    // --------------------------------------------------------------------------------
    // EDGES
    // --------------------------------------------------------------------------------

    @Override
    public void insertEdge(NODE source, NODE target, double weight) {
        insertEdgeDirected(source, target, weight);

        if (!directed) {
            insertEdgeDirected(target, source, weight);
        }
    }

    @Override
    public void insertEdge(NODE source, NODE target) {
        insertEdge(source, target, DEFAULT_WEIGHT);
    }

    private void insertEdgeDirected(NODE source, NODE target, double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("weight=" + weight);
        }

        Set<Edge<NODE>> sourceAdjacent = getAdjacent(source, true);
        sourceAdjacent.add(new Edge<>(source, target, weight));

        Set<Edge<NODE>> targetAdjacent = getAdjacent(target, true);
        targetAdjacent.add(new Edge<>(source, target, weight));
    }

    // --------------------------------------------------------------------------------

    @Override
    public void deleteEdge(NODE source, NODE target) {
        deleteEdgeDirected(source, target);

        if (!directed) {
            deleteEdgeDirected(target, source);
        }
    }

    private void deleteEdgeDirected(NODE source, NODE target) {
        Set<Edge<NODE>> sourceAdjacent = getAdjacent(source, true);
        sourceAdjacent.remove(new Edge<>(source, target, -1));

        Set<Edge<NODE>> targetAdjacent = getAdjacent(target, true);
        targetAdjacent.remove(new Edge<>(source, target, -1));
    }

    // --------------------------------------------------------------------------------
    // ITERATOR
    // --------------------------------------------------------------------------------

    @Override
    public Iterator<NODE> nodes() {
        return edgeSetByNode.keySet().iterator();
    }

    @Override
    public Iterator<Edge<NODE>> adjacent(NODE node, Direction direction) {
        return new GraphAdjacencyListSimpleEdgeIterator<>(direction,
                node, getAdjacent(node, true).iterator());
    }

    // --------------------------------------------------------------------------------
    // MISC
    // --------------------------------------------------------------------------------

    private Set<Edge<NODE>> getAdjacent(NODE node, boolean throwExceptionIfNot) {
        Set<Edge<NODE>> nodeAdjacent = edgeSetByNode.get(node);

        if (throwExceptionIfNot && nodeAdjacent == null) {
            throw new IllegalArgumentException("node=" + node + ", not found");
        }

        return nodeAdjacent;
    }
}
