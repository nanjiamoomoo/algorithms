public class SquareRoot {

    /**Given an integer number n, find its integer square root.

     Assumption:

     n is guaranteed to be >= 0.
     Example:

     Input: 18, Return: 4

     Input: 4, Return: 2
     *
     * @param x
     * @return
     */
    public int sqrt(int x) {
        /*
            We can use API  (int)Math.sqrt(x);
            but this is not the solution we are expectation.

            naive solution:
            if (x <= 1) {
                return x;
            }
            int res = 2;
            while (res <= 46430 && res * res < x) {
                res++;
            }
            if (res > 46340) return 46340;

            return res * res == x ? res : res - 1;

            but not optimal.

            How to improve?
            The problem converts to the largest i such that i * i <= x
            We can use binary search, the first step is to use binary search is to find the searching space
            then use binary search to find

            Is that possible to overflow? how about right * right > Integer.MAX_VALUE? yes. how to avoid it.
            use long to avoid data overflow

            If x < 2, return x.

            Set the left boundary to 2, and the right boundary to x / 2.

            While left <= right:

            Take num = (left + right) / 2 as a guess. Compute num * num and compare it with x:

            If num * num > x, move the right boundary right = pivot -1

            Else, if num * num < x, move the left boundary left = pivot

            Otherwise num * num == x, the integer square root is here, let's return it

         */
//        if (x <= 1) {
//            return x;
//        }
//        int left = 2;
//        int right = x / 2;
//
//        while (left < right - 1) {
//            int mid = left + (right - left) / 2;
//              long num = (long)mid * mid;
//            if (num == x) {
//                return mid;
//            } else if (num < x) {
//                left = mid;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return (long)right * right <= x ? right : left;

//        if (x <= 1) {
//            return x;
//        }
//        int res = 2;
//        while ((long)res * res < x) {
//            res++;
//        }
//        return (long)res * res == x ? res : res - 1;

        //Best Solution:
        if (x <= 1) {
            return x;
        }
        int left = (int)Math.pow(Math.E, 0.5 * Math.log(x));
        int right = left + 1;
        return (long) right * right > x ? left : right;
        //TC: O(1)
        //SC: O(1)
    }

    public static void main(String[] args) {
        System.out.println(Math.pow(Math.E, 0.5 * Math.log(3)));
    }
}
