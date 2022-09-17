package graph.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {

    /**
     *You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
     *
     * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
     *
     * Assumption: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
     *
     * Example 1:Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
     * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
     * Explanation:
     * Given: a / b = 2.0, b / c = 3.0
     * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
     * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
     *
     * Example 2: Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
     * Output: [3.75000,0.40000,5.00000,0.20000]
     *
     * Example 3: Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
     * Output: [0.50000,2.00000,-1.00000,-1.00000]
     *
     * @param equations
     * @param values
     * @param queries
     * @return Return the answers to all queries. If a single answer cannot be determined, return -1.0.
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /*
               We can use union find to solve the problem
               For each unique string, we need a unique index to identify its position in its union find data structure

               We can define a special uf structure with relativeWeight

               for an equation dividend/divisor = k, we always treat divisor group as the dominant root and then calculate relative weight.
               We can define the find function to return Pair<String, Double>,  string is the current root, and double is the relative weight to the root.

               We do union method, we update
               1. the dividend root's root to divisor root
               2. the relative weight of the dividend to the new root can be calculated

         */

        //step1: build index map
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (List<String> equation : equations) {
            if (!map.containsKey(equation.get(0))) {
                map.put(equation.get(0), index++);
            }
            if (!map.containsKey(equation.get(1))) {
                map.put(equation.get(1), index++);
            }
        }

        //step2:
        UnionFind uf = new UnionFind(map.size());
        for (int i = 0; i < values.length; i++) {
            uf.union(map.get(equations.get(i).get(0)), map.get(equations.get(i).get(1)), values[i]);
        }

        double[] res = new double[queries.size()];
        int i = 0;
        for (List<String> query : queries) {
            Integer indexDividend = map.get(query.get(0));
            Integer indexDivisor = map.get(query.get(1));
            if (indexDividend == null || indexDivisor == null) {
                res[i++] = -1.0;
                continue;
            }
            Pair rootWeightPairDividend = uf.find(indexDividend);
            Pair rootWeightPairDivisor = uf.find(indexDivisor);
            if (rootWeightPairDividend.variable != rootWeightPairDivisor.variable) {
                res[i++] = -1.0;
                continue;
            }
            res[i++] = rootWeightPairDividend.weight / rootWeightPairDivisor.weight;
        }
        return res;
    }

    class UnionFind {
        int[] root;
        double[] weight;

        public UnionFind(int size) {
            root = new int[size];
            weight = new double[size];

            for (int i = 0; i < size; i++) {
                root[i] = i;
                weight[i] = 1.0;
            }
        }

        public Pair find(int x) {
            if (x == root[x]) {
                return new Pair (x, 1.0);
            }
            Pair pair = find(root[x]);
            //path compression
            root[x] = pair.variable;
            weight[x] = weight[x] * pair.weight;

            pair.update(weight[x]);
            return pair;
        }

        public void union(int dividend, int divisor, double value) {
            Pair rootWeightPairX = find(dividend);
            Pair rootWeightPairY = find(divisor);
            if (rootWeightPairX.variable != rootWeightPairY.variable) {
                root[rootWeightPairX.variable] = rootWeightPairY.variable;
                weight[rootWeightPairX.variable] = value * rootWeightPairY.weight / rootWeightPairX.weight;
            }
        }
    }

    class Pair {
        private int variable;
        private double weight;

        public Pair (int variable, double weight) {
            this.variable = variable;
            this.weight = weight;
        }

        public void update(double weight) {
            this.weight = weight;
        }
    }
}
