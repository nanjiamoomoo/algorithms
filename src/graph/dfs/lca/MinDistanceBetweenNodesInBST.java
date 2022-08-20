package graph.dfs.lca;

public class MinDistanceBetweenNodesInBST {

    /**
     * Distance between two given nodes in a given binary search tree.
     *
     * Assumption:
     * 1. there is no parent pointer.
     * 2. both nodes belong to the tree.
     *
     *            15
     *
     *         /      \
     *
     *        5         20
     *
     *      /    \
     *
     *    3        10
     *
     *  /   \     /
     *
     * 1   4    8
     *
     *        /   \
     *
     *      7     9
     *
     *
     *
     * distance(3, 8) = 3
     *
     * distance(5, 7) = 3
     *
     * distance(1, 9) = 5
     *
     * @param root
     * @param one
     * @param two
     * @return
     */
    public int minDistanceBetweenNodesInBST(TreeNode root, TreeNode one, TreeNode two) {

        /*
               step1: find the lca of one and two
               step2: find the distance between each node to the lca
               step3: add the distance
         */
        TreeNode lca = lowestCommonAncestor(root, one, two);
        int dist1 = getHeight(lca, one);
        int dist2 = getHeight(lca, two);
        return dist1 + dist2;
    }

    /**
     * find the lca of node one and two in BST
     * @param root
     * @param one
     * @param two
     * @return
     */
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
        if (one.key < root.key && two.key < root.key) {
            return lowestCommonAncestor(root.left, one, two);
        }
        if (one.key > root.key && two.key > root.key) {
            return lowestCommonAncestor(root.right, one, two);
        }
        return root;
    }

    /**
     * get height of the node one under root
     * @param root
     * @param one
     * @return
     */
    private int getHeight(TreeNode root, TreeNode one) {
        int height = 0;
        while (root != one) {
            if (root.key < one.key) {
                root = root.right;
            } else {
                root = root.left;
            }
            height++;
        }
        return height;
    }
}
