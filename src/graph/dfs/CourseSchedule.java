package graph.dfs;

import java.util.*;

public class CourseSchedule {

    /**
     * There are a total of n courses you have to take, labeled from 0 to n - 1.
     *
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
     *
     * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
     *
     * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
     *
     * For example:
     *
     * 2, [[1,0]]
     * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
     *
     * 4, [[1,0],[2,0],[3,1],[3,2]]
     * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
     * @param courses
     * @param prerequisites
     * @return Return one correct course order
     */
    public int[] courseSchedule(int courses, int[][] prerequisites) {

        /*
             We can use DFS to solve the problem.
             Step1: Create adjacencyList (directed graph)
             Step2: Then starting from each node and check if there is a cycle for the
             how?
             for each node is has 3 states:
                1. has never been visited
                2. has been visited and confirmed no cycle
                3. has been visited in the current path, then we can confirm that it has a cycle

             how do we achieve 2 and 3 in one path in the DFS algorithm. We can use the backtracking property
             we use a int[] visited array to mark the state of a node
             if visited[i] = 0, means it has never been visited
             if visited[i] = 1, means it has been visited in the current path
             if visited[i] = 2, mean we visited this node before and confirmed that the path down from i has no cycle.

             when we search down, we mark visited[i] = 1
             when we back track, we mark it as = 2

             How do we make sure we print the courses in sequence
             We can add the node when we return back.
             Use ArrayList<>() or LinkedList<>().


         */

         List<List<Integer>> adjacencyList = new ArrayList<>();
         for (int i = 0; i < courses; i++) {
             adjacencyList.add(new ArrayList<>());
         }

         for (int[] prerequisite: prerequisites) {
             adjacencyList.get(prerequisite[1]).add(prerequisite[0]);
         }

         List<Integer> res = new LinkedList<>();
         int[] visited = new int[adjacencyList.size()];
         for (int i = 0; i < courses; i++) {
             if (hasCycle(adjacencyList, i, visited, res)) {
                 return new int[0];
             }
         }
         int[] result = new int[courses];
         for (int i = 0; i < res.size(); i++) {
             result[i] = res.get(i);
             //result[courses - 1 - i] = res.get(i) if arraylist is used.
         }
         return result;
    }

    private boolean hasCycle(List<List<Integer>> adjacencyList, int node, int[] visited, List<Integer> list) {
        if (visited[node] == 2) {
            return false;
        }
        if (visited[node] == 1) {
            return true;
        }
        visited[node] = 1;
        for (int neighbor: adjacencyList.get(node)) {
            if (hasCycle(adjacencyList, neighbor, visited, list)) {
                return true;
            }
        }
        visited[node] = 2;
        list.add(0, node);
        //list.add(node); if use arrayList and the result needs to be reversed.
        return false;
    }
    //TC: O(V + E)
    //SC: O(V + E)
}
