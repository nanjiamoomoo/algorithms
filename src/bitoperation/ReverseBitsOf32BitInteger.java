package bitoperation;

public class ReverseBitsOf32BitInteger {

    /**
     * Reverse bits of a 32-bit integer.
     *
     * Note: In different programming languages, integers might be implemented differently in terms of number of bits, signed, unsigned, etc. However it should not affect your implementation on this problem. In java, the type of input is long, but you just need to work on the last 32-bit and consider it as an unsigned 32-bit integer.
     *
     * Example 1:
     *
     * Input: 1234 (0b'00000000000000000000010011010010)
     *
     * Output: 1260388352 (0b'01001011001000000000000000000000)
     * @param n
     * @return
     */
    public long reverseBits(long n) {
        for (int offset = 0; offset < 16; offset++) {
            long right = (n >> offset) & 1L;
            long left = (n >>(31 - offset)) & 1L;
            if (left != right) {
                n ^= (1L << offset);
                n ^= (1L <<(31-offset));
            }
        }
        return n;


    }
    /*
           Another: merge sort of reversing all the bits
        */
    public long reverseBitsII(long n) {
        n = ((n & 0xFFFF0000) >>> 16) | ((n & 0x0000FFFF) << 16);
        n = ((n & 0xFF00FF00) >>> 8) | ((n & 0x00FF00FF) << 8);
        n = ((n & 0xF0F0F0F0) >>> 4) | ((n & 0x0F0F0F0F) << 4);
        n = ((n & 0xCCCCCCCC) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1);
        return n;
        //TC:O(1)
        //SC: O(1)
    }
}
