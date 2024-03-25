package org.cyrano.dsa.graph.traversals.interfaces;

import org.cyrano.dsa.graph.interfaces.Edge;

public interface Visitor<NODE> {

    void preVisit(NODE node);

    void posVisit(NODE node);

    void visit(Edge<NODE> edge);
}
