package dynamic_programming;

public class ClimbingStairs {

    /**
     * There are in total n steps to climb until you can reach the top. You can climb 1 or 2 steps a time. Determine the number of ways you can do that.
     * Examples: Input: n = 4, Return 5.
     * @param n
     * @return
     */
    public int stairs(int n) {
        /*
            dp[i] represents number of ways to climb i steps
            if we already know dp[i - 1], we can take another to climb i steps
            if we already know dp[i - 2], we can take another two steps to climb i steps
            dp[i] = dp[i - 1] + dp[i - 2]

            dp[1] = 1

            dp[2] = 2;
            dp[3] = 3;
            dp[4=] = 5
         */
        if (n == 1) {
            return 1;
        }
        int[] numOfWays = new int[n + 1];
        numOfWays[1] = 1;
        numOfWays[2] = 2;
        for (int i = 3; i <= n; i++) {
            numOfWays[i] = numOfWays[i - 1] + numOfWays[i - 2];
        }
        return numOfWays[n];
    }
}
