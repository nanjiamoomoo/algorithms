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
}
