package graph.classicgraphexpression;

import java.util.*;

public class BFS{

    public static List<Node<Integer>> bfs(Node<Integer> node) {
        List<Node<Integer>> res = new ArrayList<>();
        Queue<Node<Integer>> queue = new ArrayDeque<>();
        Set<Node<Integer>> visited = new HashSet<>(); //we need a set because there might be a circle
        queue.offer(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            Node<Integer> currNode = queue.poll();
            res.add(currNode);
            for (Node<Integer> neighbor : currNode.neighbors) {
                if (visited.add(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }
        return res;
    }
}
