package org.cyrano.dsa.graph.traversals.impl.bfs;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class NodeMetadataMap<NODE> {

    @Getter
    private Map<NODE, BFSNodeMetadata<NODE>> nodeMetadataMap = new HashMap<>();

    // --------------------------------------------------------------------------------

    private BFSNodeMetadata<NODE> lazyGetNodeMetadata(NODE node) {
        BFSNodeMetadata<NODE> nodeTraversal = nodeMetadataMap.get(node);

        if (nodeTraversal == null) {
            nodeTraversal = new BFSNodeMetadata<>();
            nodeMetadataMap.put(node, nodeTraversal);
        }

        return nodeTraversal;
    }

    // --------------------------------------------------------------------------------

    public BFSNodeMetadata<NODE> getNodeMetadata(NODE node) {
        return lazyGetNodeMetadata(node);
    }

    // --------------------------------------------------------------------------------

    public void doDisc(NODE node, NODE from) {
        getNodeMetadata(node).disc(from);
    }

    public void doProc(NODE node) {
        getNodeMetadata(node).proc();
    }

    public boolean isDisc(NODE node) {
        return getNodeMetadata(node).isDisc();
    }

    public boolean isProc(NODE node) {
        return getNodeMetadata(node).isProc();
    }
}
