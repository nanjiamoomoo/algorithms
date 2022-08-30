public class MissingNumber {

    /**
     * Given an integer array of size N - 1, containing all the numbers from 1 to N except one, find the missing number.
     * Assumption: The given array is not null, and N >= 1
     * Examples:
     * A = {2, 1, 4}, the missing number is 3
     * A = {1, 2, 3}, the missing number is 4
     * A = {}, the missing number is 1
     * @param array
     * @return
     */
    public int missingNumberI(int[] array) {
        /*
            we can use exclusive or property to solve the problem
            a ^ a = 0;
            a ^ b != 0 if a != b
            0 ^ a = a

            of we do xor operation to all the N-1 numbers in the array, and then
            do xor operations to the all number 1 ~ N numbers, the result will be the target
         */
        int eor = 0;
        for (int i = 0; i < array.length; i++) {
            eor ^= array[i];
        }
        for (int i = 1; i <= array.length + 1; i++) {
            eor ^= i;
        }
        return eor;
    }
}
