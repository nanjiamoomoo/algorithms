package graph.bst;

import graph.dfs.lca.TreeNode;

public class InsertInBST {

    /**
     * Insert a key in a binary search tree if the binary search tree does not already contain the key. Return the root of the binary search tree.
     * Assumption:
     * There are no duplicate keys in the binary search tree
     * If the key is already existed in the binary search tree, you do not need to do anything
     * @param root
     * @param key
     * @return
     */
   public TreeNode insert(TreeNode root, int key) {
        /*
            Step1: search the node with value key until we reach to null
            Step2: if we reach to null, we generate a new node with value key and return the new Node
         */

       if (root == null) {
           return new TreeNode(key);
       }

       //if root.key < key, insert on the right subtree
       if (root.key < key) {
           root.right = insert(root.right, key);
       }
       //if root.key > key, insert on the left subtree
       if (root.key > key) {
           root.left = insert(root.left, key);
       }

       return root;
       /*
          Iteratively:

       TreeNode curr = root;
       TreeNode prev = null;
       while (curr != null && curr.key != key) {
           prev = curr;
           if (curr.key < key) {
               curr = curr.right;
           } else {
               curr = curr.left;
           }
       }
       if (curr == null) {
           TreeNode newNode = new TreeNode(key);
           if (prev != null) {
               if (key < prev.key) {
                   prev.left = newNode;
               } else {
                   prev.right = newNode;
               }
           } else {
               return newNode;
           }
       }
       return root;
       */

   }
}
