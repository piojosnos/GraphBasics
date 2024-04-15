package org.cyrano.dsa.graph.impl.traversals.serializable;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import org.cyrano.dsa.graph.ObjectMapperFactory;
import org.cyrano.dsa.graph.impl.traversals.serializable.TraceList.Trace;
import org.cyrano.dsa.graph.impl.traversals.serializable.TraceList.TraceType;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.traversals.interfaces.Visitor;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TraceVisitor<NODE> implements Visitor<NODE> {

    @Getter
    private final TraceList<NODE> traceList = new TraceList<>();

    // --------------------------------------------------------------------------------

    @Override
    public void preVisit(NODE node) {
        traceList.add(new Trace<>(TraceType.PRE_VISIT_NODE, node));
    }

    @Override
    public void posVisit(NODE node) {
        traceList.add(new Trace<>(TraceType.POS_VISIT_NODE, node));
    }

    @Override
    public void visit(Edge<NODE> edge) {
        traceList.add(new Trace<>(TraceType.VISIT_EDGE, edge));
    }

    // --------------------------------------------------------------------------------

    public void createTraceList(String expFile) {
        try {
            ObjectMapperFactory.getObjectMapper().writeValue(
                    new File(expFile),
                    this.getTraceList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------------------------------------------------------------

    public void assertTraceList(String expFile) {
        try {
            TraceList<Integer> expTraceList =
                    ObjectMapperFactory.getObjectMapper().readValue(
                            new File(expFile),
                            new TypeReference<>() {
                                // Empty
                            }
                    );
            TraceList<NODE> actTraceList = this.getTraceList();

            assertEquals(expTraceList.getTraceList(), actTraceList.getTraceList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
