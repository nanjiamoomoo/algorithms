package graph.classicgraphexpression;

import java.util.*;

public class TopologicalSort {

    public static List<Node<Integer>> topology(Graph<Integer> graph) {
        List<Node<Integer>> res = new ArrayList<>();
        Map<Node<Integer>, Integer> inDegree = new HashMap<>();
        Queue<Node<Integer>> queue = new ArrayDeque<>();

        for (Node<Integer> node : graph.nodes.values()) {
            inDegree.put(node, node.in);
            if (node.in == 0) {
                queue.offer(node);
            }
        }

        while(!queue.isEmpty()) {
            Node<Integer> expNode = queue.poll();
            res.add(expNode);
            for (Node<Integer> neighbor : expNode.neighbors) {
                int neiInDegree = inDegree.get(neighbor);
                inDegree.put(neighbor, neiInDegree - 1);
                if (neiInDegree == 1) {
                    queue.offer(neighbor);
                }
            }
        }
        return res;
    }
}
