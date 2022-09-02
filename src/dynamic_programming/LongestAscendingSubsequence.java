package dynamic_programming;

import java.util.ArrayList;
import java.util.List;

public class LongestAscendingSubsequence {

    /**
     * Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.
     * Assumption: A is not null
     * Input: A = {5, 2, 6, 3, 4, 7, 5}
     * Output: 4
     * Because [2, 3, 4, 5] is the longest ascending subsequence.
     *
     * @param array
     * @return
     */
    public int longestAscendingSubsequence(int[] array) {
        /*
            we can use dp to solve this problem
            Define an array longestSubsequence[i] represents the longest subsequence from 0th index to ith index, including ith index
            longestSubsequence[i] =
                the max of
                    for each position: j from 0 ~ i - 1
                    if (array[i] > array[j]) longestSubsequence[j] + 1
                    else 1

            Clarification is 3 3 3 can be considered as ascending? no.
         */
        if (array.length == 0) {
            return 0;
        }
        int longest = 1;
        int[] longestSubsequence = new int[array.length];
        longestSubsequence[0] = 1;
        for (int i = 1; i < array.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    max = Math.max(max, longestSubsequence[j] + 1);
                }
            }
            longestSubsequence[i] = max;
            longest = Math.max(longest, max);
        }
        return longest;
    }
    //TC: N ^ 2


    /**
     * Given an array A[0]...A[n-1] of integers, find out the longest ascending subsequence. If there are multiple results, then return any valid result.
     * Assumptions： A is not null
     * Examples:
     * Input: A = {5, 2, 6, 3, 4, 7, 5}
     * Output: [2,3,4,5]
     * Because [2, 3, 4, 5] is one of the longest ascending subsequences.
     *
     * @param array
     * @return
     */
//    public int[] longestAscendingSubsequenceII(int[] array) {
//        /*
//
//             DFS: naive method
//                               ""
//                             5
//                         2    “”   2  ""
//
//              for each recursion level, we can either add an element to the list or not add
//              Note: we can only add if the element in current layer is bigger than previous added element or the list is empty
//
//               There is no limitation on the not add.
//
//               when the globalMax is updated, we make a copy of the current list and store it in the result list.
//
//               TC: 2 ^ n
//         */
//
//        if (array.length == 0) {
//            return new int[0];
//        }
//        List<Integer>[] res = new List[1];
//        List<Integer> path = new ArrayList<>();
//        int[] max = {0};
//        helper(res, path, array, 0, max);
//        int[] result = new int[res[0].size()];
//        for (int i = 0; i < result.length; i++) {
//            result[i] = res[0].get(i);
//        }
//        return result;
//    }
//
//    private void helper(List<Integer>[] res, List<Integer> path, int[] array, int index, int[] max) {
//        if (index == array.length) {
//            if (path.size() > max[0]) {
//                res[0] = new ArrayList<>(path);
//                max[0] = path.size();
//            }
//            return;
//        }
//
//        //add
//        if (path.isEmpty() || array[index] > path.get(path.size() - 1)) {
//            path.add(array[index]);
//            helper(res, path, array, index + 1, max);
//            path.remove(path.size() - 1);
//        }
//
//
//        //not add
//        helper(res, path, array, index + 1, max);
//
//    }

    /*
        Better method? We can find the last index of the longest subsequence and look back to find the final sequence
        e.g. assume the last index is i
        if there is an index j that array[j] < array[i] and dp[j] = dp[i] - 1, then we can say, j is a valid index for the final results.

     */
    public int[] longestAscendingSubsequenceII(int[] array) {
        if (array.length == 0) {
            return new int[0];
        }
        int[] longestSubsequence = new int[array.length];
        longestSubsequence[0] = 1;
        int longest = 1;
        int lastIndex = 0;
        for (int i = 1; i < longestSubsequence.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    max = Math.max(max, longestSubsequence[j] + 1);
                }
            }
            longestSubsequence[i] = max;
            if (max > longest) {
                lastIndex = i;
                longest = max;
            }
        }

        //look back to find the final result
        int[] res = new int[longest];
        int index = longest - 1;
        for (; index >= 0; index --) {
            res[index] = array[lastIndex];
            for (int i = lastIndex - 1; i >= 0; i--) {
                if (array[i] < array[lastIndex] && longestSubsequence[i] == longestSubsequence[lastIndex] - 1) {
                    lastIndex = i;
                    break;
                }
            }
        }
        return res;

        //TC: n ^ 2
    }

}
