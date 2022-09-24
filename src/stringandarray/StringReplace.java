package stringandarray;

/**
 * Replace all pattern in a string with a different string
 * <p>
 * Assumption:
 * input, pattern and target are not null
 * pattern is not empty string
 * <p>
 * Examples:
 * 1. input = "appledogapple", pattern = "apple", target = "cat", the result will be "catdogcat"
 */
public class StringReplace {

    public String stringReplace(String input, String pattern, String target) {

        /*
            pattern: is apple
            target: cat
            start = 1
            aappledogapplle
                  i
          sb: acatdogapplle

         */

        //step1: find the substring, return the start position of the substring
        //int patternStart = input.indexOf(pattern, fromIndex);
        //if pattern is not found, we append the remaining characters and return
        //sb.append(input.substring(fromIndex));
        //if pattern is found
        //step2: append the substring(fromIndex, patternStart)
        //step3: append the target
        //step4: move the pointer to start + pattern.length
        //go back to step 1

        int fromIndex = 0;
        StringBuilder sb = new StringBuilder();
        while (fromIndex < input.length()) {
            int patternStart = input.indexOf(pattern, fromIndex);
            if (patternStart == -1) {
                //when we don't find the pattern, we append the remaining.
                sb.append(input.substring(fromIndex));
                return sb.toString();
            }
            sb.append(input, fromIndex, patternStart).append(target);
            fromIndex = patternStart + pattern.length();
        }

        /*
             or
            if (input.length() < source.length()) {
              return input;
            }

            int begin = 0;
            StringBuilder sb = new StringBuilder();
            while (begin <= input.length() - source.length()) {
               int firstOccur = input.indexOf(source, begin);
               if (firstOccur == -1) {
                 break;
               }
               sb.append(input.substring(begin, firstOccur)).append(target);
               begin = firstOccur + source.length();
            }
            sb.append(input.substring(begin));
            return sb.toString();
         */

       return sb.toString();
    }
}
