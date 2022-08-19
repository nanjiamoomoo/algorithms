package graph.dfs;

import java.util.*;

public class PlaceToPutTheChair {

    /**
     * Given a gym with k pieces of equipment and some obstacles.  We bought a chair and wanted to put this chair into the gym such that  the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ‘O’ denotes a cell with an obstacle, 'C' denotes a cell without any equipment or obstacle. You can only move to neighboring cells (left, right, up, down) if the neighboring cell is not an obstacle. The cost of moving from one cell to its neighbor is 1. You can not put the chair on a cell with equipment or obstacle.
     *
     * Assumptions
     *
     * There is at least one equipment in the gym
     * The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
     * It is guaranteed that each 'C' cell is reachable from all 'E' cells.
     * If there does not exist such place to put the chair, just return {-1, -1}
     * Examples
     *
     * { { 'E', 'O', 'C' },
     *
     *   {  'C', 'E',  'C' },
     *
     *   {  'C',  'C',  'C' } }
     *
     * we should put the chair at (1, 0), so that the sum of cost from the chair to the two equipment is 1 + 1 = 2, which is minimal.
     * @param gym
     * @return The coordinate of the chair
     */
    public List<Integer> placeToPurChair(char[][] gym) {

        /*
            for each open position we can find its sum of distance to all 'E'
            we can store the distances sum in a 2D array

            for each open position 'C' we can use BFS algorithm to traverse the entire Gym
                since the weight of the edge is always one, we can use a queue to find the shortest step to each node
                as long as we find an 'E' we add the steps in the final result
         */
        int[][] distances = new int[gym.length][gym[0].length];

        for (int i = 0; i < gym.length; i++) {
            for (int j = 0; j < gym[0].length; j++) {
                if (gym[i][j] == 'C') {
                    int distance = findDistance(gym, i, j);
                    distances[i][j] = distance;
                }
            }
        }

        int[] res = new int[] {-1, -1};
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < gym.length; i++) {
            for (int j = 0; j < gym[0].length; j++) {
                if (distances[i][j] != 0 && distances[i][j] < globalMin) {
                    res[0] = i;
                    res[1] = j;
                    globalMin = distances[i][j];
                }
            }
        }

        return Arrays.asList(res[0], res[1]);

    }

    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int findDistance(char[][] gym, int i, int j) {
        /*
            data structure for the BFS:
                Queue
                boolean[][] visited to mark visited places to avoid cycle

         */
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[gym.length][gym[0].length];
        int[] begin = new int[]{i, j};
        queue.offer(begin);
        visited[i][j] = true;
        int result = 0;
        int level = 0; //keeps the level of the traversal, it also indicates the current steps
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                for (int[] dir : DIRS) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (valid(gym, visited, x, y)) {
                        visited[x][y] = true;
                        queue.offer(new int[]{x, y});
                        if (gym[x][y] == 'E') {
                            result += level + 1;
                        }

                    }
                }
            }
            level++;
        }
        return result;
    }

    private boolean valid(char[][] gym, boolean[][] visited, int x, int y) {
        return x >= 0 && x < gym.length && y >= 0 && y < gym[0].length && gym[x][y] != 'O' && !visited[x][y];
    }

    public static void main(String[] args) {
        char[][] gym = {{'C', 'C', 'E', 'O', 'C'}, {'C', 'C', 'O', 'C', 'E'}, {'C', 'C', 'E', 'E', 'C'}, {'C', 'O', 'C', 'E', 'E'}, {'C', 'C', 'O', 'C', 'C'}};
        PlaceToPutTheChair placeToPutTheChair = new PlaceToPutTheChair();
        System.out.println(placeToPutTheChair.placeToPurChair(gym));
    }


    /**
     * Another solution
     *   for each 'E' position, we use BFS to traverse the entire gym[][]
     *      we find the shortest path to each position
     *   we add up these distances on each position to indicate sum of the shortest path to each 'E'
     */
}

