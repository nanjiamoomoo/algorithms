package dynamic_programming;

public class CanIWin {

    /**
     * There is an array of positive integers, in which each integer represents a piece of Pizza’s size, you and your friend take turns to pick pizza from either end of the array. You will pick first. Your friend follows a simple strategy: He will always pick the larger one he could get during his turn. The winner is the one who gets the larger total sum of all pizza. Return the max amount of pizza you can get.
     * Assumption: If during your friend's turn, the leftmost pizza has the same size as the rightmost pizza, he will pick the rightmost one.
     * Examples:
     *  Input: [2,1,100,3]
     *  Output: 102
     * Explanation: To win the game, you pick 2 first, then your friend will pick either 3, after that you could pick 100. In the end you could get 2 + 100 = 102, while your friend could only get 1 + 3 = 4.
     * @param nums
     * @return
     */
    public int canWin(int[] nums) {
        /*
            we can use dp to solve this problem
            define maxPizza[i][j] represents the max amount of pizza you can get for pizzas from ith position to jth positions
            if there is only one piece, then you will get nums[i]
            if there is two pieces, you can either choose the left one or the right one. Return the bigger bigger one

            maxPizze[i][j] = the max amount all cases:
            case1: if you choose i
                case 1.1: your friend choose j (nums[j] >= nums[i + 1]), maxPizza[i + 1][j - 1] + nums[i]
                case 1.2: else, maxPizza[i + 2][j] + nums[i]
            case2: if you choose j
                case 2.1: your friend chose j - 1 (nums[j - 1]>= nums[i]), maxPizza[i][j - 2] + nums[j]
                case 2.2: else maxPizza[i + 1][j - 1] + nums[j]


         */
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //maxPizzas[i][j] represents the max amount pizza you can get for pizzas pieces from ith position to jth position
        int[][] maxPizzas = new int[nums.length][nums.length];

        for (int i = 0; i < nums.length; i++) {
            maxPizzas[i][i] = nums[i];
        }

        /*
            note: from the analysis above, we noticed that we need bigger i and smaller j first
            and j should be always bigger than i, otherwise it should be 0
         */

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (i == nums.length - 2 || j == 1) {
                    maxPizzas[i][j] = Math.max(nums[i], nums[j]);
                    continue;
                }
                int leftMax = nums[j] >= nums[i + 1] ? maxPizzas[i + 1][j - 1] + nums[i] : maxPizzas[i + 2][j] + nums[i];
                int rightMax = nums[j - 1] >= nums[i] ? maxPizzas[i][j - 2] + nums[j] : maxPizzas[i + 1][j - 1] + nums[j];
                maxPizzas[i][j] = Math.max(leftMax, rightMax);
            }
        }
        return maxPizzas[0][nums.length - 1];
    }
}
