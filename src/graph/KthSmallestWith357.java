package graph;


import java.util.*;

public class KthSmallestWith357 {

    /**
     * Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, x > 0 and y > 0 and z > 0, x, y, z are all integers.
     * Assumption: k >= 1
     * <p>
     * the smallest is 3 * 5 * 7 = 105
     * the 2nd smallest is 3 ^ 2 * 5 * 7 = 315
     * the 3rd smallest is 3 * 5 ^ 2 * 7 = 525
     * the 5th smallest is 3 ^ 3 * 5 * 7 = 945
     *
     * @param k
     * @return
     */
    public long kthSmallestWith357(int k) {
        /*
            we should maintain a minHeap to get the current smallest number
            when we find current smallest <x, y, z>, there are 3 neighbors to generate

         */
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            public int compare(List<Integer> list1, List<Integer> list2) {
                Double number1 = Math.pow(3, list1.get(0)) * Math.pow(5, list1.get(1)) * Math.pow(7, list1.get(2));
                Double number2 = Math.pow(3, list2.get(0)) * Math.pow(5, list2.get(1)) * Math.pow(7, list2.get(2));
                return number1.compareTo(number2);
            }
        });

        Set<List<Integer>> visited = new HashSet<>();
        List<Integer> start = Arrays.asList(1, 1, 1);
        minHeap.offer(start);
        visited.add(start);

        for (int i = 0; i < k - 1; i++) {
            List<Integer> curr = minHeap.poll();
            int x = curr.get(0);
            int y = curr.get(1);
            int z = curr.get(2);
            List<Integer> neighbor1 = Arrays.asList(x + 1, y, z);
            List<Integer> neighbor2 = Arrays.asList(x, y + 1, z);
            List<Integer> neighbor3 = Arrays.asList(x, y, z + 1);
            if (visited.add(neighbor1)) {
                minHeap.offer(neighbor1);
            }
            if (visited.add(neighbor2)) {
                minHeap.offer(neighbor2);
            }
            if (visited.add(neighbor3)) {
                minHeap.offer(neighbor3);
            }
        }
        List<Integer> res = minHeap.peek();
        return (long) (Math.pow(3, res.get(0)) * Math.pow(5, res.get(1)) * Math.pow(7, res.get(2)));
    }

    /**
     *  a better way to write the code above
     * @param k
     * @return
     */
    public long kth(int k) {
        // Write your solution here
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        minHeap.offer(3 * 5 * 7L);
        visited.add(3 * 5 * 7L);
        for (int i = 0; i < k - 1; i++) {
            Long pollNum = minHeap.poll();
            if (visited.add(3 * pollNum)) {
                minHeap.offer(3 * pollNum);
            }
            if (visited.add(5 * pollNum)) {
                minHeap.offer(5 * pollNum);
            }
            if (visited.add(7 * pollNum)) {
                minHeap.offer(7 * pollNum);
            }
        }
        return minHeap.peek();
    }
}
