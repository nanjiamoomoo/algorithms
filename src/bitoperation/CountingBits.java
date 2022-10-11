package bitoperation;

public class CountingBits {

    /**
     * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
     *
     * Example:
     * For num = 5 you should return [0,1,1,2,1,2].
     *
     * Follow up:
     *
     * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
     * Space complexity should be O(n).
     *
     * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
     *
     * @param num
     * @return
     */
    public int[] countingBits(int num) {
        /*
            we can use dp to solve the problem
            countOne[i] represents the number of 1's in the binary representation of i
            countOne[0] = 0;

            count[i] = count[i&(i - 1)] + 1;

         */
        int[] countOne = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            countOne[i] = countOne[i & (i - 1)] + 1;
        }
        return countOne;

    }
}
