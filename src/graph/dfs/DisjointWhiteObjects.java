package graph.dfs;

import java.util.*;

public class DisjointWhiteObjects {

    /**
     * In a 2D black image there are some disjoint white objects with arbitrary shapes, find the number of disjoint white objects in an efficient way.
     *
     * By disjoint, it means there is no white pixels that can connect the two objects, there are four directions to move to a neighbor pixel (left, right, up, down).
     *
     * Black is represented by 1’s and white is represented by 0’s.
     *
     * Assumption:
     * The given image is represented by a integer matrix and all the values in the matrix are 0 or 1
     * The given matrix is not null
     *
     * Examples:
     * the given image is
     *     0  0  0  1
     *     1  0  1  1
     *     1  1  0  0
     *     0  1  0  0
     * there are 3 disjoint white objects.
     *
     * @param matrix
     * @return
     */
    public int disjointWhiteObjectsI(int[][] matrix) {
        /*
             We can use DFS or BFS to solve the problem
             DFS solution is shown here, BFS solution is shown under disjointWhiteObjectsII

             we can define a method dfs() to traverse the matrix from each position with value 0.
             If a 0 position has been traversed, then we skip
             otherwise we traverse all the connected white boards until there is no way to go and count++

             TC: n ^ 2
             SC: n ^ 2
         */

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0 && !visited[i][j]) {
                    count++;
                    dfs(matrix, visited, i, j);
                }
            }
        }
        return count;
    }

    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private void dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j] || matrix[i][j] == 1) {
            return;
        }
        visited[i][j] = true;
        for (int[] dir : DIRS) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            dfs(matrix, visited, nextI, nextJ);
        }
    }

    public int disjointWhiteObjectsII(int[][] matrix) {
        /*
             This portion illustrate the BFS solution

             For each unvisited position and '0' positions, we do BFS to traverse all the 0s

                TC: O(n ^ 2)
                SC: O(n ^ 2)

         */
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0 && !visited[i][j]) {
                    count++;
                    bfs(matrix, visited, i, j);
                }
            }
        }
        return count;

    }

    private void bfs(int[][] matrix, boolean[][] visited, int i, int j) {
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(Arrays.asList(i, j));
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            List<Integer> coordinate = queue.poll();
            for (int[] dir : DIRS) {
                int nextX = coordinate.get(0) + dir[0];
                int nextY = coordinate.get(1) + dir[1];
                if (validCoordinate(matrix, visited, nextX, nextY)) {
                    queue.offer(Arrays.asList(nextX, nextY));
                }
            }
        }
    }

    private boolean validCoordinate(int[][] matrix, boolean[][] visited, int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length && !visited[i][j] && matrix[i][j] == 0;
    }
}
