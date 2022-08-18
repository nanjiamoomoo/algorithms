package graph.dijkstra_algorithm;

import java.util.*;

public class LargestProductOfLength {

    /**
     * Given a dictionary containing many words, find the largest product of two words’ lengths, such that the two words do not share any common characters.
     *
     * Assumptions
     *
     * The words only contains characters of 'a' to 'z'
     * The dictionary is not null and does not contains null string, and has at least two strings
     * If there is no such pair of words, just return 0
     * Examples
     *
     * dictionary = [“abcde”, “abcd”, “ade”, “xy”], the largest product is 5 * 2 = 10 (by choosing “abcde” and “xy”)
     * @param dict
     * @return
     */
    public int largestProduct(String[] dict) {
        /*
            Data Structure:
                pair <i, j> use to
                    i represents the index of the first word in the dictionary
                    j represents the index of the second word in the dictionary
                Max Heap: use to find the next two words that have the largest product
             Algorithm:
                BFS with PriorityQueue
                    PriorityQueue<int[]>
                      the <i, j> is polled out from queue,
                      the next possible largest can be <i + 1, j> or <i, j + 1> so we need to generate these two in the queue
                     Termination: once the queue is empty, or we find two words that do not share common characters


             Think? how to check if two words have common characters or not
                    Create a hashset using first String and verify the second String

         */
        Arrays.sort(dict, new Comparator<String>(){
            public int compare(String one, String two) {
                Integer length1 = one.length();
                Integer length2 = two.length();
                return length2.compareTo(length1);
            }
        }); //O(nlogn)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] one, int[] two) {
                Integer product1 = dict[one[0]].length() * dict[one[1]].length();
                Integer product2 = dict[two[0]].length() * dict[two[1]].length();
                return product2.compareTo(product1);
            }
        });

        int[] begin = {0, 1};
        maxHeap.offer(begin);
        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int i = curr[0]; //we want to make sure i is always smaller than j
            int j = curr[1];
            String one = dict[i];
            String two = dict[j];
            if (noCommonCharacters(one, two)) {
                return one.length() * two.length();
            }
            if (i + 1 != j) {
                maxHeap.offer(new int[]{i + 1, j});
            }
            if (j + 1 < dict.length) {
                maxHeap.offer(new int[]{i, j + 1});
            }
        }
        return 0;
        //TC: since the max size of the heap is n(n-1)/2: O(n^2 logn)
    }

    private boolean noCommonCharacters (String one, String two) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < one.length(); i++) {
            set.add(one.charAt(i));
        }
        for (int i = 0; i < two.length(); i++) {
            if (set.contains(two.charAt(i))) {
                return false;
            }
        }
        return true;
        //TC: O(m + n), m is the length of the first String, n is the length of the second string.
    }

    /**
     * Another solution to check common characters is to use bitMask
     * and then use double for loop to check each possible combination and update globalMax when find a valid answer. O(n ^ 2)
     */

    public int bitMask(String s) {
        int bitMask = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            bitMask |= 1 << (ch - 'a');
        }
        return bitMask;
    }
}
