package recursion.tree_serialization;

import recursion.lca.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ReconstructBSTWithLevelOrderTraversal {

    /**
     * Given the level order traversal sequence of a binary search tree, reconstruct the original tree.
     * Assumptions
     * The given sequence is not null
     * There are no duplicate keys in the binary search tree
     *
     * Examples
     * level order traversal = {5, 3, 8, 1, 4, 11}
     * the corresponding binary search tree is
     *         5
     *       /    \
     *     3        8
     *   /   \        \
     * 1      4        11
     * @param levelOrder
     * @return
     */
    public TreeNode reconstruct(int[] levelOrder) {
        /*

                step1: find the root
                step2: find the left tree level order traversal and right tree level order traversal sequence
         */
        List<Integer> levelOrderList = new ArrayList<>();
        for (Integer value : levelOrder) {
            levelOrderList.add(value);
        }
        return reconstruct(levelOrderList);
    }
    private TreeNode reconstruct(List<Integer> levelOrder) {
        if (levelOrder.size() == 0) {
            return null;
        }
        TreeNode root = new TreeNode(levelOrder.get(0));
        List<Integer> leftLevelOrder = new ArrayList<>();
        List<Integer> rightLevelOrder = new ArrayList<>();
        for (Integer value : levelOrder) {
            if (value < root.key) {
                leftLevelOrder.add(value);
            } else if (value > root.key) {
                rightLevelOrder.add(value);
            }
        }
        root.left = reconstruct(leftLevelOrder);
        root.right = reconstruct(rightLevelOrder);
        return root;
    }
}
