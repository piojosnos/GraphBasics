package org.cyrano.dsa.graph.traversals.impl.dfs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.mutable.MutableInt;

@Getter
@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
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

    @Override
    public String toString() {
        String ret = "";

        ret += disc + ", ";
        ret += proc + ", ";

        ret += parent + ", ";

        ret += begTime + ", ";
        ret += endTime;

        return "{" + ret + "}";
    }
}
