package org.cyrano.dsa.graph.impl.traversals.serializable;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.cyrano.dsa.graph.ObjectMapperFactory;
import org.cyrano.dsa.graph.traversals.impl.dfs.DFSNodeMetadata;
import org.cyrano.dsa.graph.traversals.impl.dfs.NodeMetadataMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@NoArgsConstructor
public class NodeMetadataList<NODE> {

    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(includeFieldNames = false)
    public static class NodeMetadata<NODE> {

        private NODE node;

        private DFSNodeMetadata<NODE> dfsNodeMetadata;
    }

    // --------------------------------------------------------------------------------

    @Getter
    private final List<NodeMetadata<NODE>> nodeMetadataList = new ArrayList<>();

    // --------------------------------------------------------------------------------

    public NodeMetadataList(NodeMetadataMap<NODE> nodeNodeMetadataMap) {
        Iterator<Map.Entry<NODE, DFSNodeMetadata<NODE>>> itt = nodeNodeMetadataMap.iterator();

        while (itt.hasNext()) {
            Map.Entry<NODE, DFSNodeMetadata<NODE>> entry = itt.next();

            NodeMetadata<NODE> nodeMetadata = new NodeMetadata<>(
                    entry.getKey(), entry.getValue());

            nodeMetadataList.add(nodeMetadata);
        }
    }

    // --------------------------------------------------------------------------------

    public void createNodeMetadataMapSer(String expFile) {
        try {
            ObjectMapperFactory.getObjectMapper().writeValue(
                    new File(expFile), nodeMetadataList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------------------------------------------------------------

    public void assertNodeMetadataMapSer(String expFile) {
        List<NodeMetadata<NODE>> expNodeMetadataList;

        try {
            expNodeMetadataList =
                    ObjectMapperFactory.getObjectMapper().readValue(
                            new File(expFile),
                            new TypeReference<>() {
                                // Empty
                            }
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<NodeMetadata<NODE>> actNodeMetadataList = this.getNodeMetadataList();

        assertEquals(expNodeMetadataList, actNodeMetadataList);
    }
}
