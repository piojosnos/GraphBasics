package org.cyrano.dsa.graph.traversals.impl.dfs;

import lombok.Getter;
import org.apache.commons.lang3.mutable.MutableInt;

@Getter
public class DFSNodeMetadata<NODE> {

    private boolean disc;
    private boolean proc;

    private NODE parent;

    private int begTime;
    private int endTime;

    void disc(MutableInt time, NODE parent) {
        disc = true;
        begTime = time.incrementAndGet();

        this.parent = parent;
    }

    void proc(MutableInt time) {
        proc = true;
        endTime = time.incrementAndGet();
    }
}