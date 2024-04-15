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

    private NodeMetadataMap<NODE> nodeNodeMetadataMap = new NodeMetadataMap<>();

    private NODE begNode;

    // --------------------------------------------------------------------------------

    @Override
    public void traverse(Graph<NODE> graph, Visitor<NODE> visitor, NODE begNode) {
        this.begNode = begNode;

        Queue<NODE> queue = new LinkedList<>();

        nodeNodeMetadataMap.doDisc(begNode, null);
        queue.add(begNode);

        while (!queue.isEmpty()) {
            NODE curr = queue.remove();

            visitor.preVisit(curr);

            nodeNodeMetadataMap.doProc(curr);

            Iterator<Edge<NODE>> itt = graph.adjacent(curr, Direction.SOURCE_TO_TARGET);

            while (itt.hasNext()) {
                Edge<NODE> edge = itt.next();

                if (!nodeNodeMetadataMap.isDisc(edge.getTarget())) {
                    nodeNodeMetadataMap.doDisc(edge.getTarget(), edge.getSource());
                    queue.add(edge.getTarget());
                }

                if (!nodeNodeMetadataMap.isProc(edge.getTarget())) {
                    visitor.visit(edge);
                }
            }

            visitor.posVisit(curr);
        }
    }



    @Override
    public List<NODE> findPath(NODE endNode) {
        LinkedList<NODE> path = new LinkedList<>();

//        NODE curr = endNode;
//
//        while (curr != begNode) {
//            path.addFirst(curr);
//
//            BFSNodeMetadata<NODE> bfsNodeMetadata = getNodeMetadata(curr);
//
//            curr = bfsNodeMetadata.getParent();
//        }
//
//        path.addFirst(curr);

        return path;
    }
}
