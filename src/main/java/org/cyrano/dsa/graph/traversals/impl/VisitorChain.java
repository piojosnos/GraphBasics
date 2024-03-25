package org.cyrano.dsa.graph.traversals.impl;

import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.traversals.interfaces.Visitor;

import java.util.ArrayList;
import java.util.List;

public class VisitorChain<NODE> implements Visitor<NODE> {

    private List<Visitor<NODE>> visitorList = new ArrayList<>();

    // --------------------------------------------------------------------------------

    public VisitorChain<NODE> addVisitor(Visitor<NODE> visitor) {
        visitorList.add(visitor);

        return this;
    }

    // --------------------------------------------------------------------------------

    @Override
    public void preVisit(NODE node) {
        for (Visitor visitor : visitorList) {
            visitor.preVisit(node);
        }
    }

    // --------------------------------------------------------------------------------

    @Override
    public void posVisit(NODE node) {
        for (Visitor visitor : visitorList) {
            visitor.posVisit(node);
        }
    }

    // --------------------------------------------------------------------------------

    @Override
    public void visit(Edge<NODE> edge) {
        for (Visitor visitor : visitorList) {
            visitor.visit(edge);
        }
    }
}
