package Recursion.tree_serialization;

import Recursion.lca.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReconstructBinaryTreeWithLevelOrderAndInOrder {

    /**
     * Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
     * Assumption:
     * 1. The given sequences are not null and they have the same length
     * 2. There are no duplicate keys in the binary tree
     * Examples
     * levelorder traversal = {5, 3, 8, 1, 4, 11}
     * inorder traversal = {1, 3, 4, 5, 8, 11}
     * the corresponding binary tree is
     *         5
     *       /    \
     *     3        8
     *   /   \        \
     * 1      4        11
     * @param inOrder
     * @param levelOrder
     * @return
     */
    public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
        /*
            we can find root node from levelOrder traversal easily
            how to boil down the problem into a smaller problem
                can we find the inOrder traversal and level order traversal sequence of left and right subtree
                inOrder is easy.
                What about level order, we will need to traverse the level order sequence and for each element we should look at its index in the inOrder traversal,
                    if it is to the left side of the root, then it is in the left subtree
                    if it is to thr right side of the root, then it belongs to the right subtree

              recursion(int[] inOrder, int leftIn, int rightIn, List<Integer> levelOrder, Map<Integer, Integer> indexMap)

                1. root  levelOrder.get(0)
                   rootIndex = indexMap.get(root.key);
                2. find level order sequence of subtrees
                for (Integer value : levelOrder) {
                        if (indexMap.get(value) < rootIndex) {
                        } else {
                        }
                   }
         */
        List<Integer> levelOrderList = new ArrayList<>();
        for (Integer value : levelOrder) {
            levelOrderList.add(value);
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            indexMap.put(inOrder[i], i);
        }
        return reconstruct(0, inOrder.length - 1, levelOrderList, indexMap);
    }

    private TreeNode reconstruct(int leftIn, int rightIn, List<Integer> levelOrder, Map<Integer, Integer> indexMap) {
        if (leftIn > rightIn) {
            return null;
        }

        TreeNode root = new TreeNode(levelOrder.get(0));
        int rootIndex = indexMap.get(root.key);
        List<Integer> leftLevelOrder = new ArrayList<>();
        List<Integer> rightLevelOrder = new ArrayList<>();
        for (Integer value : levelOrder) {
            if (indexMap.get(value) < rootIndex) {
                leftLevelOrder.add(value);
            } else if (indexMap.get(value) > rootIndex) {
                rightLevelOrder.add(value);
            }
        }

        //construct left subtree
        TreeNode left = reconstruct(leftIn, rootIndex - 1, leftLevelOrder, indexMap);
        TreeNode right = reconstruct(rootIndex + 1, rightIn, rightLevelOrder, indexMap);
        root.left = left;
        root.right = right;
        return root;

    }

    public static void main(String[] args) {
        int[] inOrder = {1,6,5,7,4,10,9};
        int[] levelOrder = {4,1,10,5,9,6,7};
        ReconstructBinaryTreeWithLevelOrderAndInOrder reconstructBinaryTreeWithLevelOrderAndInOrder = new ReconstructBinaryTreeWithLevelOrderAndInOrder();
        reconstructBinaryTreeWithLevelOrderAndInOrder.reconstruct(inOrder,levelOrder);
    }
}
