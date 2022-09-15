package graph.minimum_spanning_tree;

import java.util.*;

public class MinCostToConnectAllPoints {

    /**
     * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
     *
     * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
     *
     * Example 1:
     * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
     * Output: 20
     *
     * Example 2:
     * Input: points = [[3,12],[-2,5],[-4,1]]
     * Output: 18
     *
     * @param points
     * @return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
     */
    public int minCost(int[][] points) {
        /*
            Using Kruskal's algorithm

            Step1: define a edge class {
                                  int length;
                                  int point1;
                                  int point2;
                             }
            step2: sort based on edge length
            step3: use Kruskal's algorithm to find the minCost until all point are connected

             List<Edge> listOfEdges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                listOfEdges.add(new Edge(points, i, j));
            }
        }

        //Sort edges
        Collections.sort(listOfEdges, (Edge edge1, Edge edge2) -> {
            return edge1.length - edge2.length;
        });

        int minCost = 0;
        UnionFind uf = new UnionFind(points.length);
        for (Edge edge: listOfEdges) {
            if (uf.union(edge.point1, edge.point2)) {
                minCost += edge.length;
            }
            if (uf.allConnected()) {
                return minCost;
            }
        }
        return minCost;
         */


         /*
        We can use Prim's algorithm to solve it

        We can start from points[0] and generate all its neighbors into a minHeap. Then we expand the neighbor with the min edge and repeat this process until
        all points are connected

        For all the nodes we added in the minimum spanning tree, we need to maintain a set to indicate it has been added in the tree and when we generate a new
        edge in the minHeap, we don't need to generate the edge connected to the node that has already been added in the MST.

     */

        PriorityQueue<Edge> minHeap = new PriorityQueue<>((Edge edge1, Edge edge2)-> {
            return edge1.length - edge2.length;
        });
        Set<Integer> added = new HashSet<>();
        added.add(0);
        for (int i = 1; i < points.length; i++) {
            minHeap.offer(new Edge(points, 0, i));
        }

        int minCost = 0;
        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            if (added.add(edge.point2)) {
                minCost += edge.length;
                if (added.size() == points.length) {
                    break;
                }
                for (int i = 0; i < points.length; i++) {
                    if (!added.contains(i)) {
                        minHeap.offer(new Edge(points, edge.point2, i));
                    }
                }
            }
        }
        return minCost;
    }


    class UnionFind {
        int[] root;
        int[] rank;
        int count;

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            count = size;
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            count--;
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootY] > rank[rootX]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
            return true;
        }

        public boolean allConnected() {
            return count == 1;
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
    class Edge {
        int length;
        int point1;
        int point2;

        public Edge(int[][] points, int i, int j) {
            point1 = i;
            point2 = j;
            length = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
        }
    }


}
