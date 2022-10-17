package graph.classicgraphexpression;

import java.util.*;

public class DFS {

    public static List<Node<Integer>> dfs(Node<Integer> node) {
        List<Node<Integer>> res = new ArrayList<>();
        Deque<Node<Integer>> stack = new ArrayDeque<>();  //stack keeps all the nodes in the current dfs path
        Set<Node<Integer>> visited = new HashSet<>();
        stack.offerFirst(node);
        visited.add(node);
        res.add(node);
        while (!stack.isEmpty()) {
            Node<Integer> currNode = stack.pollFirst();
            for (Node<Integer> neighbor : currNode.neighbors) {
                if (visited.add(neighbor)) {
                    stack.offerFirst(currNode);
                    stack.offerFirst(neighbor); //stack keeps all the nodes in the current dfs path
                    res.add(neighbor);
                    break;
                }
            }
        }
        return res;

    }

    public static List<Node<Integer>> res = new ArrayList<>();
    public static Set<Node<Integer>> visited = new HashSet<>();
    public static void dfs(Node<Integer> node, List<Node<Integer>> res, Set<Node<Integer>> visited) {
        if (visited.contains(node)) {
            return;
        }
        res.add(node);
        visited.add(node);
        for (Node<Integer> neighbor : node.neighbors) {
            dfs(neighbor, res, visited);
        }
        //we don't need to remove here since the node we visited will not visit again
    }
}
