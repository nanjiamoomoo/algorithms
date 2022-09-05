package stringandarray;


import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Given a string, find the longest substring without any repeating characters and return the length of it. The input string is guaranteed to be not null.
     * Example:
     * "bcdfbd" -> "bcdf"
     *
     * @param input
     * @return
     */
    public int longestSubstringWithoutRepeatingCharacters(String input) {


        //data structure: use a queue + a hashset
        //queue: we will make sure all the characters in the queue are unique
        //hashset is used for to check duplication
        if (input == null || input.length() == 0) {
            return 0;
        }

        Queue<Character> queue = new ArrayDeque<>();
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int index = 0;
        while (index < input.length()) {
            char ch = input.charAt(index);
            //if current character is repeating
            if (!set.add(ch)) {
                maxLength = Math.max(maxLength, queue.size());
                while (queue.peek() != ch) {
                    set.remove(queue.poll());
                }
                queue.poll();
            }
            queue.offer(ch);
            index++;
        }
        //after we reach the end of the string, we need to update the maxLength one more time
        maxLength = Math.max(maxLength, queue.size());
        return maxLength;
    }


    /**
     * Given an array of integers that contains only 0s and 1s and a positive integer k, you can flip at most k 0s to 1s, return the longest subarray that contains only integer 1 after flipping.
     * <p>
     * Assumptions:
     * 1. length of the given array is between[1, 20000]
     * 2. The given array only contains 1s and 0s.
     * 3. 0 <= k <= length of given array.
     * <p>
     * Examples:
     * 1. input: array = [1, 1, 0, 0, 1, 1, 1, 0, 0, 0], k = 2, output: 7
     * Explanation: flip 0s at index 2 and 3, then the array becomes [1,1,1,1,1,1,1,0,0,0],
     * so that the length of the longest subarray that contains only integer 1 is 7.
     * <p>
     * 2. input: array =[1, 1, 0, 0, 1, 1, 1, 0, 0, 0], k = 0, output: 3
     * Explanation: k is 0, so you cannot flip any 0 to 1, then the length of the longest subarray that contains only integer 1 is 3.
     *
     * @param nums input array
     * @param k    the maximum flipping times
     * @return
     */
    public int longestSubarrayContainsOnlyOnes(int[] nums, int k) {

        /*
           maintain a sliding window: the sliding window contains only 1s after flipping
           two pointers:
           slow: the left border of the sliding window
           fast: the current index under processing, fast - 1 is the right border of the sliding window

           we need another variable count to record the number of flipping 0s in the sliding window
           if character is 1, fast++
           if character is 0, but count < k, fast++, count++;
           if character is 0, but the count == k, then we have to move the slow pointer until it reaches the first index on the right side of the first flipping zeros

         */

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = 0;
        int fast = 0;
        int flippingZerosCount = 0;
        int maxLength = 0;
        while (fast < nums.length) {
            if (nums[fast] == 1) {
                fast++;
            } else if (nums[fast] == 0 && flippingZerosCount < k) {
                fast++;
                flippingZerosCount++;
            } else {
                maxLength = Math.max(maxLength, fast - slow);
                while (nums[slow] != 0) {
                    slow++;
                }
                slow++;
                flippingZerosCount--;
            }
        }
        //we need update maxLength once we reach to the end of the array
        maxLength = Math.max(maxLength, fast - slow);
        return maxLength;
    }

}
