package dynamic_programming;

public class FindNumberOfBSTsGenerated {

    /**
     * Find the number of different Binary Search Trees generated from 1-
     *
     * Assumption: if n <= 0, return 1
     * Examples: Input: 3, Return: 5
     * @param n
     * @return
     */
    public int numOfTrees(int n) {
        /*
            We can define dp[i] represents number of trees generated from 1 ~ i

            if we already know dp[i - 1]
            how do we get dp[i]
            Scenario 1: the number i is the root, there are only dp[i - 1] possible ways. since the tree generated from 1 ~ i - 1 must be on its left subtree
            Scenario 2: if the number i is not the root, for each possible root j from 1 ~ i - 1
            we can partition the binary tree into left and right, left is from [1, j - 1] and [j + 1, i]
            each option has dp[j - 1] * dp[i - j]

            e.g.
            dp[1] = 1
            dp[2] = 2
            d[3] = 2 + 1 + 2 = 5

         */
        if (n <= 0) {
            return 1;
        }
        int[] numberOfTrees = new int[n + 1];
        numberOfTrees[0] = 1;
        numberOfTrees[1] = 1;

        for (int i = 2; i <= n; i++) {
            numberOfTrees[i] += numberOfTrees[i - 1];
            for (int j = 1; j <= i - 1; j++) {
                numberOfTrees[i] += numberOfTrees[j - 1] * numberOfTrees[i - j];
            }
        }
        return numberOfTrees[n];
    }
}
