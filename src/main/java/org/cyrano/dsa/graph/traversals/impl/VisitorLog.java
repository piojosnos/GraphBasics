package org.cyrano.dsa.graph.traversals.impl;

import lombok.extern.slf4j.Slf4j;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.traversals.interfaces.Visitor;

@Slf4j
public class VisitorLog<NODE> implements Visitor<NODE> {

    public void preVisit(NODE node) {
        log.info("preVisit Node: {}", node);
    }

    public void posVisit(NODE node) {
        log.info("posVisit Node: {}", node);
    }

    public void visit(Edge<NODE> edge) {
        log.info("visit Edge: {}", edge.toString());
    }
}
