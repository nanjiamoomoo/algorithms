package binarysearch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianOfTwoArrays {

    /**
     * Given two sorted arrays of integers in ascending order, find the median value.
     * <p>
     * Assumptions
     * <p>
     * The two given array are not null and at least one of them is not empty
     * The two given array are guaranteed to be sorted
     * Examples
     * <p>
     * A = {1, 4, 6}, B = {2, 3}, median is 3
     * A = {1, 4}, B = {2, 3}, median is 2.5
     *
     * @param a
     * @param b
     * @return
     */
    public double median(int[] a, int[] b) {
        /*
            the median of two array is to find
                1. if the a.length + b.length is odd, then it is to find (a.length + b.length + 1) / 2 th smallest number
                1. if the total length is even then it is to find the (a.length + b.length) / 2 - 1 and (a.length + b.length) th smallest number


            for two sorted array a and array b, if we can use BS to find the kth smallest number

           a  x x x x x
           b  o o o o o
           we can compare a[left + k / 2] with b[left + k / 2]
                if a[left + k / 2] is smaller, then we can discard all the elements between [left, left and k / 2]
                then the searching space becomes to k - k / 2, and left = left + k / 2 + 1
                else if b .. is smaller ....

            if left + k / 2 is out of bound, then we can discard the first k / 2 elements in b
            this rules apply to b

            if there is no elements in a, then we count in b
            if there is no elements in b, then we count in b

            we do this until there is only one element left. then we compare a[left] and b[right]

            TC: log(m + n)
            SC: O(1)
         */

        int aLeft = 0;
        int bLeft = 0;
        int k = (a.length + b.length + 1) / 2;

        double median = 0;
        while (aLeft < a.length && bLeft < b.length) {
            if (k == 1) {
                break;
            }
            if (aLeft + k / 2 - 1 < a.length && bLeft + k / 2 - 1 < b.length) {
                if (a[aLeft + k / 2 - 1] <= b[bLeft + k / 2 - 1]) {
                    aLeft = aLeft + k / 2;
                } else {
                    bLeft = bLeft + k / 2;
                }
            } else if (aLeft + k / 2 - 1 >= a.length) {
                bLeft = bLeft + k / 2;
            } else {
                aLeft = aLeft + k / 2;
            }
            k = k - k / 2;
        }
        //complicated post-processing.
        if (k == 1) {
            if (bLeft >= b.length || aLeft < a.length && a[aLeft] <= b[bLeft]) {
                median = a[aLeft++];
            } else {
                median = b[bLeft++];
            }
        } else {
            if (aLeft == a.length) {
                median = b[bLeft + k - 1];
                bLeft += k;
            } else {
                median = a[aLeft + k - 1];
                aLeft += k;
            }
        }

        if ((a.length + b.length) % 2 == 0) {
            if (bLeft >= b.length || aLeft < a.length && a[aLeft] <= b[bLeft]) {
                median = (median + a[aLeft]) / 2;
            } else {
                median = (median + b[bLeft]) / 2;
            }
        }
        return median;
    }


    /**
     * Given two arrays of integers, find the median value.
     * <p>
     * Assumptions
     * <p>
     * The two given array are not null and at least one of them is not empty
     * The two given array are not guaranteed to be sorted
     * Examples
     * <p>
     * A = {4, 1, 6}, B = {2, 3}, median is 3
     * A = {1, 4}, B = {3, 2}, median is 2.5
     *
     * @param a
     * @param b
     * @return
     */
    public double medianII(int[] a, int[] b) {
        /*
             the problem converts to find the kth smallest element and the kth largest element
             k = (a.length + b.length + 1) / 2
             We can use two heap to solve the problem
             one max heap contains the top k smallest elements
             one min heap contains the top k largest elements.

             Then the median would be the mean of the top of the min heap and the max heap

             for the first k elements we offer into both heaps 2klogk
             for the next n - k elements.
             if it is bigger than the top of minHeap. poll() minHeap and offer into minHeap
             if it is smaller than the top of maxHeap, poll() maxHeap and offer into maxHeap

             TC: O(nlog(n/2)) = O(nlogn)

         */
        int k = (a.length + b.length + 1) / 2;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2.compareTo(i1);
            }
        });

        process(minHeap, maxHeap, a, k);
        process(minHeap, maxHeap, b, k);

        return (minHeap.peek() + maxHeap.peek() + 0.0) / 2;
    }

    private void process(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int[] array, int k) {
        for (int i = 0; i < array.length; i++) {
            if (minHeap.size() < k) {
                minHeap.offer(array[i]);
                maxHeap.offer(array[i]);
                continue;
            }
            if (array[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(array[i]);
            }
            if (array[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
    }

    /*
        Another method:
        maxHeap contains smaller half, minHeap contains bigger half
        the first element we offer to maxHeap
        of a new element
            if it is bigger than max of maxHeap
                we offer it into minHeap
                if minHeap.size() > maxHeap.size(), we move the smallest in the minHeap to maxHeap
            if it is smaller or equal to the max of maxHeap
                we offer it into maxHeap
                if maxHeap.size() > minHeap.size() + 1, we move the largest in the maxHeap to the minHeap

        in the end
        if the maxHeap.size() > minHeap.size(), we return maxHeap.peek()
        otherwise we return mean of maxHeap.peek() and minHeap.peek()

        TC: O(nlogn)
     */
    public double medianAnotherWay(int[] a, int[] b) {
        // Write your solution here
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2.compareTo(i1);
            }
        });

        process(minHeap, maxHeap, a);
        process(minHeap, maxHeap, b);

        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : (maxHeap.peek() + minHeap.peek() + 0.0) / 2;
    }

    public void process(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (maxHeap.isEmpty()) {
                maxHeap.offer(array[i]);
                continue;
            }
            if (array[i] > maxHeap.peek()) {
                minHeap.offer(array[i]);
                if (minHeap.size() > maxHeap.size()) {
                    maxHeap.offer(minHeap.poll());
                }
            } else {
                maxHeap.offer(array[i]);
                if (maxHeap.size() > minHeap.size() + 1) {
                    minHeap.offer(maxHeap.poll());
                }
            }
        }
    }
}
