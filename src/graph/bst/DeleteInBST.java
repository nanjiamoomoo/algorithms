package graph.bst;

import graph.dfs.lca.TreeNode;

public class DeleteInBST {

    /**
     * Delete the target key K in the given binary search tree if the binary search tree contains K. Return the root of the binary search tree.
     * Find your own way to delete the node from the binary search tree, after deletion the binary search tree's property should be maintained.
     * Assumptions
     * There are no duplicate keys in the binary search tree
     * The smallest larger node is first candidate after deletion
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteTree(TreeNode root, int key) {
        /*
            Step 1: find the target node that is going to delete
            Step 2: delete the target node

              e.g.
                        3
                      1   7
                         5  8
                          6


              case 1: there is no left child and right child, we just remove current node by return null

              case 2： if there is only left child, we return right child
                       if there is only right child, we return left child

             case 3: there are both child
                     3.1:  if the right child has no left subtree, Then the root.right is the smallest larger candidate. we connect the root.right.left = root.left, return root.right
                     3.2: if the right child has left subtree, we will have to the find the smallest larger element on the left subtree
                            after we find it, if the smallest larger element has right tree, we connect the right subtree to the left of the parent of the smallest larger
                            if the smallest larger element has no right tree, we can just connect its left = root.left, its right = root.right, return the smallest larger


            TC: O(h)
            SC: O(h)

         */

        if (root == null) {
            return null;
        }
        if (root.key < key) {
            root.right = deleteTree(root.right, key);
            return root;
        }
        if (root.key > key) {
            root.left = deleteTree(root.left, key);
            return root;
        }

        //at this stage, we know the root.key == key, we delete the root.
        //case1 and case 2
        if (root.left == null || root.right == null) {
            return root.left == null ? root.right : root.left;
        }

        //case 3.1
        if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }

        //case 3.2
        TreeNode prev = null;
        TreeNode smallest = root.right;
        while (smallest.left != null) {
            prev = smallest;
            smallest = smallest.left;
        }
//        if (smallest.right == null) {
//            prev.left = null;
//        } else {
//            prev.left = smallest.right;
//        }
        prev.left = smallest.right;
        smallest.left = root.left;
        smallest.right = root.right;
        return smallest;
    }
}
