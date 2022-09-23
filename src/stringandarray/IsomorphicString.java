package stringandarray;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsomorphicString {

    /**
     * Two Strings are called isomorphic if the letters in one String can be remapped to get the second String. Remapping a letter means replacing all occurrences of it with another letter. The ordering of the letters remains unchanged. The mapping is two way and no two letters may map to the same letter, but a letter may map to itself. Determine if two given String are isomorphic.
     *
     * Assumptions:
     * The two given Strings are not null.
     *
     * Examples:
     * "abca" and "xyzx" are isomorphic since the mapping is 'a' <-> 'x', 'b' <-> 'y', 'c' <-> 'z'.
     * "abba" and "cccc" are not isomorphic.
     *
     * @param source
     * @param target
     * @return
     */
    public boolean isomorphic(String source, String target) {
        /*
            We can create one on one map between source and target

            for each unique letter in source, if the map doesn't have the mapping for this letter, we add a new mapping in the map
            otherwise, we will check if the mapping letter in the map matches the letter in the target

            We also need another set to contain all the letters in target being mapped so far, since there is a possibility that two different
            letters are mapping to the same letter in the target. If a unique letter doesn't have mapping in the map, but the mapped letter in the target was mapped before,
            then there is a contradiction

            TC: O(n)
            SC: O(1)
         */

        if (source.length() != target.length()) {
            return false;
        }

        Map<Character, Character> oneToOneMap = new HashMap<>();
        Set<Character> mappedTarget = new HashSet<>();

        for (int i = 0; i < source.length(); i++) {
            Character ch = oneToOneMap.get(source.charAt(i));
            if (ch != null) {
                if (!ch.equals(target.charAt(i))) {
                    return false;
                }
            } else {
                if (mappedTarget.contains(target.charAt(i))) {
                    return false;
                }
                oneToOneMap.put(source.charAt(i), target.charAt(i));
                mappedTarget.add(target.charAt(i));
            }
        }
        return true;
    }

    /*
        Another solution:
        We define a dictionary mapping_s_t which will be used to map characters in string s to characters in string t and another dictionary mapping_t_s which will be used to map characters in string t to characters in string s.
        Next, we iterate over the two strings one character at a time.
        Let's assume the character in the first string is c1 and the corresponding character in the second string is c2.
        If c1 does not have a mapping in mapping_s_t and c2 does not have a mapping in mapping_t_s, we add the corresponding mappings in both the dictionaries and move on to the next character.
        At this point, we expect both the character mappings to exist in the dictionaries and their values should be mapping_s_t[c1] = c2 and mapping_t_s[c2] = c1. If either of these conditions fails (c1 is not in the dictionary, c2 is not in the dictionary, unexpected mapping), we return false.
        Return true once both the strings have been exhausted.
        public boolean isIsomorphic(String s, String t) {

        int[] mappingDictStoT = new int[256];
        Arrays.fill(mappingDictStoT, -1);

        int[] mappingDictTtoS = new int[256];
        Arrays.fill(mappingDictTtoS, -1);

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Case 1: No mapping exists in either of the dictionaries
            if (mappingDictStoT[c1] == -1 && mappingDictTtoS[c2] == -1) {
                mappingDictStoT[c1] = c2;
                mappingDictTtoS[c2] = c1;
            }

            // Case 2: Ether mapping doesn't exist in one of the dictionaries or Mapping exists and
            // it doesn't match in either of the dictionaries or both
            else if (!(mappingDictStoT[c1] == c2 && mappingDictTtoS[c2] == c1)) {
                return false;
            }
        }

        return true;
    }
     */
}
