package graph.dijkstra_algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TwoSumSmaller {

    /**
     * Determine the number of pairs of elements in a given array that sum to a value smaller than the given target number.
     *
     * Assumptions: The given array is not null and has length of at least 2
     *
     * Examples: A = {1, 2, 2, 4, 7}, target = 7, number of pairs is 6({1,2}, {1, 2}, {1, 4}, {2, 2}, {2, 4}, {2, 4})
     *
     * @param array
     * @param target
     * @return
     */
    public int smallerPairs(int[] array, int target) {
        /*
            Brutal force:
            We can find the all the sum from small to large until we find one >= the target
            Step 1 : sort array
            Step2:
            int order to keep the sum as small as possible
            j = 1 ~ array.length - 1
                for i = 0, j - 1
                    if sum < target
                        count++
                     else break;
         */
//        Arrays.sort(array);
//        int count = 0;
//        for (int j = 1; j < array.length; j++) {
//            for (int i = 0; i < j; i++) {
//                if (array[i] + array[j] >= target) {
//                    break;
//                }
//                count++;
//            }
//        }
//        return count;
//        //TC: n ^ 2


    /*
        Another method: PriorityQueue
        the problem converts to find top K sum of the two elements

     */
        Arrays.sort(array);
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return array[o1.i] + array[o1.j] - array[o2.i] - array[o2.j];
            }
        });
        minHeap.offer(new Pair(0, 1));
        boolean[][] visited = new boolean[array.length][array.length];
        visited[0][1] = true;
        int count = 0;
        while (!minHeap.isEmpty()) {
            Pair curr = minHeap.poll();
            if (array[curr.i] + array[curr.j] >= target) {
                return count;
            }
            if (curr.i + 1 < curr.j && !visited[curr.i + 1][curr.j]) {
                visited[curr.i + 1][curr.j] = true;
                minHeap.offer(new Pair(curr.i + 1, curr.j));
            }
            if (curr.j + 1 < array.length && !visited[curr.i][curr.j + 1]) {
                visited[curr.i][curr.j + 1] = true;
                minHeap.offer(new Pair(curr.i, curr.j + 1));
            }
        }
        return count;
    }
    class Pair {
        int i;
        int j;
        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }


}
