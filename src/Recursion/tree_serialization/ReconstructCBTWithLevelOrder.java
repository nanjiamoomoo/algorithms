package Recursion.tree_serialization;

import Recursion.lca.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class ReconstructCBTWithLevelOrder {

    /**
     * How to reconstruct a complete binary tree from its level-order traversal sequence only.
     * Assumption:
     * The given level-order is not null.
     * @param level
     * @return
     */

    //method1: DFS
    public TreeNode reconstruct(int[] level) {
        /*
            since it is complete binary tree we can easily find its left child index and right child index
                left child : 2n + 1
                right child: 2n + 2

             so I can define a method reconstruct(int[] level, int index)
              this method returns the constructed tree with index node as the root based on the level order sequence within in range [left, right]
         */
        return reconstruct(level, 0);
    }

    private TreeNode reconstruct(int[] level, int index) {
        if (index >= level.length) {
            return null;
        }

        //root
        TreeNode root = new TreeNode(level[index]);
        //left child
        root.left = reconstruct(level, 2* index + 1);
        root.right = reconstruct(level, 2 * index + 2);
        return root;
    }

    //method2: BFS
    public TreeNode reconstructBFS(int[] level) {
        /*
            since it is complete binary tree we can easily find its left child index and right child index
                left child : 2n + 1
                right child: 2n + 2

             we can traverse the level[], for each element, we generate its child(neighbor in graph)
                    1 2 3 4 5
              we can use queue data structure

             In normal BFS for a tree, we generate neighbors based on the expanded node
             but in the case, we generate neighbors based on the index of the expanded node in the array and assign new nodes as left, right child
             < 1 2 3 4 5 6>
                       i

               [ 6
               1
              2  3
             4 5 6
         */
        if (level == null || level.length == 0) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        int index = 0;
        TreeNode root = new TreeNode(level[0]);
        queue.offer(root);
        /*
            instead of using !queue.isEmpty() we also can use index to terminate. We know that for complete binary tree
            the last parent node index is size / 2 - 1
         */
        while (index <= level.length / 2 - 1) {
            TreeNode curr = queue.poll();
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            if (left < level.length) {
                curr.left = new TreeNode(level[left]);
                queue.offer(curr.left);
            }
            if (right < level.length) {
                curr.right = new TreeNode(level[right]);
                queue.offer(curr.right);
            }
            index++;
        }
        return root;


    }

}
