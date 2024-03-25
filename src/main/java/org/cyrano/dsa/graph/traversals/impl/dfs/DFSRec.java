package org.cyrano.dsa.graph.traversals.impl.dfs;

import org.apache.commons.lang3.mutable.MutableInt;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;
import org.cyrano.dsa.graph.traversals.interfaces.Visitor;

import java.util.Iterator;

public class DFSRec<NODE> extends DFSBase<NODE> {

    @Override
    public void traverse(Graph<NODE> graph, Visitor<NODE> visitor, NODE begNode) {
        this.begNode = begNode;

        MutableInt time = new MutableInt(0);

        DFSNodeMetadata<NODE> nodeMetadata = getNodeMetadata(begNode);
        nodeMetadata.disc(time, null);

        traverseInternal(graph, visitor, begNode, time);
    }

    private void traverseInternal(Graph<NODE> graph, Visitor<NODE> visitor, NODE currNode, MutableInt time) {
        DFSNodeMetadata<NODE> currNodeMetadata = getNodeMetadata(currNode);

        visitor.preVisit(currNode);

        Iterator<Edge<NODE>> itt = graph.adjacent(currNode, Direction.SOURCE_TO_TARGET);

        while (itt.hasNext()) {
            Edge<NODE> edge = itt.next();

            DFSNodeMetadata<NODE> nextNodeMetadata = getNodeMetadata(edge.getTarget());

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
}
