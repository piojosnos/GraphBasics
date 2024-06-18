package org.cyrano.dsa.graph.impl.example;

import org.cyrano.dsa.graph.impl.adjacencylist.GraphAdjacencyListDouble;
import org.cyrano.dsa.graph.interfaces.Direction;
import org.cyrano.dsa.graph.interfaces.Edge;
import org.cyrano.dsa.graph.interfaces.Graph;

import java.util.Iterator;

public class Main2 {

    public static void main(String[] args) {

        Graph<Integer> graph =
                new GraphAdjacencyListDouble<>(true);
//        Graph<Integer> graph =
//                new GraphAdjacencyMatrix(true, 9);

        graph.insertNode(0);
        graph.insertNode(1);

        graph.insertEdge(0, 1);

        // ----------------------------------------

        graph.insertNode(3);
        graph.insertNode(5);

        graph.insertEdge(0, 3);
        graph.insertEdge(1, 5);

        // ----------------------------------------

        graph.insertNode(2);
        graph.insertNode(4);

        graph.insertEdge(1, 2);
        graph.insertEdge(2, 1);
        graph.insertEdge(3, 2);
        graph.insertEdge(1, 4);

        // ----------------------------------------

        graph.insertNode(8);
        graph.insertNode(7);

        graph.insertEdge(2, 8);
        graph.insertEdge(8, 4);

        graph.insertEdge(2, 7);
        graph.insertEdge(7, 4);

        // ----------------------------------------

        graph.insertNode(6);

        graph.insertEdge(4, 6);
        graph.insertEdge(5, 6);
        graph.insertEdge(6, 7);

        // ----------------------------------------

        printAdjacents(graph, 1, Direction.SOURCE_TO_TARGET);
        printAdjacents(graph, 1, Direction.TARGET_TO_SOURCE);

        // ----------------------------------------

        graph.deleteEdge(1, 5);

        // ----------------------------------------

        printAdjacents(graph, 1, Direction.SOURCE_TO_TARGET);
        printAdjacents(graph, 1, Direction.TARGET_TO_SOURCE);

        // ----------------------------------------

        graph.deleteNode(2);

        // ----------------------------------------

        printAdjacents(graph, 1, Direction.SOURCE_TO_TARGET);
        printAdjacents(graph, 1, Direction.TARGET_TO_SOURCE);
    }

    // --------------------------------------------------------------------------------

    private static void printAdjacents(Graph graph, int node, Direction direction) {
        Iterator<Edge<Integer>> itt =
                graph.adjacent(node, direction);

        System.out.println("Adjacent of " + node + ", " + direction + " : ");

        while (itt.hasNext()) {
            Edge<Integer> edge = itt.next();
            System.out.println(
                    "src=" + edge.getSource() + ", tgt=" + edge.getTarget());
        }
    }
}
