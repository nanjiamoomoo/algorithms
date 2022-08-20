package graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class DecodeWays {

    /**
     * Find all possible decode ways providing an input string
     *
     * The decoding scheme is:
     *
     * 1 -> A
     * 2 -> B
     * …
     * 10 -> J
     * 11 -> K
     * 12 -> L
     * …
     * 21 -> U
     * 22 -> V
     * ...
     * 26 -> Z
     * e.g. Given input String  "1121"
     *
     * It can be decoded as
     * [ "AABA",
     *   "AAU",
     *   "ALA",
     *   "KBA",
     *   "KU"
     * ]
     *
     * Assumptions:
     *  the input string only contains digit letters
     *  if there is no valid decode ways, return empty list, e.g. 3404
     *
     * @param input
     * @return return a list of String
     */
    public List<String> decodeWays(String input) {
        /*
            recursion tree
                             1  1  2   1
                                                level 0 index = 0
                   A121              K21
                                                level 1
              AA21   AL1          KB1   KU
                                       return      level 2
           AAB1 AAU     ALA          KBA
               return  return      return           level 3
         AABA
        return                                      level4



               when to return? when the index reach to the end of the string, we add to the final result.

              we can use dfs(String input, int index)
              index represents the position of the first digit letter in current level
              based on assumption input.charAt(index) can be from 0 ~ 9,
              if it is 0, we return, since it is not valid
              if (input.charAt(index) == 1 or 2) as long as index < input.length() - 1, we have two branches
              if it is equal to 3 ~ 9, we can only have one branch



         */
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfsDecodeWays(res, input, 0, sb);
        return res;
    }

    private void dfsDecodeWays(List<String> res, String input, int index, StringBuilder sb) {
        //base case
        if (index == input.length()) {
            res.add(sb.toString());
            return;
        }

        int num = input.charAt(index) - '0';
        if (num == 0) {
            return;
        }
        //first branch use one digit
        if (num >= 1 && num <= 9) {
            sb.append((char)(num - 1 + 'A'));
            dfsDecodeWays(res, input, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        //there is a potential second branch if index < input.length() - 1
        if (index < input.length() - 1) {
            num = (input.charAt(index) - '0') * 10 + input.charAt(index + 1) - '0';
            if (num >= 10 && num <= 26) {
                int length = sb.length();
                sb.append((char)(num - 1 + 'A'));
                dfsDecodeWays(res, input, index + 2, sb);
                sb.setLength(length);
            }
        }
    }


    public static void main(String[] args) {
        String input = "1121";
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.decodeWays(input));
    }
}
