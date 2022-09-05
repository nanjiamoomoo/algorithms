package graph.bfs;

import recursion.lca.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GetKeysInBSTLayerByLayer {

    /**
     * Get the list of list of keys in a given binary tree layer by layer. Each layer is represented by a list of keys and the keys are traversed from left to right.
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
     * the result is [ [5], [3, 8], [1, 4, 11] ]
     * @param root
     * @return
     */
    public List<List<Integer>> layerByLayer (TreeNode root) {
        /*
            standard BFS algorithm
            Data Structure: Queue
            Expansion /Generate rule: When expand a node, generate its child from left to right
            Termination rule: queue is empty

            How to make sure we are getting keys layer by layer, we can use queue size
            for each while loop
                we will make sure we expand the node in current layer and generate all the nodes in next lary
         */
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- != 0) {
                TreeNode curr = queue.poll();
                list.add(curr.key);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
