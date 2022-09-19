package graph.binarytree;

import graph.dfs.lca.TreeNode;

public class BinaryTreeDiameter {

    /**
     * Given a binary tree in which each node contains an integer number. The diameter is defined as the longest distance from one leaf node to another leaf node. The distance is the number of nodes on the path.
     * If there does not exist any such paths, return 0.
     * Examples
     *
     *     5
     *
     *   /    \
     *
     * 2      11
     *
     *      /    \
     *
     *     6     14
     *
     * The diameter of this tree is 4 (2 → 5 → 11 → 14)
     * @param root
     * @return
     */
    public int diameter(TreeNode root) {
        /*
            The problem converts to find the sum of height of leftSubtree and rightSubtree for each node
            and return the maximum sum
            If a node has only child, then we should disqualify it since there is no path from a leaf node to another leaf node through it

            TC: O(n)
            SC: O(h)
         */
        int[] distance = {0};
        getHeight(root, distance);
        return distance[0];
    }

    private int getHeight(TreeNode root, int[] distance) {
        if (root == null) {
            return 0;
        }
        int leftSubTreeHeight = getHeight(root.left, distance);
        int rightSubTreeHeight = getHeight(root.right, distance);
        if (leftSubTreeHeight != 0 && rightSubTreeHeight != 0) {
            distance[0] = Math.max(distance[0], leftSubTreeHeight + rightSubTreeHeight + 1);
        }
        return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
    }
}
