package graph.dfs.maxpathsum;

import Recursion.lca.TreeNode;

public class BinaryTreePathSumToTargetI {

    /**
     * Given a binary tree and a target sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given target.
     *
     * Example:
     * Given the below binary tree and target = 16,
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           1    3  4
     *          /  \      \
     *         7    2      1
     * return true, as there exist a root-to-leaf path 5-8-3 which sum is 16.
     * @param root
     * @param target
     * @return
     */
    public boolean existBinaryTreePathSumToTargetI(TreeNode root, int target) {
        /*
            use dfs with prefix sum
            we can find all the possible path sum from root to leaf and compare with target, if we found a match return true, otherwise return false

            existBinaryTreePathSumToTargetI(TreeNode root, int prefixSum, int target)

               //base case
               if reach to leaf node, compare to target

               if reach to null, return false;

              on current layer: we update prefixSum += root.key
              how many states to generate: left and right

              early termination? yes, if we find a true, we directly return true;

         */
        return existBinaryTreePathSumToTargetI(root, 0, target);
    }

    private boolean existBinaryTreePathSumToTargetI(TreeNode root, int prefixSum, int target) {
        if (root == null) {
            return false;
        }

        //leaf node
        if (root.left == null && root.right == null) {
            if (prefixSum + root.key == target) {
                return true;
            }
            return false;
        }

        //on current layer
        if (existBinaryTreePathSumToTargetI(root.left, prefixSum + root.key, target)) {
            return true;
        }
        if (existBinaryTreePathSumToTargetI(root.right, prefixSum + root.key, target)) {
            return true;
        }
        return false;
    }
}
