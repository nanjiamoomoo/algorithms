package graph.dfs;

import graph.dfs.lca.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class ClosestNumberInBSTII {

    /**
     * In a binary search tree, find k nodes containing the closest numbers to the given target number. return them in sorted array
     * Assumption:
     * The given root is not null.
     * There are no duplicate keys in the binary search tree.
     * Examples:
     *     5
     *   /   \
     * 2     11
     *      /  \
     *     6   14
     *
     * closest number to 4 is 5
     * closest number to 10 is 11
     * closest number to 6 is 6
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    public int[] closestKValues(TreeNode root, double target, int k) {
        /*
            Naive method:
                using the property of BST
                in order traversal is in ascending order
                2 5 6 11 14

                How to find the closest k?
                    find the closest element first
                    then find the closest k element
            TC: O(n) + O(logn + k)
            other ways to find the closest k element?
            Yes. We can use a sliding window of k to keep the current closet element to k
                 e.g.   2 5 6 11 14  target is 8 the result is 5, 6, 11
                 [5  6  8

                 if the current sliding window size is smaller than k, we add the new element to it
                 if the sliding window size is k, we compare distance of the new element to the target with the most left element to the target, if it is smaller, then we add it,
                    otherwise we can stop, since we already find the k closest elements.
                    The best data structure for the sliding window is queue.
             combine with the in order traversal, we can find the result in O(n)


         */

        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        inOrderTraversal(root, queue, target, k);
        int[] res = new int[queue.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = queue.poll().key;
        }

        return res;
    }

    private boolean inOrderTraversal(TreeNode root, Queue<TreeNode> queue, double target, int k) {
        if (root == null) {
            return false;
        }

        if (inOrderTraversal(root.left, queue, target, k)) {
            return true;
        }
        if (queue.size() < k) {
            queue.offer(root);
        } else {
            int left = queue.peek().key;
            if (Math.abs(target - left) > Math.abs(target - root.key)) {
                queue.poll();
                queue.offer(root);
            } else {
                return true;
            }
        }
        if (inOrderTraversal(root.right, queue, target, k)) {
            return true;
        }
        return false;
    }
}
