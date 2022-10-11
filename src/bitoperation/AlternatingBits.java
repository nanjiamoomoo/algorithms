package bitoperation;

public class AlternatingBits {

    /**
     * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
     * Example 1:
     *
     * Input: 5
     * Output: True
     * Explanation:
     * The binary representation of 5 is: 101
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        if (n <= 2) {
            return true;
        }
        int previousBit = n & 1;
        n = n >> 1;
        while (n != 0) {
            int currBit = n & 1;
            if (currBit == previousBit) {
                return false;
            }
            previousBit = currBit;
            n = n >> 1;
        }
        return true;

        /*
           Another way:
            e.g. 101
            then 101 ^ (0101...010101010101 & 000.... 111) = 0;
            public boolean hasAlternatingBits(int n) {

                int mask = 0;
                int num = n;
                while (num != 0) {
                  num = num >> 1;
                  mask = mask << 1;
                  mask |= 1;
                }
                return ((0xAAAAAAAA & mask) ^ n) == 0 || ((0x55555555 & mask) ^ n) == 0;
             }


         */
    }
}
