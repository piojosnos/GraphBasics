package org.cyrano.dsa.graph.traversals.impl.dfs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.mutable.MutableInt;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;
import org.cyrano.dsa.graph.traversals.interfaces.Traversal;
import org.cyrano.dsa.graph.traversals.interfaces.Visitor;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@Slf4j
public class DFSItt<NODE> /*extends DFSBase<NODE>*/ implements Traversal<NODE, DFSNodeMetadata<NODE>> {

    @Getter
    private NodeMetadataMap<NODE> nodeNodeMetadataMap = new NodeMetadataMap<>();

    protected NODE begNode;

    @Override
    public void traverse(Graph<NODE> graph, Visitor<NODE> visitor, NODE begNode) {
        this.begNode = begNode;

        traverseInternal(graph, visitor, begNode);
    }

    private void traverseInternal(Graph<NODE> graph, Visitor<NODE> visitor, NODE begNode) {
        Stack<StackStructure<NODE>> stack = new Stack<>();
        Edge<NODE> fakeEdge = new Edge<NODE>(null, begNode, -1);

        StackStructure<NODE> stackStructure = new StackStructure<>(fakeEdge, null);
        stack.push(stackStructure);

        MutableInt time = new MutableInt(0);

        while (!stack.empty()) { // TODO: Labels... Madness! Madness! :-)
            StackStructure<NODE> currentStackStructure = stack.peek();
            Edge<NODE> currentEdge = currentStackStructure.edge;
            NODE currentNode = currentEdge.getTarget();

            DFSNodeMetadata<NODE> currNodeMetadata = nodeNodeMetadataMap.getNodeMetadata(currentNode);

            if (!currNodeMetadata.isDisc()) {
                currNodeMetadata.disc(time, currentEdge.getSource());

                visitor.preVisit(currentNode);

                if (currentEdge != fakeEdge) {
                    visitor.visit(currentEdge);
                }


                Iterator<Edge<NODE>> itt = graph.adjacent(currentNode, Direction.SOURCE_TO_TARGET);
                currentStackStructure.itt = itt;

                StackStructure<NODE> nextStackStructure = findNextEdge(itt, visitor);
                if (nextStackStructure != null) {
                    stack.push(nextStackStructure);
                }
            } else if (!currNodeMetadata.isProc()) {
                Iterator<Edge<NODE>> itt = currentStackStructure.itt;

                StackStructure<NODE> nextStackStructure = findNextEdge(itt, visitor);
                if (nextStackStructure != null) {
                    stack.push(nextStackStructure);
                    continue;
                }

                stack.pop();  // Remove the node from the stack before post visit

                currNodeMetadata.proc(time);

                visitor.posVisit(currentNode);
            } else {
                stack.pop();
            }
        }
        log.info("Stack empty");
    }

    public StackStructure<NODE> findNextEdge(Iterator<Edge<NODE>> itt, Visitor<NODE> visitor) {
        while (itt.hasNext()) {
            Edge<NODE> edge = itt.next();
            DFSNodeMetadata<NODE> nextNodeMetadata = nodeNodeMetadataMap.getNodeMetadata(edge.getTarget());

            if (!nextNodeMetadata.isDisc()) {
                return new StackStructure<>(edge, null);
            } else {
                visitor.visit(edge);
            }
        }

        return null;
    }

    @AllArgsConstructor
    private static class StackStructure<NODE> {
        Edge<NODE> edge;
        Iterator<Edge<NODE>> itt;
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
