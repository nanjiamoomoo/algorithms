package AmazonQuestions;

public class IntegerToRoman {

    /**
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
     *
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     *
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given an integer, convert it to a roman numeral.
     * @param num
     * @return
     */
    public String convertToRoman(int num) {
        /*
            1. Determine how many thousands, append M
            2. Determine how many hundreds, 100 ~ 300, 400, 500 ~ 800, 900
            3. Determine how many tens, 10 ~ 30, 40, 50 ~ 80, 90
            5. Determine how many one, 1 ~ 3, 4, 5 ~ 8, 9

            private static final String[] thousands = {"", "M", "MM", "MMM"};
            private static final String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
            private static final String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            private static final String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

            public String intToRoman(int num) {
                return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
            }
         */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num / 1000; i++) {
            sb.append('M');
        }
        num %= 1000;
        appendHundreds(sb, num / 100);
        num %= 100;
        appendTens(sb, num / 10);
        num %= 10;
        appendOnes(sb, num);
        return sb.toString();
    }

    private void appendHundreds(StringBuilder sb, int hundreds) {
        if (hundreds == 9) {
            sb.append("CM");
            return;
        }
        if (hundreds == 4) {
            sb.append("CD");
            return;
        }
        if (hundreds >= 5) {
            sb.append('D');
            hundreds -= 5;
        }
        for (int i= 0; i < hundreds; i++) {
            sb.append('C');
        }
    }private void appendTens(StringBuilder sb, int tens) {
        if (tens == 9) {
            sb.append("XC");
            return;
        }
        if (tens == 4) {
            sb.append("XL");
            return;
        }
        if (tens >= 5) {
            sb.append('L');
            tens -= 5;
        }
        for (int i= 0; i < tens; i++) {
            sb.append('X');
        }
    }private void appendOnes(StringBuilder sb, int ones) {
        if (ones == 9) {
            sb.append("IX");
            return;
        }
        if (ones == 4) {
            sb.append("IV");
            return;
        }
        if (ones >= 5) {
            sb.append('V');
            ones -= 5;
        }
        for (int i= 0; i < ones; i++) {
            sb.append('I');
        }
    }

    public final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public String convertToRomanII(int num) {
        /*
            we can create two arrays
            values[]{1000, 900, 500, 400, ...}
            symbols[]{"M", "CM", "D", "CD", ...}

            for each values[i] < num, then we can append the corresponding symbol to the final result
         */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num > 0; i++) {
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();


    }
}
