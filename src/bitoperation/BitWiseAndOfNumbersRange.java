package bitoperation;

public class BitWiseAndOfNumbersRange {

    /**
     * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
     *
     * For example, given the range [5, 7], you should return 4.
     * @param m
     * @param n
     * @return
     */
    public int rangeBitWiseAnd(int m, int n) {
        /*
            Simple solution:
            int res = m;
            for (int i = m + 1; i <= n; i++) {
              res &= i;
            }
            return res;
         */

        /*
            notice that after the AND operation on all the numbers, the remaining part of bit strings is the common prefix of all these bit strings.
            The final result asked by the problem consists of this common prefix of bit string as its left part, with the rest of bits as zeros.
            More specifically, the common prefix of all these bit string is also the common prefix between the starting and ending numbers of the specified range

         */
        int shift = 0;
        // find the common 1-bits
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;

        /*
            When we do AND bit operation between number and number-1, the rightmost bit of one in the original number would be turned off (from one to zero).
            By applying the Brian Kernighan's algorithm, we basically turn off the bits that lies on the right side of the common prefix, from the ending number nn. With the rest of bits reset, we then can easily obtain the desired result.
            public int rangeBitwiseAnd(int m, int n) {
                while (m < n) {
                  // turn off rightmost 1-bit
                  n = n & (n - 1);
                }
                return m & n;
              }
         */

    }

}
