package graph.bfs;

import graph.dfs.lca.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class DetermineIfBinaryTreeIsMinHeap {

    /**
     * Determine if the given binary tree is min heap.
     * Assumption:
     * Tree is not null.
     *
     * @param root
     * @return
     */
    public boolean isMinHeap(TreeNode root) {

        /*
            We can use BFS to do level traversal of the binary tree
            Since the heap is complete binary tree, we have to make sure that if we generated a null, we cannot generate another null node anymore
            We need to use a flag to mark if we have met the first null. In the beginning, we mark the flag as false.
            Data structure: Queue
            Expand/generation rule:
                When we expand a node, we generate its left child and right child.
                    Left child:
                    if the left child is null
                        if the flag is true, then we ignore
                        if the flag is false, we mark the flag to true
                    if the left child is not null
                        if the flag is true or the value is smaller than parent node, then we return true
                        if the flag is false, we offer it in the queue

                    right child:
                    the same as left child
         */

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean firstNull = false;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left == null) {
                firstNull = true;
            } else if (firstNull || curr.left.key < curr.key) {
                return false;
            } else {
                queue.offer(curr.left);
            }
            if (curr.right == null) {
                firstNull = true;
            } else if (firstNull || curr.right.key < curr.key) {
                return false;
            } else {
                queue.offer(curr.right);
            }
        }
        return true;
    }
}
