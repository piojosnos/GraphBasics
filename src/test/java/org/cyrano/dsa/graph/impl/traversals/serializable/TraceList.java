package org.cyrano.dsa.graph.impl.traversals.serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cyrano.dsa.graph.interfaces.Edge;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TraceList<NODE> {

    public enum TraceType {
        PRE_VISIT_NODE,
        POS_VISIT_NODE,
        VISIT_EDGE
    }

    // --------------------------------------------------------------------------------

    @Getter
    @Setter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Trace<NODE> {
        private TraceType traceType;

        private NODE node;

        private Edge<NODE> edge;

        // --------------------------------------------------------------------------------

        public Trace(TraceType traceType, NODE node) {
            if (traceType == TraceType.VISIT_EDGE) {
                throw new IllegalArgumentException();
            }

            this.traceType = traceType;
            this.node = node;
        }

        // --------------------------------------------------------------------------------

        public Trace(TraceType traceType, Edge<NODE> edge) {
            if (traceType != TraceType.VISIT_EDGE) {
                throw new IllegalArgumentException();
            }

            this.traceType = traceType;
            this.edge = edge;
        }

        // --------------------------------------------------------------------------------

        @Override
        public String toString() {
            String ret = traceType.toString() + ",";

            if (traceType == TraceType.VISIT_EDGE) {
                ret += edge;
            } else {
                ret += node;
            }

            return "{" + ret + "}";
        }
    }

    // --------------------------------------------------------------------------------

    private final List<Trace<NODE>> traceList = new ArrayList<>();

    // --------------------------------------------------------------------------------

    public void add(Trace<NODE> trace) {
        traceList.add(trace);
    }
}
