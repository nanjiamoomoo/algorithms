package graph.dijkstra_algorithm;

import java.util.*;

public class NetWorkDelayTime {

    /**
     * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
     *
     * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
     *
     * Assumption:
     * 1 <= k <= n <= 100
     * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
     *
     * Example 1:
     * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
     * Output: 2
     *
     * Example 2:
     * Input: times = [[1,2,1]], n = 2, k = 1
     * Output: 1
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int minimumTime(int[][] times, int n, int k) {
        /*
            This is weighted directed graph
            The question converts to calculate the minimum time to reach to each node and in the meanwhile we will maintain a globalMax
            When every node is visited, we will return the globalMax

            Data structure:
                1. visited Set
                2. PriorityQueue to find the next node with the minimum delay

            Step1: build adjacency  Map<Integer, Set<List<Integer>>> List<Integer>, the first element is the node, the second is the delay
            Step2: use Dijkstra's algorithm to traverse the entire graph
            Expansion and Generation rule: expand the next node with minimum and generate all the neighbors

         */

        //step1:
        Map<Integer, Set<List<Integer>>> map = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            map.put(i, new HashSet<>());
        }
        for (int[] time : times) {
            List<Integer> list = new ArrayList<>();
            list.add(time[1]);
            list.add(time[2]);
            map.get(time[0]).add(list);
        }

        int delay = -1;
        Set<Integer> visited = new HashSet<>();
        //step2:
        //note: List<Integer>, the first element is the node, the second is the cost to reach to them
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>((l1, l2) -> {
            return l1.get(1) - l2.get(1);
        });

        minHeap.offer(Arrays.asList(k, 0));
        visited.add(k);

        while (!minHeap.isEmpty()) {
            List<Integer> curr = minHeap.poll();
            delay = Math.max(delay, curr.get(1));
            visited.add(curr.get(0));
            if (visited.size() == n) {
                return delay;
            }
            Set<List<Integer>> neighbors = map.get(curr.get(0));
            for (List<Integer> neighbor : neighbors) {
                if (!visited.contains(neighbor.get(0))) {
                    minHeap.offer(Arrays.asList(neighbor.get(0), neighbor.get(1) + curr.get(1)));
                }
            }
        }
        return -1;
    }
}
