package org.cyrano.dsa.graph.interfaces;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Edge<NODE> {

    @EqualsAndHashCode.Include
    private NODE source;

    @EqualsAndHashCode.Include
    private NODE target;

    @EqualsAndHashCode.Exclude
    private double weight;

    // --------------------------------------------------------------------------------

    public String toString() {
        return source + "|" + target;
    }
}
