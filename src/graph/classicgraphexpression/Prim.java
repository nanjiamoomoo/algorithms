package graph.classicgraphexpression;

import java.util.*;

public class Prim {

    public static List<Edge> primAlgorithm(Graph graph) {

        Set<Node> nodeSet = new HashSet<>(); //keep all the unlocked node
        Set<Edge> edgeSet = new HashSet<>(); //keep all the unlocked edge
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        List<Edge> res = new ArrayList<>();
        Collection<Node> nodes = graph.nodes.values();
        for (Node node : nodes) { //only if the original graph has many disconnected sub-graphs
            //node is the start
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                //generate start node edges
                List<Edge> edges = node.edges;
                for (Edge edge: edges) {
                    if (!edgeSet.contains(edge)) {
                        minHeap.offer(edge);
                        edgeSet.add(edge);
                    }
                }
                while (!minHeap.isEmpty()) {
                    Edge edge = minHeap.poll();
                    if (!nodeSet.contains(edge.to)) {
                        res.add(edge);
                        nodeSet.add(edge.to);
                        List<Edge> neiEdges = edge.to.edges;
                        for (Edge curr : neiEdges) {
                            if (!edgeSet.contains(curr)) {
                                minHeap.offer(curr);
                                edgeSet.add(curr);
                            }
                        }
                    }
                }
               //break;
            }
        }
        return res;
    }
}
