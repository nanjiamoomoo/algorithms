package graph.unionfind;

public class NumOfProvinces {

    /**
     * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
     *
     * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
     *
     * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
     *
     * Examples:
     * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
     * Output: 2
     *
     * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
     * Output: 3
     * @param isConnected
     * @return the total number of provinces.
     */
    public int numberOfProvinces(int[][] isConnected) {
        /*
            we can use union find to solve the problem
            Step1: define unionfind class
            Step2: traverse isConnected[][] array to find disjoint sets
            Step3: return disjoint sets
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
                }
                count = size;
            }

            public int find(int x) {
                if (x == root[x]) {
                    return x;
                }
                return root[x] = find(root[x]);
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX != rootY) {
                    count--;
                    if (rank[rootX] > rank[rootY]) {
                        root[rootY] = rootX;
                    } else if (rank[rootY] > rank[rootX]) {
                        root[rootX] = rootY;
                    } else {
                        root[rootY] = rootX;
                        rank[rootX] += 1;
                    }
                }
            }

            public boolean connected(int x, int y) {
                return find(x) == find(y);
            }

            public int getCount() {
                return count;
            }
        }

        UnionFind uf = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }
}
