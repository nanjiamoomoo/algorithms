package dynamic_programming;

/**
 * Array Hopper(Jump) related problems
 */
public class ArrayHopper {

    /**
     * Given an array A of non-negative integers, you are initially positioned at index 0 of the array.
     * A[i] means the maximum jump distance from that position (you can only jump towards the end of the array).
     * Determine if you are able to reach the last index
     *
     * Assumptions:
     * 1. The given array is not null and has length of at least 1
     *
     * Examples:
     * 1. {1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)
     * 2. {2, 1, 1, 0, 2}, we are not able to reach the end of array
     *
     * @param array
     * @return
     */
    public boolean canReachLastIndex(int[] array) {


//      /*  //ifReachable[i] represents if the i-th index position can be reached from 0-th index position
//        boolean ifReachable[] = new boolean[array.length];
//
//        ifReachable[0] = true;
//
//        *//*
//            ifReachable[i] =
//                for each previous position j from 0, 1, ... i - 1
//                    if (ifReachable[j] && array[j] >= i - j) means current position can be reached through position j
//                       ifReachable[i] = true;
//                       break;
//                false;
//
//         *//*
//
//        for (int i = 1; i < array.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (ifReachable[j] && array[j] >= i - j) {
//                    ifReachable[i]= true;
//                    break;
//                }
//            }
//        }
//
//        return ifReachable[array.length - 1];*/

        //ifReachable[i] represents if we can reach the array.length - 1 index position from ith index position
        boolean[] ifReachable = new boolean[array.length];
        ifReachable[array.length - 1] = true;
        /*
            induction rule: check if we can jump to the last index from another position on the right side
            ifReachable[i] =
                for each position j from i + 1 to array.length - 1
                    if (ifReachable[j] && array[i] >= j - i
                        ifReachable[i] = true
                        break;
                ifReachable[i] = false;
         */

        for (int i = array.length - 2; i >= 0; i--) {
            for (int j = i + 1; j <= Math.max(array.length - 1, i + array[i]); j++) {
                if (ifReachable[j]) {
                    ifReachable[i] = true;
                    break;
                }
            }
        }
        return ifReachable[0];
    }

    /**
     * Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from index i (you can only jump towards the end of the array).
     * Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of the array, return -1.
     * Example:
     * 1. {3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)
     * 2. {2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.
     *
     * @param array
     * @return
     */
    public int minJump(int[] array) {

        /*
            define minJumpsToReachEnd[i] represents minimum jumps needed to jump from ith position to the end of the array
         */

        int[] minJumpsToReachEnd = new int[array.length];
        minJumpsToReachEnd[array.length - 1] = 0;

        /*
            Induction rule:
                minJumpsToReachEnd[i] =
                    int minimumJumps = -1;
                    for each reachable position j [i + 1, Math.min(array[i] + i, array.length - 1]
                        if (minJumpsToReachEnd[j] != -1)
                            minimumJumps = minimumJumps == -1? minJumpsToReachEnd[j] + 1 : Math.min(minimumJumps, minJumpsToReachEnd[j] + 1;

                    minJumpsToReachEnd[i] = minimumJumps;
         */
        for (int i = array.length - 2; i >= 0; i--) {
            int minimumJumps = -1;
            for (int j = i + 1; j <= Math.min(array.length - 1, array[i] + i); j++) {
                if (minJumpsToReachEnd[j] != -1) {
                    minimumJumps = minimumJumps == -1 ? minJumpsToReachEnd[j] + 1 : Math.min(minimumJumps, minJumpsToReachEnd[j] + 1);
                }
            }
            minJumpsToReachEnd[i] = minimumJumps;
        }

        return minJumpsToReachEnd[0];
     }

}
