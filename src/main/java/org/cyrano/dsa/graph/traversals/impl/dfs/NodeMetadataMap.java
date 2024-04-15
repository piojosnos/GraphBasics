package org.cyrano.dsa.graph.traversals.impl.dfs;

import lombok.Getter;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class NodeMetadataMap<NODE> {

    @Getter
    private Map<NODE, DFSNodeMetadata<NODE>> nodeMetadataMap = new LinkedHashMap<>();

    private MutableInt time = new MutableInt(0);

    // --------------------------------------------------------------------------------

    private DFSNodeMetadata<NODE> lazyGetNodeMetadata(NODE node) {
        DFSNodeMetadata<NODE> nodeTraversal = nodeMetadataMap.get(node);

        if (nodeTraversal == null) {
            nodeTraversal = new DFSNodeMetadata<>();
            nodeMetadataMap.put(node, nodeTraversal);
        }

        return nodeTraversal;
    }

    // --------------------------------------------------------------------------------

    public DFSNodeMetadata<NODE> getNodeMetadata(NODE node) {
        return lazyGetNodeMetadata(node);
    }

    // --------------------------------------------------------------------------------

    public void doDisc(NODE node, NODE from) {
        getNodeMetadata(node).disc(time, from);
    }

    public void doProc(NODE node) {
        getNodeMetadata(node).proc(time);
    }

    public boolean isDisc(NODE node) {
        return getNodeMetadata(node).isDisc();
    }

    public boolean isProc(NODE node) {
        return getNodeMetadata(node).isProc();
    }

    // --------------------------------------------------------------------------------
    // Used for serialization
    // --------------------------------------------------------------------------------

    public Iterator<Map.Entry<NODE, DFSNodeMetadata<NODE>>> iterator() {
        return nodeMetadataMap.entrySet().iterator();
    }
}
