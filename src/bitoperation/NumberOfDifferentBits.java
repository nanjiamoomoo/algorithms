package bitoperation;

public class NumberOfDifferentBits {

    /**
     * Determine the number of bits that are different for two given integers.
     *
     * Examples
     *
     * 5(“0101”) and 8(“1000”) has 3 different bits
     * @param one
     * @param two
     * @return
     */
    public int differentBits(int one, int two) {
        /*
            to get the kth's digit we can do x >> 1
            then we compare each digit of the two numbers
         */
//        int count = 0;
//        for (int i = 0; i < 32; i++) {
//            count += ((one >> i) & 1) ^ ((two >> i) & 1);
//        }
//        return count;

        /*
            Solution2: count the number of 1 bit in the xor
         */
        int xor = one ^ two;
        int count = 0;
        while (xor != 0) {
            xor &= (xor - 1);
            count++;
        }
        return count;
    }
}
