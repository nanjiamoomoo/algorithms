package graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {

    /**
     * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
     * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
     *
     * Given a string containing only digits, return all possible valid IP addresses that can be formed by inserting dots into the string. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
     * Example1:
     * Input:  ”25525511135”
     * Output: [“255.255.11.135”, “255.255.111.35”]
     * Example 2:
     * Input: s = "0000"
     * Output: ["0.0.0.0"]
     * Example 3:
     * Input: s = "101023"
     * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     * @param input
     * @return  return all possible valid IP addresses
     */
    public List<String> restoreIPAddress(String input) {
        /*
                                             101023
                  .       1.01023           10.1023              101.023
                  .    1.0.1023      10.1.023 10.10.23 10.102.3       101.0.23
              1.0.10.23 1.0.102.3      10.1.0.23 10.10.2.3             101.0.2.3

              on each level we have potential 3 positions to put our '.'

              the first is after the first digit (the number should between [0, 9]
              the second is after the second digit (the number can only be between[10, 99]
              the third option is after the third digit (the number have to between[100, 255]


             TC: 3 ^ 4     (there are 3 branches, and maximum depths of 4)
         */

        List<String> res = new ArrayList<>();
        if (input.length() < 4) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        helper(res, input, sb, 0, 0);
        return res;
    }

    /*
    private void helper(List<String> res, String input, StringBuilder sb, int index, int level) {
        if (level == 3) {
            //TODO: we need to check if the last portion valid.
            // if it has only one digit left, then it can be any number
            // if it has two digits left, then it has to be between[10, 99]
            // if it has 3 digits left, then it has to be between[100, 255]
            // if it has more than 3 digits, then it is not a valid
            int size = sb.length();
            if (index == input.length() - 1) {
                sb.append(input.charAt(index));
                res.add(sb.toString());
                sb.setLength(size);
                return;
            } else if (index == input.length() - 2) {
                if (input.charAt(index) != '0') {
                    sb.append(input.charAt(index));
                    sb.append(input.charAt(index + 1));
                    res.add(sb.toString());
                    sb.setLength(size);
                    return;
                }
            } else if (index == input.length() - 3) {
                int num = 10 * 10 * (input.charAt(index) - '0') + 10 * (input.charAt(index + 1) - '0') + input.charAt(index + 2) - '0';
                if (num >= 100 && num <= 255) {
                    sb.append(num);
                    res.add(sb.toString());
                    sb.setLength(size);
                }
            }
        }

        int size = sb.length();
        //first option
        if (index < input.length() - 1) {
            sb.append(input.charAt(index));
            sb.append('.');
            helper(res, input, sb, index + 1, level + 1);
            sb.setLength(size);
        }


        //second option
        if (index < input.length() - 2) {
            int num = 10 * (input.charAt(index) - '0') + input.charAt(index + 1) - '0';
            if (num >= 10 && num <= 99) {
                sb.append(num);
                sb.append('.');
                helper(res, input, sb, index + 2 , level + 1);
                sb.setLength(size);
            }
        }

        //third option
        if (index < input.length() - 3) {
            int num = 10 * 10 * (input.charAt(index) - '0') + 10 * (input.charAt(index + 1) - '0') + input.charAt(index + 2) - '0';
            if (num >= 100 && num <= 255) {
                sb.append(num);
                sb.append('.');
                helper(res, input, sb, index + 3, level + 1);
                sb.setLength(size);
            }
        }

    }
    */
    /**
     *      above code looks bulky, can we optimize it
     */

    private void helper(List<String> res, String input, StringBuilder sb, int index, int level) {
        /*
            what if we allow the fourth dot add to the StringBuilder
            on the level 4, if the index is at the end of the array, we can remove the last dot
         */
        if(index == input.length()) {
            if (level == 4) {
                //remove last dot
                sb.deleteCharAt(sb.length() - 1);
                res.add(sb.toString());
                //add the last dot back
                sb.append('.');
            }
            return;
        }

        int size = sb.length();
        //first option
        if (index < input.length()) {
            sb.append(input.charAt(index));
            sb.append('.');
            helper(res, input, sb, index + 1, level + 1);
            sb.setLength(size);
        }


        //combine second and third option
        if (input.charAt(index) != '0') {
            if (index < input.length() - 1) {
                int num = 10 * (input.charAt(index) - '0') + input.charAt(index + 1) - '0';
                sb.append(num);
                sb.append('.');
                helper(res, input, sb, index + 2 , level + 1);
                sb.setLength(size);
                if (index < input.length() -2) {
                    num = 10 * num + input.charAt(index + 2) - '0';
                    if (num <= 255) {
                        sb.append(num);
                        sb.append('.');
                        helper(res, input, sb, index + 3, level + 1);
                        sb.setLength(size);
                    }
                }
            }
        }
    }

}
