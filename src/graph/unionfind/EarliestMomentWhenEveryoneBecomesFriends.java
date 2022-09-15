package graph.unionfind;

import java.util.Arrays;

public class EarliestMomentWhenEveryoneBecomesFriends {

    /**
     * There are n people in a social group labeled from 0 to n - 1. You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
     *
     * Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
     *
     * Example:
     * Input: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
     * Output: 3
     *
     * @param logs
     * @param n
     * @return  the earliest time for which every person became acquainted with every other person. If there is no such earliest time, return -1.
     */
    public int earliestTimeStamp(int[][] logs, int n) {
        /*
            High level idea:
                we can use union find to solve the problem
                We maintain a global timestamp in indicate the last time we connect two group of peoples
                Step1: sort based on time stamped
                Step2: We traverse logs and connect two person until all nodes are connected
                        if they have been connected, we ignore
                        if they have not been connected, we connect and update the global timestamp



         */

        class UnionFind {
            int[] root;
            int[] rank;
            int count;
            int timeStamp;

            public UnionFind(int size) {
                root = new int[size];
                rank = new int[size];
                for (int i = 0; i < size; i++) {
                    root[i] = i;
                    rank[i] = 1;
                }
                count = size;
                timeStamp = Integer.MIN_VALUE;
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

            public boolean connected(int x, int y) {
                return find(x) == find(y);
            }

            public int getCount() {
                return count;
            }

            public boolean allConnected() {
                return count == 1;
            }

            public void updateTimeStamp(int time) {
                timeStamp = time;
            }
        }


        Arrays.sort(logs, (int[] log1, int[] log2) -> {
            return log1[0] - log2[0];
        });
        UnionFind uf = new UnionFind(n);
        for (int[] log : logs) {
            if (uf.union(log[1], log[2])) {
                uf.updateTimeStamp(log[0]);
                if (uf.allConnected()) {
                    return uf.timeStamp;
                }
            }
        }
        return -1;
    }
}
