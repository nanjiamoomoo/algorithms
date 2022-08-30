package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KSmallestInUnsortedArray {

    /**
     * Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.
     * Assumption:
     * A is not null
     * K is >= 0 and smaller than or equal to size of A
     * Examples:
     * A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
     *
     * @param array
     * @param k
     * @return an array with size K containing the K smallest numbers in ascending order
     */
    public int[] kSmallest(int[] array, int k) {
        int[] res = new int[k];
        if (k == 0) {
            return res;
        }

        /*
            We can use a maxHeap of size k to maintain the current k smallest elements visited so far.

            In the beginning, we offer first k elements from the array into the heap
            After that, we will visit a new element, if it is bigger than or equals to the heap.top(), we ignore
            otherwise if pop() the top element from the heap and offer the new element in to maintain the property of the heap (only keep current k smallest elements)

            Eventually we will find our k smallest numbers in the maxHeap
            then we put them in the final result
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2.compareTo(i1);
            }
        });
        int i = 0;
        for (; i < k; i++) {
            maxHeap.offer(array[i]);
        } //O(klognk)

        for (; i < array.length; i++) {
            if (array[i] >= maxHeap.peek()) {
                continue;
            }
            maxHeap.poll();
            maxHeap.offer(array[i]);
        } //TC: O((n-k)*logk)

        //TC: O(k)
        for (int j = k - 1; j >= 0; j--) {
            res[j] = maxHeap.poll();
        }
        return res;
    }
}
