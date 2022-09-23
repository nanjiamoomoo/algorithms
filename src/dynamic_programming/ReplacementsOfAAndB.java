package dynamic_programming;

public class ReplacementsOfAAndB {

    /**
     * Given a string with only character 'a' and 'b', replace some of the characters such that the string becomes in the forms of all the 'b's are on the right side of all the 'a's.
     * Determine what is the minimum number of replacements needed.
     * <p>
     * Examples:
     * "abaab", the minimum number of replacements needed is 1 (replace the first 'b' with 'a' so that the string becomes "aaaab").
     * Assumptions:
     * The given string is not null.
     *
     * @param input
     * @return the minimum number of replacements needed.
     */
    public int minReplacements(String input) {
        /*
            define two array:
            replaceA[i] represents the min number of replacements needed to replace all characters to 'a' from 0th index to ith index
            replaceB[i] represents the min number of replacements needed to replace all characters to 'b' from ith index to n - 1 index

            for each i from 0 ~ n - 1
            we need to find the min of replaceA[i] + replaceB[i + 1]
         */
        if (input.length() <= 1) {
            return 0;
        }
        int[] replaceA = new int[input.length()];
        replaceA[0] = input.charAt(0) == 'b' ? 1 : 0;
        for (int i = 1; i < input.length(); i++) {
            replaceA[i] = replaceA[i - 1] + (input.charAt(i) == 'b' ? 1 : 0);
        }

        int[] replaceB = new int[input.length()];
        replaceB[input.length() - 1] = input.charAt(input.length() - 1) == 'a' ? 1 : 0;
        for (int i = input.length() - 2; i >= 0; i--) {
            replaceB[i] = replaceB[i + 1] + (input.charAt(i) == 'a' ? 1 : 0);
        }

        int min = Math.min(replaceA[input.length() - 1], replaceB[0]);
        for (int i = 0; i < input.length() - 1; i++) {
            min = Math.min(min, replaceA[i] + replaceB[i + 1]);
        }
        return min;
    }

    public static void main(String[] args) {
        String s = "bb";
        String s1 = "aaaa";
        ReplacementsOfAAndB replacementsOfAAndB = new ReplacementsOfAAndB();
        System.out.println(replacementsOfAAndB.minReplacements(s1));
    }

}
