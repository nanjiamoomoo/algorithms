package graph.bst;

import graph.dfs.lca.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RecoverBST {

    /**
     * Given a Binary Search Tree with only two nodes swapped. Try to find them and recover the binary search tree.
     * Input:
     *                4
     *               / \
     *              2   6
     *             / \ / \
     *            1  5 3  7
     *
     * Output:       4
     *              / \
     *             2   6
     *          /  \   / \
     *         1   3  5  7
     *
     * @param root
     * @return
     */
    public TreeNode recover(TreeNode root) {
       /*
            Method 1:
               Step1: In order traverse the BST
               Step2: find the swapped node based on in order traversal sequence
               Step3: traverse the tree again and change the node value

               TC:O(n)
               SC: O(n)

        */
        List<Integer> inOrder = new ArrayList<>();
        inOrderTraversal(root, inOrder);
        int[] swappedNodes = new int[2];

        findSwappedNodes(inOrder, swappedNodes);

        recover(root, swappedNodes);
        return root;
    }

    private void recover(TreeNode root, int[] swappedNodes) {
        if (root == null) {
            return;
        }
        if (root.key == swappedNodes[0] || root.key == swappedNodes[1]) {
            root.key = root.key == swappedNodes[0] ? swappedNodes[1] : swappedNodes[0];
        }
        recover(root.left, swappedNodes);
        recover(root.right, swappedNodes);
    }

    private void inOrderTraversal(TreeNode root, List<Integer> inOrder) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, inOrder);
        inOrder.add(root.key);
        inOrderTraversal(root.right, inOrder);
    }

    private void findSwappedNodes(List<Integer> list, int[] swappedNodes) {
        boolean findFirstSwappedNode = false;
        for(int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) < list.get(i)) {
                swappedNodes[1] = list.get(i + 1);
                if (!findFirstSwappedNode) {
                    swappedNodes[0] = list.get(i);
                } else {
                    break;
                }
            }
        }
    }

    /*
        Method 2: use InOrder Traversal Iteratively
        we construct inorder traversal by iterations and identify swapped nodes at the same time, in one pass.

        We also can do this in one path recursively.
        TC: O(n)
        SC: O(h)
          public TreeNode recover(TreeNode root) {
            TreeNode originRoot = root;
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode[] swappedNodes = new TreeNode[2];
            TreeNode prev = null;
            while (!stack.isEmpty() || root != null) {
              while (root != null) {
                stack.offerFirst(root);
                root = root.left;
              }
              root = stack.pollFirst();
              if (prev != null && prev.key > root.key) {
                swappedNodes[1] = root;
                if (swappedNodes[0] == null) {
                  swappedNodes[0] = prev;
                } else {
                  break;
                }
              }
              prev = root;
              root = root.right;

            }
            swap(swappedNodes[0], swappedNodes[1]);
            return originRoot;
          }

          private void swap(TreeNode a, TreeNode b) {
            int tmp = a.key;
            a.key = b.key;
            b.key = tmp;
          }
     */

    /*
        Best Solution: Morris Inorder Traversal
        To Explore
     */
}
