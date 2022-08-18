package graph.topologicalsort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
               This is topological sort problem
               Data Structure:
                    Queue: contains all the node with inDegree of zero
                    AdjacencyList: List<List<Integer>> to include all courses and its neighbors
                    inDegree List: records the inDegree of each courses

               Algorithm: BFS algorithm
                    Expand a node
                    Generate its neighbors with inDegree zero
                    Termination: until Queue is empty or all nodes have been traversed
         */

         List<List<Integer>> adjacencyList = new ArrayList<>();
         int[] inDegree = new int[courses];
         for (int i = 0; i < courses; i++) {
             adjacencyList.add(new ArrayList<>());
         }

         for (int[] prerequisite: prerequisites) {
             adjacencyList.get(prerequisite[1]).add(prerequisite[0]);
             inDegree[prerequisite[0]]++;
         }

         Queue<Integer> queue = new ArrayDeque<>();
         for (int i = 0 ; i < courses; i++) {
             if(inDegree[i] == 0) {
                 queue.offer(i);
             }
         }
         int[] res = new int[courses];
         int expandedNum = 0;
         while (!queue.isEmpty()) {
             Integer currCourse = queue.poll();
             res[expandedNum++] = currCourse;
             List<Integer> neighbors = adjacencyList.get(currCourse);
             //we need to reduce each neighbor's inDegree by 1 and only generate node with inDegree of zero after reducing
             for (Integer neighbor : neighbors) {
                 //because of the inDegree control, each node can only be generated once
                 if (--inDegree[neighbor] == 0) {
                     queue.offer(neighbor);
                 }
             }
         }
         if (expandedNum == courses) {
             return res;
         }
         return new int[0];
    }
    //TC: O(V + E)
    //SC: O(V + E)
}
