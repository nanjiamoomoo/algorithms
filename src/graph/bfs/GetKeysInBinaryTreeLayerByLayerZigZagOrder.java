package graph.bfs;

import graph.dfs.lca.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GetKeysInBinaryTreeLayerByLayerZigZagOrder {

    /**
     * Get the list of keys in a given binary tree layer by layer in zigZag order.
     * Examples
     *
     *         5
     *
     *       /    \
     *
     *     3        8
     *
     *   /   \        \
     *
     *  1     4        11
     *
     * the result is [5, 3, 8, 11, 4, 1]
     * @param root
     * @return
     */
    public List<Integer> zigZag(TreeNode root) {
        /*
            We use BFS algorithm to traverse layer by layer

            The first layer we traverse from right to left
            the second layer we traverse from left to right
            the third layer we traverse from right to left
            ...

            let's look at second layer and third layer
            if the second traverse from left to right, how do we generate third layer to make sure it will be expanded from right to left
            --> if we use a Queue, we have to generate from right to left. then it would be 4 1 11, not right. Then how should we do it?

            how about we use stack? if we generate 1 4 11, then we get 11 4 1, which satisfy the result?
            but here is another problem. If we use stack, and generate 1 4, then we are not able to expand 8.

            How to solve the problem?
            WHat is we use a deque? We generate third layer on the other end?
              ][ 1 4 11

              3 --> 1 4
            Then it works
            Let's summarize
            Data structure: deque
            if it is an odd layer node, we expand and generate from the right size(Last). When we generate child, if the current layer is in odd layer, we generate right child first
            if it is an even layer node, we expand and generate from the left size(first). When we generate child, if the current layer is in even layer, we generate left child first

        ][ 1 4 11
            5 -> 3 -> 8 ->
         */

        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        int level = 1;
        deque.offerLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- != 0) {
                if (level == 0) {
                    TreeNode curr = deque.pollFirst();
                    res.add(curr.key);
                    if (curr.left != null) {
                        deque.offerLast(curr.left);
                    }
                    if (curr.right != null) {
                        deque.offerLast(curr.right);
                    }
                } else {
                    TreeNode curr = deque.pollLast();
                    res.add(curr.key);
                    if (curr.right != null) {
                        deque.offerFirst(curr.right);
                    }
                    if (curr.left != null) {
                        deque.offerFirst(curr.left);
                    }
                }
            }
            level = 1 - level;
        }
        return res;
    }
}
