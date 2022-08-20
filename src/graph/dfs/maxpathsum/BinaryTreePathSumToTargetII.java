package graph.dfs.maxpathsum;

import Recursion.lca.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BinaryTreePathSumToTargetII {

    /**
     * Given a binary tree in which each node contains an integer number. Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), the sum of the numbers on the path is the given target number.
     * <p>
     * Examples
     * <p>
     * 5
     * <p>
     * /    \
     * <p>
     * 2      11
     * <p>
     * /    \
     * <p>
     * 6     14
     * <p>
     * /
     * <p>
     * 3
     * <p>
     * If target = 17, There exists a path 11 + 6, the sum of the path is target.
     * <p>
     * If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.
     * <p>
     * If target = 10, There does not exist any paths sum of which is target.
     * <p>
     * If target = 11, There exists a path only containing the node 11.
     *
     * @param root
     * @return
     */
    public boolean existBinaryTreePathSumToTargetII(TreeNode root, int target) {
        /*
            we can use dfs with List<Integer> prefixPath

            prefixPath[i] includes the prefixSum of all nodes from the tree root to node i

            on each layer, we can use prefixSum to check if there exists a subPath (including current root) that its sum equals the target

            ont current layer
                1. we check if there exists a subPath equals to target, if there is, return true, or return false
                early termination? yes

            how many states to generate? left and right

            if reach to null, return false;

            Time complexity? :
                            x          1 * 1
                        x       x      2 * 2
                    x     x   x    x   4 * 3
                                     ...



         */

        return existBinaryTreePathSumToTargetII(root, new ArrayList<>(), target);
    }

    private boolean existBinaryTreePathSumToTargetII(TreeNode root, List<Integer> prefixSum, int target) {
        if (root == null) {
            return false;
        }

        //check if there is subPath ends at current root can match the target
        int pathSum = prefixSum.size() == 0 ? root.key : prefixSum.get(prefixSum.size() - 1) + root.key;
        if (pathSum == target) {
            return true;
        }
        prefixSum.add(pathSum);
        for (int i = 0; i < prefixSum.size() - 1; i++) {
            if (pathSum - prefixSum.get(i) == target) {
                return true;
            }
        }

        // how many states to generate on current level
        if (existBinaryTreePathSumToTargetII(root.left, prefixSum, target)) {
            return true;
        }
        if (existBinaryTreePathSumToTargetII(root.right, prefixSum, target)) {
            return true;
        }
        prefixSum.remove(prefixSum.size() - 1);
        return false;


    }

    /**
     * can we make the above algorithm better?
     * yes, instead of using a List<Integer> we can use a HashSet<Integer> to keep all the prefixSums of nodes on the path
     * as long as current prefixSum - target is in the set, then we can return true;
     * trick: we can add 0 in the set, as long as target == prefixSum, we also return true;
     *
     * Another thing to pay attention, when we add a new pathSum to the prefixSum set, it is possible that there is already a same value exists int the set
     * In the case, we are actually not adding anything in the set, when we remove a node, we shouldn't remove it
     */

    private boolean existBinaryTreePathSumToTargetII(TreeNode root, Set<Integer> prefixSum, int pathSum, int target) {
        if (root == null) {
            return false;
        }
        pathSum += root.key;
        if (prefixSum.contains(pathSum - target)) {
            return true;
        }
        boolean needRemove =  prefixSum.add(pathSum);
        if (existBinaryTreePathSumToTargetII(root.left, prefixSum, pathSum, target)) {
            return true;
        }
        if (existBinaryTreePathSumToTargetII(root.right, prefixSum, pathSum, target)) {
            return true;
        }
        if (needRemove) {
            prefixSum.remove(pathSum);
        }

        return false;

        //TC: O(n)
        //SCL: O(h)
    }

}
