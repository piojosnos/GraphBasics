package org.cyrano.dsa.graph.traversals.impl.dfs;

import lombok.Getter;
import org.apache.commons.lang3.mutable.MutableInt;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;
import org.cyrano.dsa.graph.traversals.interfaces.Traversal;
import org.cyrano.dsa.graph.traversals.interfaces.Visitor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DFSRec<NODE> /*extends DFSBase<NODE>*/ implements Traversal<NODE, DFSNodeMetadata<NODE>> {

    @Getter
    private NodeMetadataMap<NODE> nodeNodeMetadataMap = new NodeMetadataMap<>();

    protected NODE begNode;

    @Override
    public void traverse(Graph<NODE> graph, Visitor<NODE> visitor, NODE begNode) {
        this.begNode = begNode;

        MutableInt time = new MutableInt(0);

        DFSNodeMetadata<NODE> nodeMetadata = nodeNodeMetadataMap.getNodeMetadata(begNode);
        nodeMetadata.disc(time, null);

        traverseInternal(graph, visitor, begNode, time);
    }

    private void traverseInternal(Graph<NODE> graph, Visitor<NODE> visitor, NODE currNode, MutableInt time) {
        DFSNodeMetadata<NODE> currNodeMetadata = nodeNodeMetadataMap.getNodeMetadata(currNode);

        visitor.preVisit(currNode);

        Iterator<Edge<NODE>> itt = graph.adjacent(currNode, Direction.SOURCE_TO_TARGET);

        while (itt.hasNext()) {
            Edge<NODE> edge = itt.next();

            DFSNodeMetadata<NODE> nextNodeMetadata = nodeNodeMetadataMap.getNodeMetadata(edge.getTarget());

            if (!nextNodeMetadata.isDisc()) {
                nextNodeMetadata.disc(time, currNode);

                visitor.visit(edge);

                traverseInternal(graph, visitor, edge.getTarget(), time);
            } else if (!nextNodeMetadata.isProc() || graph.isDirected()) {
                visitor.visit(edge);
            }
        }

        currNodeMetadata.proc(time);

        visitor.posVisit(currNode);
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
