package graph;

import java.util.*;

public class WordLadder {

    /**
     * Given a begin word, an end word and a dictionary, find the least number transformations from begin word to end word, and return the length of the transformation sequence. Return 0 if there is no such transformations.
     * <p>
     * In each transformation, you can only change one letter, and the word should still in the dictionary after each transformation.
     * <p>
     * Assumptions
     * <p>
     * 1. All words have the same length.
     * <p>
     * 2. All words contain only lowercase alphabetic characters.
     * <p>
     * 3. There is no duplicates in the word list.
     * <p>
     * 4.The beginWord and endWord are non-empty and are not the same.
     * <p>
     * Example: start = "git", end = "hot", dictionary = {"git","hit","hog","hot"}
     * <p>
     * Output: 3
     * <p>
     * Explanation: git -> hit -> hot
     *
     * @param begin
     * @param end
     * @param wordList : dictionary
     * @return Return the length of the transformation sequence if there is such transformation. Return 0 if there is no such transformations.
     */
    public int wordLadderI(String begin, String end, List<String> wordList) {
        /*
             for each word we have to generate its neighbors
                as long as its neighbor is a valid word in the dictionary
                   we will compare it to the end
                        if it equals to the end, we return the level of the BFS (height of the graph)
                        if it does not equal to the end, we generate it in the queue

              Since we are using BFS algorithm, we will maintain a queue data structure
              since we don't want to generate repeat word, we will maintain a set to keep all the visited word
         */

        //put the wordList in a set for quicker check
        Set<String> dict = new HashSet<>(wordList);
        //if the dictionary does not have the end string, then there is definitely no such transformation.
        if (!dict.contains(end)) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(begin);
        visited.add(begin);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                List<String> neighbors = findNeighbors(curr, dict);
                for (String nei : neighbors) {
                    if (visited.add(nei)) {
                        if (nei.equals(end)) {
                            return step + 1;
                        }
                        queue.offer(nei);
                    }
                }
            }
            step++;
        }
        return 0;
    }

    private List<String> findNeighbors(String s, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char origin = array[i];
            for (int j = 0; j < 26; j++) {
                char ch = (char) ('a' + j);
                if (ch != origin) {
                    array[i] = ch;
                    String tmp = new String(array);
                    if (dict.contains(tmp)) {
                        res.add(tmp);
                    }
                }
            }
            array[i] = origin;
        }
        return res;
    }

    /**
     * Given a begin word, an end word and a dictionary, find the least number transformations from begin word to end word, and return the transformation sequences. Return empty list if there is no such transformations.
     * <p>
     * In each transformation, you can only change one letter, and the word should still in the dictionary after each transformation.
     * <p>
     * Assumptions
     * <p>
     * 1. All words have the same length.
     * <p>
     * 2. All words contain only lowercase alphabetic characters.
     * <p>
     * 3. There is no duplicates in the word list.
     * <p>
     * 4.The beginWord and endWord are non-empty and are not the same.
     * <p>
     * Example: start = "git", end = "hot", dictionary = {"git","hit","hog","hot","got"}
     * <p>
     * Output: [["git","hit","hot"], ["git","got","hot"]]
     *
     * @param begin
     * @param end
     * @param wordList
     * @return Return the list of transformation sequences. Return empty list if there is no such transformations
     */
    public List<List<String>> wordLadderII(String begin, String end, List<String> wordList) {

        /*

                                  git                 <git> level 0 {git}
                              hit     got      <git, hit>   <git, got>  level 1 {git, hit , got}
                                  hot        <git, hit, hot>  <git, got, hot>   level 2 {git, hit, hot}
                                  hog

                maintain a Queue<List<String>>
                    expand a list
                        find the last string and its neighbors
                            for each neighbors, we generate a new list

                 Notes:
                 1. as long as we find an end string, we have to make sure the current layer has been entire traversed before ending the loop, since we need all possible solution
                 2. we can only control the visited nodes in previous layer, if we control visited under same layer, we might end up lose possible solutions
                    how should we do it, we can add all the visited node in the same layer in a list, and add them all to the visited set, or remove them from the word set

                 3.when to terminate: after each traverse level, if we cannot find the end word, then we know we have find out result.
         */
        List<List<String>> res = new ArrayList<>();
        Queue<List<String>> queue = new ArrayDeque<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(end)) {
            return res;
        }

        List<String> start = new ArrayList<>();
        start.add(begin);
        queue.offer(start);
        dict.remove(begin);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> toRemoved = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                List<String> currList = queue.poll();
                String last = currList.get(currList.size() - 1);
                List<String> neighbors = findNeighbors(last, dict);
                for (String nei : neighbors) {
                    currList.add(nei);
                    List<String> newList = new ArrayList<>(currList);
                    if (nei.equals(end)) {
                        res.add(newList);
                    }
                    toRemoved.add(nei);
                    queue.offer(newList);
                    currList.remove(currList.size() - 1);
                }
            }
            for (String s : toRemoved) {
                dict.remove(s);
            }
            if (!dict.contains(end)) {
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String start = "git";
        String end = "hot";
        List<String> dict = Arrays.asList("git", "hit", "hog", "hot", "got");
        WordLadder w = new WordLadder();
        System.out.println(w.wordLadderII(start, end, dict));
    }
}
