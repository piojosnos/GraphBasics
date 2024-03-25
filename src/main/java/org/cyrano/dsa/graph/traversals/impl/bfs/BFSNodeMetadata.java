package org.cyrano.dsa.graph.traversals.impl.bfs;

import lombok.Getter;

@Getter
public class BFSNodeMetadata<NODE> {

    private boolean disc;
    private boolean proc;

    private NODE parent;

    void disc(NODE parent) {
        disc = true;

        this.parent = parent;
    }

    void proc() {
        proc = true;
    }
}
