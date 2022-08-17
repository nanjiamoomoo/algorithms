package graph;

import java.util.*;

public class KthClosestPointToOrigin {

    /**
     * Given three arrays sorted in ascending order. Pull one number from each array to form a coordinate <x,y,z> in a 3D space. Find the coordinates of the points that is k-th closest to <0,0,0>.
     * We are using euclidean distance here.
     * <p>
     * Assumptions
     * The three given arrays are not null or empty, containing only non-negative numbers
     * K >= 1 and K <= a.length * b.length * c.length
     * <p>
     * Examples
     * <p>
     * A = {1, 3, 5}, B = {2, 4}, C = {3, 6}
     * <p>
     * The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)
     * <p>
     * The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)
     *
     * @param a
     * @param b
     * @param c
     * @return a size 3 integer list, the first element should be from the first array, the second element should be from the second array and the third should be from the third array
     */
    public List<Integer> kthClosestPointToOrigin(int[] a, int[] b, int[] c, int k) {

        /*
            since a, b, c are in ascending order
            so the first closest element to <0, 0, 0> will be at <a[0], b[0], c[0]>
            let's assume x represents the index of element in a[]
                         y represents the index of element in b[]
                         z represents the index of element in c[]
            if <a[x], b[y], c[z]> is the currently the closest element to origin,
            the next closest can only be one of the following three <a[x + 1], b[y], c[z]>, <a[x], b[y + 1], c[z]>, <a[x], b[y], c[z + 1]>

            There might be also duplications, so we need to HashSet to deduplication

            we use a minHeap to find the next closest element

            TC: 4klog(2k)

         */

        /**
         * minHeap includes element that is composed by list of 3 indices from 3 arrays respectively
         */
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            public int compare(List<Integer> list1, List<Integer> list2) {
                Long distance1 = (long) a[list1.get(0)] * a[list1.get(0)] + b[list1.get(1)] * b[list1.get(1)] + c[list1.get(2)] * c[list1.get(2)];
                Long distance2 = (long) a[list2.get(0)] * a[list2.get(0)] + b[list2.get(1)] * b[list2.get(1)] + c[list2.get(2)] * c[list2.get(2)] ;
                return distance1.compareTo(distance2);
            }
        });

        Set<List<Integer>> set = new HashSet<>();
        minHeap.offer(Arrays.asList(0, 0, 0));
        set.add(Arrays.asList(0, 0, 0));
        //we poll() k - 1 times, the next top element in the minHeap if the kth closest element
        for (int i = 0; i < k - 1; i++) {
            List<Integer> indices = minHeap.poll();
            System.out.println(indices);
            int x = indices.get(0);
            int y = indices.get(1);
            int z = indices.get(2);
            //for each element we have 3 neighbors to offer
            if (x < a.length - 1 && set.add(Arrays.asList(x + 1, y, z))) {
                minHeap.offer(Arrays.asList(x + 1, y, z));
            }
            if (y < b.length - 1 && set.add(Arrays.asList(x, y + 1, z))) {
                minHeap.offer(Arrays.asList(x, y + 1, z));
            }
            if (z < c.length - 1 && set.add(Arrays.asList(x, y, z + 1))) {
                minHeap.offer(Arrays.asList(x, y, z + 1));
            }
        }
        List<Integer> result = minHeap.peek();
        return Arrays.asList(a[result.get(0)], b[result.get(1)], c[result.get(2)]);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = {2, 4};
        int[] c = {1,2};
        int k = 10;
        KthClosestPointToOrigin kthClosestPointToOrigin = new KthClosestPointToOrigin();
        System.out.println(kthClosestPointToOrigin.kthClosestPointToOrigin(a, b, c, k));

    }
}
