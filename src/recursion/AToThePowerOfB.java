package recursion;

public class AToThePowerOfB {
    /**
     * Evaluate a to the power of b, assuming both a and b are integers and b is non-negative.
     * Examples
     * power(2, 0) = 1
     * power(2, 3) = 8
     * power(0, 10) = 0
     * power(-2, 5) = -32
     *
     * Corner Cases:
     * 0^0 = 1
     * @param a
     * @param b
     * @return
     */
    public long power(int a, int b) {
        /*
            We can use recursion to solve the problem
            the sub problem is power(a, b / 2)
            if b is odd
                curr value = a * power(a, b / 2) * power(a, b / 2)
            if b is even
                curr value = power(a, b / 2) * power(a, b / 2)

             To reduce the time complexity, we can store power(a, b / 2)

             corner case:
             b = 0, return 1
         */
        if (b == 0) {
            return 1;
        }
        long half = power(a, b / 2);
        if (b % 2 == 0) {
            return half * half;
        } else {
            return a * half * half;
        }
    }
}
