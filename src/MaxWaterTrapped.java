public class MaxWaterTrapped {

    /**
     * Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest amount of water that can be trapped in the histogram.
     * Assumption: The given array is not null
     * @param array
     * @return
     */
    public int maxTrappedI(int[] array) {
        /*
              for each bar we need to find the highest bar on its left side and its right side, so we can calculate water to be trapped on top of it.
              method 1: we can use dp.
              left[i] represents the largest height on its left including itself
              right[i] represents the largest height on its right including itself

              for each i, I can calculate the water on top of it by using math.min(left[i], right[i]) - array[i]
              then we calculate sum for each i

              method2:
              we can use two pointers, one starts from left we call it i and the other one is from right, we call it j
              We also maintain two variables, leftMax and rightMax
              leftMax represents the max high on the left side of including i
              rightMax represents the max on the right side of j including j
              for every loop, we compare the leftMax and rightMax.
                if leftMax is smaller, then we can update the waterTrapped at i and move i to the right. Why? since leftMax is left boundary and right boundary is bigger than left boundary, so right will not be the constraint factor
                if rightMax is smaller, then we can update the waterTrapped at j and move j to the left.


         */
        if (array.length <= 2) {
            return 0;
        }
//        int[] highestOnLeft = new int[array.length];
//        int[] highestOnRight = new int[array.length];
//        highestOnLeft[0] = array[0];
//        highestOnRight[array.length - 1] = array[array.length - 1];
//        for (int i = 1; i < array.length; i++) {
//            highestOnLeft[i] = Math.max(highestOnLeft[i - 1], array[i]);
//        }
//        for (int i = array.length - 2; i>=0; i--) {
//            highestOnRight[i] = Math.max(highestOnRight[i + 1], array[i]);
//        }
//        int waterTrapped = 0;
//        for (int i = 0; i < array.length; i++) {
//            waterTrapped += Math.min(highestOnLeft[i], highestOnRight[i]) - array[i];
//        }
//        return waterTrapped;

        int leftMax = array[0];
        int rightMax = array[array.length - 1];
        int left = 0;
        int right = array.length - 1;
        int waterTrapped = 0;
        while (left <= right) {
            //step1: update leftMax and rightMax
            leftMax = Math.max(leftMax, array[left]);
            rightMax = Math.max(rightMax, array[right]);
            if (leftMax <= rightMax) {
                //we can update the waterTrapped on top of index left
                waterTrapped += leftMax - array[left];
                left++;
            } else {
                waterTrapped += rightMax - array[right];
                right--;
            }
        }
        return waterTrapped;
    }
}
