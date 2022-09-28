package graph.binarytree;

import graph.dfs.lca.TreeNode;

import java.util.*;

public class VerticalListOfBinaryTree {

    /**
     * Given a binary tree, get the vertically representation of it as a list of lists.
     *
     * The columns should be from left to right, and for each column the nodes should be placed from top to bottom, from left to right.
     *
     * The following example illustrates vertical order traversal. Input:
     *
     *             1
     *           /   \
     *          2     3
     *         /  \  / \
     *        4   5,6   7
     *                  \     \
     *                    8     9
     *
     * Output:
     *
     * [[4],         // left most column
     *  [2],         // 2nd left-most column
     *  [1, 5, 6], // 3rd left-most column, top->bottom, left->right
     *  [3, 8],
     *  [7],
     *  [9]]
     * @param root
     * @return
     */
    public List<List<Integer>> verticalPrint(TreeNode root) {
        // Write your solution here
    /*

        First we need to count the order of each node
          if we go left, the order should be reduced by one to indicate it has higher priority to be traversed first
          if we go right, the order should be increased by one to indicate it has lower priority to be traversed
         Since the question requires each column node to be placed from top - bottom, left - right, we need to use level order traversal to traverse the tree
        Then we should add the nodes to the final result based on their order from small to large

      */
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<TreeNode, Integer>  map = new HashMap<>();
        Set<Integer> orders = new HashSet<>();
        getOrders(root, 0, orders, map);
        List<Integer> orderList = new ArrayList<>(orders);
        Map<Integer, Integer> indexMap = new HashMap<>();
        Collections.sort(orderList);
        for (int i = 0; i < orderList.size(); i++) {
            indexMap.put(orderList.get(i), i);
        }


        for (int i = 0; i < orders.size(); i++) {
            res.add(new ArrayList<>());
        }

        verticalTraversal(root, res, indexMap, map);

        return res;
    }

    private void verticalTraversal(TreeNode root, List<List<Integer>> res, Map<Integer, Integer> indexMap, Map<TreeNode, Integer> orderMap) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int index = indexMap.get(orderMap.get(curr));
            res.get(index).add(curr.key);
            if (curr.left != null) {
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
            }
        }
    }

    private void getOrders(TreeNode root, int order, Set<Integer> set, Map<TreeNode, Integer> map) {
        if (root == null) {
            return;
        }
        map.put(root, order);
        set.add(order);
        getOrders(root.left, order - 1, set, map);
        getOrders(root.right, order + 1, set, map);
    }
}
