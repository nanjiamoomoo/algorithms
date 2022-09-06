package graph.bst;

import graph.dfs.lca.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ClosestNumberInBSTI {
    /**
     * In a binary search tree, find the node containing the closest number to the given target number.
     * Assumption:
     * The given root is not null.
     * There are no duplicate keys in the binary search tree.
     * Examples:
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
     * closest number to 4 is 5
     * closest number to 10 is 11
     * closest number to 6 is 6
     * @param root
     * @param target
     * @return
     */
    public int closest(TreeNode root, int target) {
          /*
        Better method:
        if the root.key < target, then the closer element can only be in the right tree
        if the root.key > target, then the closer element can only be in the left tree

        TC: O(h)
        SC: O(1)
     */
        int min = Integer.MAX_VALUE;
        TreeNode minNode = null;
        while (root != null) {
            if (root.key == target) {
                return root.key;
            }
            if (Math.abs(root.key - target) < min) {
                minNode = root;
                min = Math.abs(root.key - target);
            }
            if (root.key < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return minNode.key;
        /*
                the in order traversal of the BST is in ascending order

                We can in order level traversal the tree
                use List of size one to keep the closest element
                the updating rule is
                    if the new element is closer to the target, we update
                    otherwise, we find the closest element to the target

                //TC: O(n)
                //SC: O(h)
         */
//        List<Integer> closest = new ArrayList<>();
//        findClosest(root, target, closest);
//        return closest.get(0);
    }
//
    private boolean findClosest(TreeNode root, int target, List<Integer> closest) {
        if (root == null) {
            return false;
        }
        if (findClosest(root.left, target, closest)) {
            return true;
        }
        if (closest.size() == 0) {
            closest.add(root.key);
        } else {
            if (Math.abs(root.key - target) < Math.abs(closest.get(0)- target)) {
                closest.set(0, root.key);
            } else {
                return true;
            }
        }

        if (findClosest(root.right, target, closest)) {
            return true;
        }
        return false;
    }


}
