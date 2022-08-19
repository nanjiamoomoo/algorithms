package graph.topologicalsort;

import java.util.*;

public class AlienDictionary {

    /**
     * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
     *
     * Example 1:
     * Given the following words in dictionary,
     *
     * [
     *   "wrt",
     *   "wrf",
     *   "er",
     *   "ett",
     *   "rftt"
     * ]
     * The correct order is: "wertf".
     *
     * Example 2:
     * Given the following words in dictionary,
     *
     * [
     *   "z",
     *   "x"
     * ]
     * The correct order is: "zx".
     *
     * Example 3:
     * Given the following words in dictionary,
     *
     * [
     *   "z",
     *   "x",
     *   "z"
     * ]
     * The order is invalid, so return "".
     *
     * Note:
     *
     * You may assume all letters are in lowercase.
     * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
     * If the order is invalid, return an empty string.
     * There may be multiple valid order of letters, return any one of them is fine.
     * @param dict
     * @return
     */
    public String alienDictionary(String[] dict) {

        /*
            in order to find the right alphabet sequence, we need to compare the two adjacent words and find first different characters
             e.g.
                wrt
                wrfg-> t -> f

                why we only need to compare the two adjacent characters?
                  3 scenarios analysis

             By comparing the adjacent words, we can build adjacency list and use topological sort with BFS to solve the problem
                Data structure: Map<Character, Set<Character>> map, the value Set<Character> represents the neighbors of the Key character
                                    we use Set for deduplication purpose.
                                Map<Character, Integer> inDegree to record the inDegree of each character

         */

        //step1 build adjacencyList and inDegree[]
        Map<Character, Set<Character>> adjacencyList= new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        //TC: N * L
        for (String s : dict) {
            for (int i = 0; i < s.length(); i++) {
                adjacencyList.putIfAbsent(s.charAt(i), new HashSet<>());
                inDegree.putIfAbsent(s.charAt(i), 0);
            }
        }



        //TC: N * L
        for (int i = 0; i < dict.length - 1; i++) {
            char[] dependencies = findDependency(dict[i], dict[i + 1]);
            if (dependencies != null) {
                //e.g.
                // t -> f
                if (adjacencyList.get(dependencies[0]).add(dependencies[1])) {
                    inDegree.put(dependencies[1], inDegree.get(dependencies[1]) + 1);
                }
            }
        }

        //O(V + E)
        Queue<Character> queue = new ArrayDeque<>();
        //find all the inDegree zero nodes
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character curr = queue.poll();
            sb.append(curr);
            Set<Character> neighbors = adjacencyList.get(curr);
            for (Character nei: neighbors) {
                int in = inDegree.get(nei);
                inDegree.put(nei, --in);
                if (in == 0) {
                    queue.offer(nei);
                }
            }
        }
        return sb.length() == inDegree.size() ? sb.toString() : "";
    }

    //TC: O(L)
    private char[] findDependency(String one, String two) {
        int i = 0;
        int length = Math.min(one.length(), two.length());
        while (i < length) {
            if (one.charAt(i) != two.charAt(i)) {
               return new char[]{one.charAt(i), two.charAt(i)};
            }
            i++;
        }
        return null;
    }

    public static void main(String[] args) {
        String[] dict = {"wnrwjuhezuhmauwhgflfmzma","aezarvahlavgfv","zaajzuf","zmzznjuanhaaplwjp","pzarfujmvzufmewljnvhmjrzpjgn","jlrjnefpz","eufvlnzvwu","vpfuereujmufhaghrrjnzvwnaj","vlzgel","mgrzemlmuzmvrza","mzfgmgfhwfrhzere","mjegjw","mlenmjjwhhhvwfpfmehz","mhrujzuuew","lznaggjvnfeeajzumvwphezmjnmv","hwulgnpwjumumn","hlrevzwrvrvrml","unzgjjuprhhwugjamej","rgrhvnnzrhg","rupralvvjlhpelav","fzvjv","fnwjrepzejuzhg"};
        AlienDictionary alienDictionary = new AlienDictionary();
        System.out.println(alienDictionary.alienDictionary(dict));
    }
}
