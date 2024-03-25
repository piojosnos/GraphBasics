package org.cyrano.dsa.graph.traversals.impl.bfs;

import lombok.Getter;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;
import org.cyrano.dsa.graph.traversals.interfaces.Traversal;
import org.cyrano.dsa.graph.traversals.interfaces.Visitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS<NODE> implements Traversal<NODE, BFSNodeMetadata<NODE>> {

    @Getter
    private Map<NODE, BFSNodeMetadata<NODE>> nodeMetadataMap = new HashMap<>();

    private NODE begNode;

    // --------------------------------------------------------------------------------

    protected BFSNodeMetadata<NODE> lazyGetNodeMetadata(NODE node) {
        BFSNodeMetadata<NODE> nodeTraversal = nodeMetadataMap.get(node);

        if (nodeTraversal == null) {
            nodeTraversal = new BFSNodeMetadata<>();
            nodeMetadataMap.put(node, nodeTraversal);
        }

        return nodeTraversal;
    }

    // --------------------------------------------------------------------------------

    @Override
    public BFSNodeMetadata<NODE> getNodeMetadata(NODE node) {
        return lazyGetNodeMetadata(node);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void traverse(Graph<NODE> graph, Visitor<NODE> visitor, NODE begNode) {
        this.begNode = begNode;

        Queue<NODE> queue = new LinkedList<>();

        doDisc(begNode, null);
        queue.add(begNode);

        while (!queue.isEmpty()) {
            NODE curr = queue.remove();

            visitor.preVisit(curr);

            doProc(curr);

            Iterator<Edge<NODE>> itt = graph.adjacent(curr, Direction.SOURCE_TO_TARGET);

            while (itt.hasNext()) {
                Edge<NODE> edge = itt.next();

                if (!isDisc(edge.getTarget())) {
                    doDisc(edge.getTarget(), edge.getSource());
                    queue.add(edge.getTarget());
                }

                if (!isProc(edge.getTarget())) {
                    visitor.visit(edge);
                }
            }

            visitor.posVisit(curr);
        }
    }

    private void doProc(NODE node) {
        getNodeMetadata(node).proc();
    }

    private boolean isProc(NODE node) {
        return getNodeMetadata(node).isProc();
    }

    private void doDisc(NODE node, NODE from) {
        getNodeMetadata(node).disc(from);
    }

    private boolean isDisc(NODE node) {
        return getNodeMetadata(node).isDisc();
    }


    @Override
    public List<NODE> findPath(NODE endNode) {
        LinkedList<NODE> path = new LinkedList<>();

        NODE curr = endNode;

        while (curr != begNode) {
            path.addFirst(curr);

            BFSNodeMetadata<NODE> bfsNodeMetadata = getNodeMetadata(curr);

            curr = bfsNodeMetadata.getParent();
        }

        path.addFirst(curr);

        return path;
    }
}
