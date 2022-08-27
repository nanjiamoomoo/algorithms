package graph.dijkstra_algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestNumberInSortedMatrix {

    /**
     * Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest
     * Assumption: the matrix is not null, N > 0 and M > 0
     *              K > 0 and K <= N * M
     * Examples:
     * { {1, 3, 5, 7},
     *   {2, 4, 8, 9},
     *   {3, 5, 11, 15},
     *   {6, 8, 13, 18} }
     * the 5th smallest number is 4
     * the 8th smallest number is 6
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        /*
            We can use dijkstra's algorithm to solve the problem
            Data structure: minHeap
            Since all rows and cols are sorted, for each element expanded, we can generate the element on its right and element on its bottom. Since the right and bottom elements are
            the potential elements to be the next bigger element.

            we also need to track if a position has been visited before.
         */
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
            public int compare(Cell c1, Cell c2) {
                Integer i1 = matrix[c1.x][c1.y];
                Integer i2 = matrix[c2.x][c2.y];
                return i1.compareTo(i2);
            }
        });

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        minHeap.offer(new Cell(0, 0));
        visited[0][0] = true;
        for (int i = 0; i < k - 1; i++) {
            Cell cell = minHeap.poll();
            int x = cell.x;
            int y = cell.y;
            if (valid(x + 1, y, matrix, visited)) {
                minHeap.offer(new Cell(x + 1, y));
                visited[x + 1][y] = true;
            }
            if (valid(x, y + 1, matrix, visited)) {
                minHeap.offer(new Cell(x, y + 1));
                visited[x][y + 1] = true;
            }
        }
        Cell res = minHeap.peek();
        return matrix[res.x][res.y];
    }

    private boolean valid(int x, int y, int[][] matrix, boolean[][] visited){
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && !visited[x][y];
    }

    class Cell {
        int x;
        int y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
