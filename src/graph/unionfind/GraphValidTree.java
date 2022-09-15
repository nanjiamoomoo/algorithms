package graph.unionfind;

public class GraphValidTree {

    /**
     * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
     *
     * Assumption:
     * n >= 1
     *
     * Example1:
     * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
     * Output: true
     *
     * Example2:
     * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
     * Output: false
     *
     * @param n
     * @param edges
     * @return Return true if the edges of the given graph make up a valid tree, and false otherwise.
     */
    public boolean validTree(int n, int[][] edges) {
        /*
            use UnionFind to solve the problem.
            What is a valid tree:
            1. edges count = vertices count - 1
            2. no cycles (if we connect every edge, then the final remaining disjoint group/set is 1)
         */
        class UnionFind {
            int[] root;
            int[] rank;
            int count;

            public UnionFind(int size) {
                root = new int[size];
                rank = new int[size];
                for (int i = 0; i < size; i++) {
                    root[i] = i;
                    rank[i] = 1;
                    count = size;
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
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootY] > rank[rootX]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
                count--;
                return true;
            }

            public boolean connectivity (int x, int y) {
                return find(x) == find(y);
            }
        }

        if (n != edges.length + 1) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return false;
            }
        }
        return uf.count == 1? true : false;
    }
}
