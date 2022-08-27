package graph.dijkstra_algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxWaterTrapped {

    /**
     * Given a non-negative integer 2D array representing the heights of bars in a matrix. Suppose each bar has length and width of 1. Find the largest amount of water that can be trapped in the matrix. The water can flow into a neighboring bar if the neighboring bar's height is smaller than the water's height. Each bar has 4 neighboring bars to the left, right, up and down side.
     * Assumption: The given matrix is not null and has size of M * N, where M > 0 and N > 0, all the values are non-negative integers in the matrix.
     * Examples:
     * {
     *   { 2, 3, 4, 2 },
     *   { 3, 1, 2, 3 },
     *   { 4, 3, 5, 4 }
     * }
     * the amount of water can be trapped is 3,
     * at position (1, 1) there is 2 units of water trapped,
     * at position (1, 2) there is 1 unit of water trapped.
     * @param matrix
     * @return
     */
    public int maxWaterTrapped(int[][] matrix) {
        /*
            We can start from the periphery and find the lowest point on the most outside border, since the lowest points constraints how much water can be trapped.
            because we need to find the lowest height, we can use a minHeap. The minHeap keeps the border bars and height.
                from the lowest point, we can find its neighbors and find out how much water can be stored on top of these neighbor bars
                if the neighbor height >= the lowest point, then no water can be store on top of it. we offer it in minHeap
                else if neighbor height < the lowest point, then we can store water on top of it, we update the water height to the lowest point and offer it in the minHeap.

            based on the algorithm above
                we calculated the water trapped on top of each bar before generating it into the minHeap.
                so we need to use a visited map to keep record all the points have been visited and calculated

                we are certain that the first time we find a point(neighbor), the path much be the path with the lowest height on the way
         */
        int waterTrapped = 0;
        PriorityQueue<Point> minHeap = new PriorityQueue<>(new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.value < p2.value ? -1 : p1.value > p2.value ? 1 : 0;
            }
        });
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        init(minHeap, matrix, visited);
        //we don't need to calculate water trapped, since it won't trap any water on the most outer boarder
        while (!minHeap.isEmpty()) {
            Point curr = minHeap.poll();
            int x = curr.x;
            int y = curr.y;
            for (int[] dir : DIRS) {
                int neiX = x + dir[0];
                int neiY = y + dir[1];
                if (valid(neiX, neiY, matrix,visited)) {
                    if (matrix[neiX][neiY] >= curr.value) {
                        minHeap.offer(new Point(neiX, neiY, matrix[neiX][neiY]));
                    } else {
                        waterTrapped += curr.value - matrix[neiX][neiY];
                        Point point = new Point(neiX, neiY, curr.value);
                        minHeap.offer(point);
                    }
                    visited[neiX][neiY] = true;
                }
            }
        }
        return waterTrapped;

    }

    public final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private void init(PriorityQueue<Point> minHeap, int[][] matrix, boolean[][] visited) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < col; i++) {
            minHeap.offer(new Point(0, i, matrix[0][i]));
            minHeap.offer(new Point(row - 1, i, matrix[row - 1][i]));
            visited[0][i] = true;
            visited[row - 1][i] = true;
        }
        for (int i = 1; i < row - 1; i++) {
            minHeap.offer(new Point(i, 0, matrix[i][0]));
            minHeap.offer(new Point(i, col - 1, matrix[i][col - 1]));
            visited[i][0] = true;
            visited[i][col - 1] = true;
        }
    }

    private boolean valid(int x, int y, int[][] matrix, boolean[][] visited) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && !visited[x][y];
    }

    class Point {
        int x;
        int y;
        int value;
        Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
        public void  update(int value) {
            this.value = value;
        }
    }
    //TC:mn*log(m + n)
    //SC: m * n

    public static void main(String[] args) {
        int[][] matrix = {{5, 8, 7, 7}, {5, 2, 1, 5}, {7, 1, 7, 1}, {8, 9, 6, 9}, {9, 8, 9, 9}};
        MaxWaterTrapped maxWaterTrapped = new MaxWaterTrapped();
        System.out.println(maxWaterTrapped.maxWaterTrapped(matrix));
    }
}
