package graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class ArrayHopper {

    /**
     * Given an array A of non-negative integers, you are initially positioned at an arbitrary index of the array. A[i] means the maximum jump distance from that position (you can either jump left or jump right). Determine the minimum jumps you need to reach the right end of the array. Return -1 if you can not reach the right end of the array.
     * <p>
     * Assumptions
     * <p>
     * The given array is not null and has length of at least 1.
     * Examples
     * <p>
     * {1, 3, 1, 2, 2}, if the initial position is 2, the minimum jumps needed is 2 (jump to index 1 then to the right end of array)
     * <p>
     * {3, 3, 1, 0, 0}, if the initial position is 2, the minimum jumps needed is 2 (jump to index 1 then to the right end of array)
     * <p>
     * {4, 0, 1, 0, 0}, if the initial position is 2, you are not able to reach the right end of array, return -1 in this case.
     *
     * @param array
     * @param index
     * @return
     */
    public int minJumpII(int[] array, int index) {
         /*
             method 1: BFS
             data structure: queue
                             boolean[] visited to record if a position has been visited
             in the beginning we offer the index into the queue
             then we generate its neighbors(all the positions that can be reached)

             termination condition: if we reached array.length - 1, or the queue is empty

          */
        if (index == array.length - 1) {
            return 0;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[array.length];
        queue.offer(index);
        visited[index] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- != 0) {
                Integer currPos = queue.poll();
                int leftBoundary = currPos - array[currPos];
                int rightBoundary = currPos + array[currPos];
                for (int i = rightBoundary; i >= leftBoundary; i--) {
                    if (validIndex(visited, i)) {
                        visited[i] = true;
                        if (i == array.length - 1) {
                            return step + 1;
                        }
                        queue.offer(i);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private boolean validIndex(boolean[] visited, int index) {
        return index >= 0 && index < visited.length && !visited[index];
    }
}
