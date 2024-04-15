package org.cyrano.dsa.graph.traversals.interfaces;

import org.cyrano.dsa.graph.interfaces.Graph;

import java.util.List;

public interface Traversal<NODE, T> {

    //T getNodeMetadata(NODE node);

    void traverse(Graph<NODE> graph, Visitor<NODE> visitor, NODE startNode);

    List<NODE> findPath(NODE endNode);
}
