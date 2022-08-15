package DynamicProgramming;

public class FibonacciNumber {

    public long fibonacci(int k) {
        //corner case
        if (k <= 0) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        //dp[i] represents the ith number in the Fibonacci Sequence
        long[] dp = new long[k + 1];
        //induction rule:
        // dp[i] = dp[i - 1] + dp[i - 2]
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= k; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[k];
    }

    public long fibonacciI(int k) {
        //corner case
        if (k <= 0) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        long a = 0;
        long b = 1;
        long result = 0;
        for (int i = 2; i <= k; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

}
