package graph.bfs;

import recursion.lca.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * check if a binary tree is a complete binary tree(CBT)
 */
public class CheckIfCBT {

    /**
     * Check if a given binary tree is completed. A complete binary tree is one in which every level of the binary tree is completely filled except possibly the last level. Furthermore, all nodes are as far left as possible.
     * Assumption: What if the binary tree is null? Return true in this case.
     * @param root
     * @return
     */
    public boolean isCompleted(TreeNode root) {
        /*
                 3
               #   4
                  1  2
                 # # # #
              level order traversal : 3 # 4 1 2 # # # #

                 3
               1   4
             1   2 # #
             level order traversal: 3 1 4 1 2 # # # # # # # #

               Q: what is the difference between a CBT and non CBT.
                    if we use level traversal of the tree, as long as we meet a #, CBT will not have another non=# element


              Data Structure: Queue
                we can use BFS to traverse the tree
                we use a flag to indicate if we have traversed a # node. in the beginning it is false. When we traversed a #, we marked it as true.
                if traverse a non-# element, if the flag is true, then we return false, other we continue. until we traverse the entire tree.

            Below shows two methods:
            method 1 takes longer time to run, but it is easier to implement.
            method 2 takes less time to run, but we need to check left tree and right tree, so it is harder to implement.
         */
        if (root == null) {
            return true;
        }

        //method 1: check at expansion


        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (!flag) {
                //if we haven't met a null, and current expanded node is null, we mark flag as true
                if (curr == null) {
                    flag = true;
                } else {
                    //we haven't met a null, the current expanded is not a null, we offer its childs
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                }
            } else {
                //if we have met a null, if current expanded is not a null, we can return false;
                if (curr != null) {
                    return false;
                }
            }

        }
        return true;



        //method2: check at generation
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        boolean flag = false;
//        while (!queue.isEmpty()) {
//            TreeNode curr = queue.poll();
//            //if current is not null, we can generate its child
//            if (curr != null) {
//                if (!flag) {
//                    //if we haven't met a null, and current left is null, we mark flag as true
//                    if (curr.left == null) {
//                        flag = true;
//                    }
//                    //after check left we have to check right
//                    if (!flag) {
//                        if (curr.right == null) {
//                            flag = true;
//                        }
//                    } else {
//                        if (curr.right != null) {
//                            return false;
//                        }
//                    }
//
//                } else {
//                    if (curr.left != null || curr.right != null) {
//                        return false;
//                    }
//                }
//                //not matter left child is null or not null, we generate it in the queue
//                queue.offer(curr.left);
//                //not matter right child is null or not null, we generate it in the queue
//                queue.offer(curr.right);
//            }
//            //if current is null, we ignore since we don't generate any child
//        }
//        return true;
    }
}
