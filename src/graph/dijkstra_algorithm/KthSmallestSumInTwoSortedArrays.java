package graph.dijkstra_algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestSumInTwoSortedArrays {

    /**
     * Given two sorted arrays A and B, of sizes m and n respectively. Define s = a + b, where a is one element from A and b is one element from B. Find the Kth smallest s out of all possible s'.
     * Assumptions:
     * A is not null and A is not of zero length, so as B
     * K > 0 and K <= m * n
     * Examples:
     * A = {1, 3, 5}, B = {4, 8}
     * 1st smallest s is 1 + 4 = 5
     * 2nd smallest s is 3 + 4 = 7
     * 3rd, 4th smallest s are 9 (1 + 8, 4 + 5)
     * 5th smallest s is 3 + 8 = 11
     *  @param one
     * @param two
     * @param k
     * @return
     */
    public int kthSmallestSumInTwoArrays(int[] one, int[] two, int k) {

        /*
                A= 1 3 5
                B= 4 8

                we can imagine this as a 2D array, the row represents the index in one array, the col represents the index in the other array
                the value is the sum of elements in two arrays. We can use i represents the row and j represents the col
                   j
                   4  8  9
            i   1  5  9  10
            i   3  7  11 12
                4  8  12 13
                10 14 18  19
              the smallest sum is when i = 0, and j = 0, which is 1 + 4 = 5
              the next potential smallest sum is i = 1, j = 0 or i = 0, j = 1
              if the current smallest sum is at <i, j>
                the sum at <i + 1， j> < i, j + 1> could potentially be the next smallest.

         */
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
            public int compare(Cell c1, Cell c2) {
                Integer i1 = one[c1.i] + two[c1.j];
                Integer i2 = one[c2.i] + two[c2.j];
                return i1.compareTo(i2);
            }
        });

        boolean[][] visited = new boolean[one.length][two.length];

        minHeap.offer(new Cell(0, 0));
        visited[0][0] = true;
        for (int i = 0; i < k - 1; i++) {
            Cell cell = minHeap.poll();
            int x = cell.i;
            int y = cell.j;
            if (valid(x + 1, y, one.length, two.length, visited)) {
                minHeap.offer(new Cell(x + 1, y));
                visited[x + 1][y] = true;
            }
            if (valid(x, y + 1, one.length, two.length, visited)) {
                minHeap.offer(new Cell(x, y + 1));
                visited[x][y + 1] = true;
            }
        }
        Cell res = minHeap.peek();
        return one[res.i] + two[res.j];
    }
    private boolean valid(int x, int y, int m, int n, boolean[][] visited){
        return x >= 0 && x < m && y >= 0 && y < n && !visited[x][y];
    }

    class Cell {
        int i;
        int j;
        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
