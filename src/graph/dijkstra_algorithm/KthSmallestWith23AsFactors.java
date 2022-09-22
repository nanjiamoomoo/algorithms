package graph.dijkstra_algorithm;

import java.util.*;

public class KthSmallestWith23AsFactors {

    /**
     * Find the Kth smallest number s such that s = 2 ^ x * 3 ^ y, x >= 0 and y >= 0, x and y are all integers.
     *
     * Assumptions
     * K >= 1
     *
     * Examples
     * the smallest is 1
     * the 2nd smallest is 2
     * the 3rd smallest is 3
     * the 5th smallest is 2 * 3 = 6
     * the 6th smallest is 2 ^ 3 * 3 ^ 0 = 8
     * @param k
     * @return
     */
    public int kthSmallest(int k) {

        /*
            we can use priority queue to help us find the next smallest element
            if the current smallest element corresponds to <x, y>, then the next potential smallest can happen in <x + 1, y> and <x, y + 1>

            The TC: is klogk
            SC: O(k)
         */
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return (int)(Math.pow(2, o1.get(0)) * Math.pow(3, o1.get(1)) - Math.pow(2, o2.get(0)) * Math.pow(3, o2.get(1)));
            }
        });

        minHeap.offer(Arrays.asList(0, 0));
        Set<List<Integer>> visited = new HashSet<>();
        visited.add(Arrays.asList(0, 0));
        while (--k != 0) {
            List<Integer> curr = minHeap.poll();
            List<Integer> nei1 = Arrays.asList(curr.get(0) + 1, curr.get(1));
            List<Integer> nei2 = Arrays.asList(curr.get(0), curr.get(1) + 1);
            if (visited.add(nei1)) {
                minHeap.offer(nei1);
            }
            if (visited.add(nei2)) {
                minHeap.offer(nei2);
            }
        }
        List<Integer> kthSmallestXY = minHeap.peek();
        return (int)(Math.pow(2, kthSmallestXY.get(0)) * Math.pow(3, kthSmallestXY.get(1)));
    }
}
