package org.cyrano.dsa.graph.interfaces;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class Edge<NODE> {

    @EqualsAndHashCode.Include
    private final NODE source;

    @EqualsAndHashCode.Include
    private final NODE target;

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
