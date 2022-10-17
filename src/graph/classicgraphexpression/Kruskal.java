package graph.classicgraphexpression;

import java.util.*;

public class Kruskal {

    public static class UnionSet {
        private Map<Node, Node> parents;
        private Map<Node, Integer> sizeMap;

        public UnionSet(Collection<Node> nodeSet) {
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node node : nodeSet) {
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node node) {
            if (node == parents.get(node)) {
                return node;
            }
            Node ans = findFather(parents.get(node));
            parents.put(node, ans);
            return ans;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            Node aParent = parents.get(a);
            Node bParent = parents.get(b);
            if (aParent == bParent) {
                return;
            }
            Node big = sizeMap.get(a) >= sizeMap.get(b) ? a : b;
            Node small = big == a ? b : a;
            parents.put(small, big);
            sizeMap.put(big, sizeMap.get(a) + sizeMap.get(b));
            sizeMap.remove(small);
        }

        public int sets() {
            return sizeMap.size();
        }
    }

    public static List<Edge> kruskalAlgorithm(Graph graph) {
        UnionSet uf = new UnionSet(graph.nodes.values());

        PriorityQueue<Edge> edges = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        Set<Edge> edgeSet = graph.edges;
        for (Edge edge : edgeSet) {
            edges.offer(edge);
        }

        List<Edge> res = new ArrayList<>();
        while (!edges.isEmpty()) {
            Edge curr = edges.poll();
            if (!uf.isSameSet(curr.from, curr.to)) {
                res.add(curr);
                uf.union(curr.from, curr.to);
                if (uf.sets() == 1) {
                    return res;
                }
            }
        }
        return res;
    }
}
