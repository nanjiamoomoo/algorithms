package graph.unionfind;

import java.util.*;

public class NumberOfIslandsII {

    /**
     * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water and the given list of positions do not duplicate.
     *
     * Example:
     *
     * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
     * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
     *
     * 0 0 0
     * 0 0 0
     * 0 0 0
     * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
     *
     * 1 0 0
     * 0 0 0   Number of islands = 1
     * 0 0 0
     * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
     *
     * 1 1 0
     * 0 0 0   Number of islands = 1
     * 0 0 0
     * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
     *
     * 1 1 0
     * 0 0 1   Number of islands = 2
     * 0 0 0
     * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
     *
     * 1 1 0
     * 0 0 1   Number of islands = 3
     * 0 1 0
     * We return the result as an array: [1, 1, 2, 3]
     * @param m
     * @param n
     * @param positions
     * @return
     */
    public List<Integer> numIslands(int m, int n, int[][] positions) {
         /*
      we can use unionFind to solve the problem
      for each position, we use its index to represent itself

      for each new position, we will check its adjacent lands horizontally or vertically and union all the neighbors island together

      We need to use a Map<List<Integer>, Integer> to record all the positions that have been visited.
      The value of the map is the index of the position int the positions array

      TC: O(n)
      SC: O(n)

    */



        List<Integer> res = new ArrayList<>();
        if (positions.length== 0) {
            return res;
        }
        Map<List<Integer>, Integer> visited = new HashMap<>();
        UnionFind uf = new UnionFind(positions.length);
        int count = 0;
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
//            int beforeUnion = uf.count;
            for (int[] dir : DIRS) {
                int neiX = x + dir[0];
                int neiY = y + dir[1];
                if (isValid(neiX, neiY, m, n)) {
                    List<Integer> neighbor = Arrays.asList(neiX, neiY);
                    Integer neiIndex = visited.get(neighbor);
                    if (neiIndex != null) {
                        uf.union(i, neiIndex);
                    }
                }
            }
            visited.put(Arrays.asList(x, y), i);
//            int afterUnion = uf.count;
//            count = count + 1 + afterUnion - beforeUnion;
            count = uf.count - (positions.length - visited.size());
            res.add(count);
        }
        return res;
    }

    public static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    class UnionFind {
        int[] group;
        int[] rank;
        int count;

        public UnionFind(int size) {
            group = new int[size];
            rank = new int[size];
            count = size;
            for (int i = 0; i < size; i++) {
                group[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x == group[x]) {
                return x;
            }
            return group[x] = find(group[x]);
        }

        public void union(int x, int y) {
            int groupX = find(x);
            int groupY = find(y);
            if (groupX == groupY) {
                return ;
            }
            if (rank[groupX] > rank[groupY]) {
                group[groupY] = groupX;
            } else if (rank[groupY] > rank[groupX]) {
                group[groupX] = groupY;
            } else {
                group[groupY] = groupX;
                rank[groupX]++;
            }
            count--;
        }
    }
}
