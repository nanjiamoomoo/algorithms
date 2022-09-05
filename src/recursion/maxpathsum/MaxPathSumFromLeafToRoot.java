package recursion.maxpathsum;

import recursion.lca.TreeNode;

public class MaxPathSumFromLeafToRoot {

    /**
     * Given a binary tree in which each node contains an integer number. Find the maximum possible path sum from a leaf to root.

     * Assumptions
     * The root of given binary tree is not null.

     * Examples
     *
     *
     *
     *          10
     *        /    \
     *     -2       7
     *   /   \
     * 8     -4
     *
     * The maximum path sum is 10 + 7 = 17.
     * @param root
     * @return
     */
    public int maxPathSumFromLeafToRoot(TreeNode root) {
        /*
                the semantics of the method maxPathSumFromLeafToRoot(root)
                    return the max path sum from leaf to root

                what are we expecting from left child: the max path sum from leaf to left child
                what are we expecting from right child: the max path sum from leaf to right child

                in current layer:
                1. we compare the left and right and choose the bigger one
                2. add root value
                3. return
         */
        //base case:
        //leaf node
        if (root.left == null && root.right == null) {
            return root.key;
        }
        //a node has no left subtree or right subtree?
        //since it requires from leaf node to root, so we have to ignore the null branch
        if (root.left == null) {
            return maxPathSumFromLeafToRoot(root.right) + root.key;
        } else if (root.right == null) {
            return maxPathSumFromLeafToRoot(root.left) + root.key;
        }

        //what is both branch are not null
        int left = maxPathSumFromLeafToRoot(root.left);
        int right = maxPathSumFromLeafToRoot(root.right);
        return left >= right ? left + root.key : right + root.key;

        //TC: O(n)
        //SC: O(h)

        /*
                we can simply the above logic
                if (root == null) return 0
                int left = maxPathSumFromLeafToRoot(root.left);
                int right = maxPathSumFromLeafToRoot(root.right);
                if (root.left != null && root.right != null {
                        return left >= right ? left + root.key : right + root.key;
                }

                return root.left == null? right + root.key : left + root.key;

         */
    }
}
