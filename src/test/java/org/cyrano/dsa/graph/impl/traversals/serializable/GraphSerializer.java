package org.cyrano.dsa.graph.impl.traversals.serializable;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import org.cyrano.dsa.graph.ObjectMapperFactory;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GraphSerializer<NODE> {

    @Getter
    public static class SerializableGraph<NODE> {

        private final List/* */<NODE>/* */nodes = new ArrayList<>();

        private final List<Edge<NODE>>/**/edges = new ArrayList<>();
    }

    // --------------------------------------------------------------------------------
    // SAVE
    // --------------------------------------------------------------------------------

    public void save(String expFile, Graph<NODE> graph) {
        SerializableGraph<NODE> serializableGraph = graphToSerializableGraph(graph);

        try {
            ObjectMapperFactory.getObjectMapper().writeValue(
                    new File(expFile),
                    serializableGraph);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------------------------------------------------------------

    private SerializableGraph<NODE> graphToSerializableGraph(Graph<NODE> graph) {
        SerializableGraph<NODE> serializableGraph = new SerializableGraph<>();

        Iterator<NODE> ittNode = graph.nodes();

        while (ittNode.hasNext()) {
            NODE node = ittNode.next();

            serializableGraph.getNodes().add(node);

            Iterator<Edge<NODE>> ittEdge = graph.adjacent(
                    node, Direction.SOURCE_TO_TARGET);

            while (ittEdge.hasNext()) {
                serializableGraph.getEdges().add(ittEdge.next());
            }
        }

        return serializableGraph;
    }

    // --------------------------------------------------------------------------------
    // LOAD
    // --------------------------------------------------------------------------------

    public void load(String expFile, Graph<NODE> graph) {
        SerializableGraph<NODE> serializableGraph;

        try {
            serializableGraph =
                    ObjectMapperFactory.getObjectMapper().readValue(
                            new File(expFile),
                            new TypeReference<>() {
                                // Empty
                            }
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        serializableGraphToGraph(serializableGraph, graph);
    }

    // --------------------------------------------------------------------------------

    private void serializableGraphToGraph(
            SerializableGraph<NODE> serializableGraph, Graph<NODE> graph) {

        for (NODE node : serializableGraph.getNodes()) {
            graph.insertNode(node);
        }

        for (Edge<NODE> edge : serializableGraph.getEdges()) {
            graph.insertEdge(edge.getSource(), edge.getTarget(), edge.getWeight());
        }
    }
}
