package stringandarray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * String anagrams related problems
 */
public class Anagrams {

    /**
     * Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.
     * Assumption:
     * 1. sh is not null or empty
     * 2. lo is not null
     *
     * Example:
     * l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab"("ab", "ba").
     *
     * @param sh
     * @param lo
     * @return
     */
    public List<Integer> allAnagrams(String sh, String lo) {

        //maintain a sliding window of size sh.length() in the long string
        //we check the if the characters in the window match the characters in the short string
        //how? use a Map<Character, Integer>
        //we can create map that
        /*
            long: "abcbac"
            short: "ab"
            map {
                <a, 0>
                <b, 0>
            }
            the integer in the map represents the count of characters in the sliding window needed to match the map

            int matchNeeded = map.size
            abcbac
                i
            matchNeedded = 0
            [0,

            when a character is moving in the window, we check if the character is in the map
                if it is in the map, we can reduce the count of the character by 1.
                    Then, if the count reduces from 1 to 0, we reduce matchNeeded by 1
                    if the count reduce from -1 to 0, we increase the matchNeeded by 1
                if it is not in the map, we don't do anything
            when a character is moving out the window, we also need to check if the character is in the map
                if it is in the map, we can increase the count the character by 1.
                    Then, if the count increases from 0 to 1, we increase the matchNeeded by 1
                    if the count increase from -1 to 1, we decrease the matchNeeded by 1
             after moving one character in and one character out, we need to check the matchNeeded count, if it is 0, then we find one result (i - sh.length() + 1)

         */

        List<Integer> res = new ArrayList<>();
        if (sh.length() > lo.length()) {
            return res;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < sh.length(); i++) {
            char ch = sh.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        //matchNeeded records how many characters left to make a match
        int matchNeeded = map.size();

        int index = 0;
        while (index < lo.length()) {
            char chIn = lo.charAt(index);
            //add a character in the sliding window
            if (map.containsKey(chIn)) {
                int countNeeded = map.get(chIn) - 1;
                map.put(chIn, countNeeded);
                if (countNeeded == 0) {
                    matchNeeded--;
                } else if (countNeeded == -1) {
                    matchNeeded++;
                }
            }
            //when the index is larger or equals to the sh.length, there is moving out

            if (index >= sh.length()) {
                char chOut = lo.charAt(index - sh.length());
                if (map.containsKey(chOut)) {
                    int countNeeded = map.get(chOut) + 1;
                    map.put(chOut, countNeeded);
                    if (countNeeded == 0) {
                        matchNeeded--;
                    } else if (countNeeded == 1) {
                        matchNeeded++;
                    }
                }
            }
            if (matchNeeded == 0) {
                res.add(index - sh.length() + 1);
            }
            index++;
        }
        return res;
    }

    /**
     *Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
     * strs[i] consists of lowercase English letters.
     * Example 1:
     *
     * Input: strs = ["eat","tea","tan","ate","nat","bat"]
     * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        /*
            the question converts to how to uniquely identify a group anagrams
            we can use an array of size 26, each position represents the count of specific characters
            Then we traverse each string, and generate the array for each string

            once the array is generated, we convert the array into another string to uniquely identify a group
            such {1, 2, 3, 0, 0...} abbccc...
            then this converts to 123
         */
        Map<String, List> map = new HashMap<>();
        for (String s : strs) {
            int[] array = new int[26];
            for (char c : s.toCharArray()) {
                array[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int num : array) {
                sb.append((char)(num + '0'));
            }
            map.putIfAbsent(sb.toString(), new ArrayList<String>());
            map.get(sb.toString()).add(s);
        }
        return new ArrayList<>((List)map.values());

        /*
        TC: O(NK)
        SC: O(NK)
         */
    }

}
