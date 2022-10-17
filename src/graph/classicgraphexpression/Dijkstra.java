package graph.classicgraphexpression;

import java.util.*;

public class Dijkstra {

    public static class Node {
        int value;
        int in;
        int out;
        List<Node> neighbors;
        List<Edge> edges;

        public Node(int value) {
            this.value = value;
            neighbors = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    public static class Edge {
        int weight;
        Node from;
        Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public static class Graph {
        public Set<Node> nodes;
        public Set<Edge> edges;

        public Graph() {
            nodes = new HashSet<>();
            edges = new HashSet<>();
        }
    }

    public static class Distance {
        private Node node;
        private int value;

        public Distance(Node node, int value) {
            this.node = node;
            this.value = value;
        }
    }
    public static Map<Node, Integer> dijkstra(Graph graph, Node from) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        PriorityQueue<Distance> priorityQueue = new PriorityQueue<>(new Comparator<Distance>() {
            @Override
            public int compare(Distance o1, Distance o2) {
                return o1.value - o2.value;
            }
        });

        priorityQueue.offer(new Distance(from, 0));
        while(!priorityQueue.isEmpty()) {
            Distance distance = priorityQueue.poll();
            if (distanceMap.containsKey(distance.node)) {
                continue;
            }
            distanceMap.put(distance.node, distance.value);
            for (Edge edge : distance.node.edges) {
                if (!distanceMap.containsKey(edge.to)) {
                    priorityQueue.offer(new Distance(edge.to, distance.value + edge.weight));
                }
            }
        }
        return distanceMap;
    }
}
