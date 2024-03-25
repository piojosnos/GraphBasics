package org.cyrano.dsa.graph.traversals.impl.bfs;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class NodeMetadataMap<NODE> {

    @Getter
    private Map<NODE, BFSNodeMetadata<NODE>> nodeMetadataMap = new HashMap<>();


}
