package org.cyrano.dsa.graph.traversals.impl.dfs;

import org.cyrano.dsa.graph.traversals.interfaces.Traversal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class DFSBase<NODE> implements Traversal<NODE, DFSNodeMetadata<NODE>> {

    private Map<NODE, DFSNodeMetadata<NODE>> nodeMetadataMap = new HashMap<>();

    protected NODE begNode;

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

    //@Override
    public DFSNodeMetadata<NODE> getNodeMetadata(NODE node) {
        return lazyGetNodeMetadata(node);
    }

    // --------------------------------------------------------------------------------

    @Override
    public List<NODE> findPath(NODE endNode) {
        LinkedList<NODE> path = new LinkedList<>();

        NODE curr = endNode;

        while (curr != begNode) {
            path.addFirst(curr);

            DFSNodeMetadata<NODE> nodeTraversal = lazyGetNodeMetadata(curr);

            curr = nodeTraversal.getParent();
        }

        path.addFirst(curr);

        return path;
    }
}
