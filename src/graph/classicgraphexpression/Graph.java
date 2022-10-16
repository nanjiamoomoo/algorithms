package graph.classicgraphexpression;

import java.util.*;

public class Graph<V> {
    public Map<V, Node<V>> nodes;
    public Set<Edge<V>> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}

class Node<V> {
    public V value;
    public int in;
    public int out;
    List<Node<V>> neighbors;
    List<Edge<V>> edges;

    public Node(V value) {
        this.value = value;
        neighbors = new ArrayList<>();
        edges = new ArrayList<>();
    }
}

class Edge<V> {
    public int weight;
    public Node<V> from;
    public Node<V> to;

    public Edge(int weight, Node<V> from, Node<V> to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}

