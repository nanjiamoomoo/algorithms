package recursion.tree_serialization;

import recursion.lca.TreeNode;

public class ReconstructBSTWithPostOrderTraversal {

    /**
     * Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.
     * Assumption:
     * 1. The given sequence is not null
     * 2. There are no duplicate keys in the binary search tree
     * Examples
     * postorder traversal = {1, 4, 3, 11, 8, 5}
     * the corresponding binary search tree is
     *         5
     *       /    \
     *     3        8
     *   /   \        \
     * 1      4        11
     *
     * @param postOrder
     * @return
     */
    public TreeNode reconstruct(int[] postOrder) {
        /*
                how to boil down the problem into a smaller problem?
                    can we find the post order traversal of left subtree and right subtree?

                since we can easily find the root of the tree
                because it is BST, we can traverse the tree and find the first element that is bigger than the root.
                    All the nodes to the left belongs to the left subtree
                    All the nodes to rhe right belongs to the right subtree
                    Then we have the postOrder[] of left subtree and right subtree

                we successfully boil down the problem into smaller problems.
                    we can use recursion

                 define reconstruct(int[] postOrder, int left, int right)

                 1. find root
                 2. traverse the postOrder from left to right to find the left subtree boundary and right subtree boundary and call recursion to build left tree and right tree
                 3. create root and connect left tree and right tree
                 4. return root.
         */
        return reconstruct(postOrder, 0, postOrder.length - 1);
    }

    private TreeNode reconstruct(int[] postOrder, int left, int right) {
        if (left > right) {
            return null;
        }
        int boundary = left;
        for (; boundary <= right - 1; boundary++) {
            if (postOrder[boundary] > postOrder[right]) {
                break;
            }
        }
        TreeNode leftSubTree = reconstruct(postOrder, left, boundary - 1);
        TreeNode rightSubTree = reconstruct(postOrder, boundary, right - 1);
        TreeNode root = new TreeNode(postOrder[right]);
        root.left = leftSubTree;
        root.right = rightSubTree;
        return root;
    }
    //TC: O(n ^2) in the worst scenario, such [1 2 3 4 5]
    //SC: O(h)
}
