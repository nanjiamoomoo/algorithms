package recursion;

public class LargestAndSmallest {

    /**
     * Use the least number of comparisons to get the largest and smallest number in the given integer array. Return the largest number and the smallest number.
     * Assumption:
     * The given array is not null and has length of at least 1
     * Examples:
     * {2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5, 1].
     * @param array
     * @return
     */
    public int[] largestAndSmallest(int[] array) {

        /*
            We can use binary comparison to get the largest and smallest method
            Define a method largestAndSmallest(int[] array, int left, int right) which returns the largest and smallest in the range[left, right]

            for each recursion level,
                we get the largest and smallest from the left half
                and get the largest and smallest from the right half

             and generate the new largest and smallest and return

            Comparison: 1.5n
         */
        return largestAndSmallest(array, 0, array.length - 1);
    }

    private int[] largestAndSmallest(int[] array, int left, int right) {
        if (left == right) {
            return new int[]{array[left], array[right]};
        }
        if (right - left == 1) {
            if (array[left] >= array[right]) {
                return new int[]{array[left], array[right]};
            }
            return new int[]{array[right], array[left]};
        }

        int mid = left + (right - left) / 2;
        int[] leftPair = largestAndSmallest(array, left, mid);
        int[] rightPair = largestAndSmallest(array, mid + 1, right);

        int largest = Math.max(leftPair[0], rightPair[0]);
        int smallest = Math.min(leftPair[1], rightPair[1]);
        return new int[]{largest, smallest};
    }

    /*
        Another method is to use two pointers to partition the array into the larger half and smaller half n/2
        while (i < j)
            if (array[i] < array[j]) {
                swap
            }
            i++;
            j--;

        Then find the max in the larger half n/2
        find the smallest in the smaller half n/ 2

     */
}
