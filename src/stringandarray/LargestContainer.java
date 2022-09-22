package stringandarray;

import java.util.ArrayList;
import java.util.List;

public class LargestContainer {

    /**
     * Given an array of non-negative integers, each of them representing the height of a board perpendicular to the horizontal line, the distance between any two adjacent boards is 1. Consider selecting two boards such that together with the horizontal line they form a container. Find the volume of the largest such container.
     * <p>
     * Assumptions
     * <p>
     * The given array is not null and has size of at least 2
     * Examples
     * <p>
     * { 2, 1, 3, 1, 2, 1 }, the largest container is formed by the two boards of height 2, the volume of the container is 2 * 4 = 8.
     *
     * @param array
     * @return
     */
    public int largestContainer(int[] array) {
        /*
            Naive solution: use two pointers, i < j find the water trapped in and update global max
               int largest = 0;
                for (int j = 1; j < array.length; j++) {
                    for (int i = 0; i < j; i++) {
                        largest = Math.max(largest, (j - i) * Math.min(array[i], array[j]));
                    }
                }
                return largest;
            TC: O(n^2)
         */

        /*
            Better solution:

            What board is qualified to be the left board
                 a board must be bigger than all the board on its left, then it is qualified to be the left board (Ascending)
            What board is qualified to be the right board
                 a board must be bigger than all the board on its right, then it is qualified to be the right board
         */
//        List<Integer> leftBoards = new ArrayList<>();
//        leftBoards.add(0);
//        int currMax = array[0];
//        for (int i = 1; i < array.length; i++) {
//            if (array[i] > currMax) {
//                leftBoards.add(i);
//                currMax = array[i];
//            }
//        }
//
//        List<Integer> rightBoards = new ArrayList<>();
//        rightBoards.add(array.length - 1);
//        currMax = array[array.length - 1];
//        for (int i = array.length - 2; i>= 0; i--) {
//            if (array[i] > currMax) {
//                rightBoards.add(i);
//                currMax = array[i];
//            }
//        }
//
//        int largest = 0;
//        for (int left : leftBoards) {
//            for (int right : rightBoards) {
//                largest = Math.max(largest, (right - left) * Math.min(array[left], array[right]));
//            }
//
//        }
//
//
//        return largest;

    /*
         Best solution:
         We take two pointers, one at the beginning and one at the end of the array constituting the length of the lines.
         Futher, we maintain a variable maxarea to store the maximum area obtained till now.
         At every step, we find out the area formed between them, update maxarea and move the pointer pointing to the shorter line towards the other end by one step.
         Then we can move the shorter one?
         why?
         Since if we move the longer one, the width of the container reduces, but we are not getting a bigger height, since the height is determined by the shorter boards.
         however if we move the shorter one, the widof the container reduces, but we may get a bigger height, which potentially update the maxarea

     */

        int left = 0;
        int right = array.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(array[left], array[right]));
            if (array[left] <= array[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
