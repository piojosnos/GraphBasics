//package org.cyrano.dsa.graph.traversals.impl.bfs;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.cyrano.dsa.graph.traversals.interfaces.Traversal;
//import org.cyrano.dsa.graph.traversals.interfaces.Visitor;
//import org.cyrano.dsa.graph.interfaces.Edge;
//import org.cyrano.graph.nongeneric.traversals.repaint.RepaintEvent;
//import org.cyrano.graph.nongeneric.traversals.repaint.RepaintListenerProxy;
//import org.cyrano.graph.nongeneric.visualization.StyleProvider;
//import org.cyrano.graph.nongeneric.visualization.Styles;
//import org.cyrano.graph.nongeneric.visualization.utils.Style;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.cyrano.graph.nongeneric.visualization.IdCalculator.calcForwardEdgeId;
//import static org.cyrano.graph.nongeneric.visualization.IdCalculator.calcReverseEdgeId;
//
//@RequiredArgsConstructor
//public class BFSVisitor implements Visitor, StyleProvider {
//
//    private enum EdgeType {
//        TREE_EDGE,
//        SIDE_EDGE,
//        EMPTY;
//    }
//
//    @Getter
//    private final RepaintListenerProxy repaintListenerProxy =
//            new RepaintListenerProxy(new ArrayList<>());
//
//    private final Map<String, BFSVisitor.EdgeType> visitedEdges = new HashMap<>();
//
//    private final Traversal traversal;
//
//    // --------------------------------------------------------------------------------
//    // StyleProvider
//    // --------------------------------------------------------------------------------
//
//    @Override
//    public String getNodeMetaString(int node) {
//        return null;
//    }
//
//    // --------------------------------------------------------------------------------
//
//    @Override
//    public Style styleForNode(int node) {
//        BFSNodeMetadata nodeMetadata = (BFSNodeMetadata) traversal.getNodeMetadata(node);
//
//        if (nodeMetadata.isProc()) {
//            return Styles.VISITED;
//        }
//
//        if (nodeMetadata.isDisc()) {
//            return Styles.INQUEUE;
//        }
//
//        return Styles.PENDING;
//    }
//
//    // --------------------------------------------------------------------------------
//
//    @Override
//    public Style styleForEdge(Edge edge) {
//        if (!visitedEdges.containsKey(calcForwardEdgeId(edge))
//                && !visitedEdges.containsKey(calcReverseEdgeId(edge))) {
//            return Styles.PENDING;
//        }
//
//        if (visitedEdges.get(calcForwardEdgeId(edge)) == EdgeType.TREE_EDGE) {
//            return Styles.TREE_EDGE;
//        }
//
//        if (visitedEdges.get(calcForwardEdgeId(edge)) == EdgeType.SIDE_EDGE) {
//            return Styles.BACK_EDGE;
//        }
//
//        return null;
//    }
//
//    // --------------------------------------------------------------------------------
//    // Visitor
//    // --------------------------------------------------------------------------------
//
//    @Override
//    public void preVisit(int node) {
//        repaintListenerProxy.fireModelChangedEvent(new RepaintEvent(this));
//    }
//
//    @Override
//    public void posVisit(int node) {
//        repaintListenerProxy.fireModelChangedEvent(new RepaintEvent(this));
//    }
//
//    @Override
//    public void visit(Edge edge) {
//        if (visitedEdges.containsKey(calcForwardEdgeId(edge))
//                || visitedEdges.containsKey(calcReverseEdgeId(edge))) {
//            repaintListenerProxy.fireModelChangedEvent(new RepaintEvent(this));
//            return;
//        }
//
//        if (isTreeEdge(traversal, edge)) {
//            visitedEdges.put(calcForwardEdgeId(edge), BFSVisitor.EdgeType.TREE_EDGE);
//            visitedEdges.put(calcReverseEdgeId(edge), BFSVisitor.EdgeType.EMPTY);
//            repaintListenerProxy.fireModelChangedEvent(new RepaintEvent(this));
//            return;
//        }
//
//        if (isSideEdge(traversal, edge)) {
//            visitedEdges.put(calcForwardEdgeId(edge), BFSVisitor.EdgeType.SIDE_EDGE);
//            visitedEdges.put(calcReverseEdgeId(edge), BFSVisitor.EdgeType.EMPTY);
//            repaintListenerProxy.fireModelChangedEvent(new RepaintEvent(this));
//            return;
//        }
//
//        throw new IllegalStateException();
//    }
//
//    // --------------------------------------------------------------------------------
//    // Misc
//    // --------------------------------------------------------------------------------
//
//    private boolean isTreeEdge(Traversal traversal, Edge edge) {
//        int source = edge.getSource();
//        int target = edge.getTarget();
//        BFSNodeMetadata targetNodeMetadata = (BFSNodeMetadata) traversal.getNodeMetadata(target);
//
//        return targetNodeMetadata.getParent() != null && targetNodeMetadata.getParent() == source;
//    }
//
//    // --------------------------------------------------------------------------------
//
//    private boolean isSideEdge(Traversal traversal, Edge edge) {
//        int source = edge.getSource();
//        int target = edge.getTarget();
//        BFSNodeMetadata targetNodeMetadata = (BFSNodeMetadata) traversal.getNodeMetadata(target);
//
//        return targetNodeMetadata.getParent() != null && targetNodeMetadata.getParent() != source;
//    }
//}
