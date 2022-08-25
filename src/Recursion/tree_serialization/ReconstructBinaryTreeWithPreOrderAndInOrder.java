package Recursion.tree_serialization;

import Recursion.lca.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeWithPreOrderAndInOrder {

    /**
     * Given the preorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
     * Assumption:
     * 1. The given sequences are not null and they have the same length
     * 2. There are no duplicate keys in the binary tree
     *
     * Examples:
     * preorder traversal = {5, 3, 1, 4, 8, 11}
     * inorder traversal = {1, 3, 4, 5, 8, 11}
     * the corresponding binary tree is
     *         5
     *       /    \
     *     3        8
     *   /   \        \
     * 1      4        11
     *
     * @param inOrder
     * @param preOrder
     * @return
     */
    public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
        /*
                    1
                  2   3
                4  5  6  7

                inorder:  4 2 5 1 6 3 7
                preorder: 1 2 4 5 3 6 7

                the first element in the preorder is definitely the root
                if we can find the root in the inorder array, let's assume its index is k.
                Then we can tell that all the nodes in the left side, [0, k - 1] belongs to the left subtree. The left side is also in the inorder sequence of the left subtree
                all the nodes in the right side, [k + 1, array.length - 1] belongs to the right subtree. The right side is also the inorder sequence of the right subtree

                based on above analysis, since we can break down the big problem into a smaller problem, so we can use recursion to solve the problem.

               we can define a method reconstruct(int[] inOrder, int leftIn, int rightIn, int[] preOrder, int leftPre, int rightPre)
               this method returns a Tree that can be build based on inOrder traversal sequence in the range[leftIn, rightIn] and preOrder traversal sequence in the range[leftPre, rightPre]

               what we should do on current layer?
               1. find the index boundaries for its subtrees in the inOrder[] and preOrder[]
               2. pass these indices into the recursion function to build the left subtree and right subtree
               3. create current layer root and assign the left subtree and right subtree to the root
               4. return root.

                Note: Since all the nodes are unique, we can use a map to quickly locate the index of the root in the inorder[] array.
         */
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            indexMap.put(inOrder[i], i);
        }
        return reconstruct(inOrder, 0, inOrder.length - 1, preOrder, 0, preOrder.length - 1, indexMap);
    }

    private TreeNode reconstruct(int[] inOrder, int leftIn, int rightIn, int[] preOrder, int leftPre, int rightPre, Map<Integer, Integer> indexMap) {
        //base case
        if (leftIn > rightIn) {
            return null;
        }

        int rootIndex = indexMap.get(preOrder[leftPre]);
        /*
                size of the left subtree: rootIndex - leftIn
         */
        TreeNode left = reconstruct(inOrder, leftIn, rootIndex - 1, preOrder, leftPre + 1, leftPre + rootIndex - leftIn, indexMap);
        TreeNode right = reconstruct(inOrder, rootIndex + 1, rightIn, preOrder, leftPre + rootIndex - leftIn + 1, rightPre, indexMap);
        //create root
        TreeNode root = new TreeNode(preOrder[leftPre]);
        root.left = left;
        root.right = right;
        return root;
    }
    //TC: O(n)
    //SC: O(n) map, and call stackO(h)
}
