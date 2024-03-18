package org.cyrano.dsa.graph.interfaces;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode()
@Getter
@RequiredArgsConstructor
public class Edge {

    @EqualsAndHashCode.Include
    private final int source;

    @EqualsAndHashCode.Include
    private final int target;

    @EqualsAndHashCode.Exclude
    private final double weight;

    // --------------------------------------------------------------------------------

    public String calcForwardEdgeId() {
        return source + "|" + target;
    }

    public String calcReverseEdgeId() {
        return target + "|" + source;
    }
}
