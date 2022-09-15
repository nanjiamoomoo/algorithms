package graph.unionfind;

import java.util.*;

public class SmallestStringWithSwaps {

    /**
     * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
     *
     * You can swap the characters at any pair of indices in the given pairs any number of times.
     *
     * Assumption:
     * string only contains lower case English letters.
     *
     * Input: s = "dcab", pairs = [[0,3],[1,2]]
     * Output: "bacd"
     * Explanation:
     * Swap s[0] and s[3], s = "bcad"
     * Swap s[1] and s[2], s = "bacd"
     *
     * Input: s = "cba", pairs = [[0,1],[1,2]]
     * Output: "abc"
     * Explanation:
     * Swap s[0] and s[1], s = "bca"
     * Swap s[1] and s[2], s = "bac"
     * Swap s[0] and s[1], s = "abc"
     *
     * @param s
     * @param pairs
     * @return the lexicographically smallest string that s can be changed to after using the swaps.
     *
     */
    public String smallestString(String s, List<List<Integer>> pairs) {
        /*
            Step1: use unionFind to find indices belong to the same group
            Step2: For the characters under the same group, we sort them and put them back into the original position
         */
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
                if (x == root[x]){
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
        }

        UnionFind uf = new UnionFind(s.length());
        for(List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] array = uf.root;
        for (int i = 0; i < array.length; i++) {
            int currRoot = uf.find(i);
            map.putIfAbsent(currRoot, new ArrayList<Integer>());
            List<Integer> list = map.get(currRoot);
            list.add(i);
        }

        char[] charArray = s.toCharArray();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> indices= entry.getValue();
            List<Character> subChar = new ArrayList<>();
            for (int index : indices) {
                subChar.add(charArray[index]);
            }
            Collections.sort(subChar);
            for (int i = 0; i < indices.size(); i++) {
                charArray[indices.get(i)] = subChar.get(i);
            }
        }
        return String.valueOf(charArray);
    }
}
