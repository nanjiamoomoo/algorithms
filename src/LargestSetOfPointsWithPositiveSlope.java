import java.util.Arrays;
import java.util.Comparator;

public class LargestSetOfPointsWithPositiveSlope {

    /**
     * Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can form a set such that any pair of points in the set can form a line with positive slope. Return the size of such a maximal set.
     * Assumption:
     * The given array is not null
     * Note: if there does not even exist 2 points can form a line with positive slope, should return 0.
     *
     * Examples:
     * <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are {<0, 0>, <1, 1>, <2, 3>}, the size is 3.
     * @param points
     * @return
     */
    public int largestSetOfPointsWithPositiveSlope(Point[] points) {
        /*
                Scenario 1:
                clarification: can <0, 1> <0, 0> can count as positive slope? No
                 What is positive slope? that means when x1 > x2, y1 > y2
                If we sort points based on x index first, then the problem becomes to find the longest ascending subsequence int the array

                lAS[i] represents the largest count of points with positive slopes between 0th index point and ith index point(including ith index)

                lAS[i] =
                    for j between 0, i - 1, find max among
                    if (points[i].x != points[j].x && points[i].y > points[j].y)
                       lAS[j] + 1
                    else = 1

                Maintain a globalMax and return the globalMax

                TC: O(n ^ 2)
                SC: o(n)
                Scenario 2:
                clarification: can <0, 1> <0, 0> can count as positive slope? Yes
                What is positive slope? that means when x1 >= x2, y1 > y2
                If we sort points based on x index first, then the problem becomes to find the longest ascending subsequence int the array

                lAS[i] represents the largest count of points with positive slopes between 0th index point and ith index point(including ith index)

                lAS[i] =
                    for j between 0, i - 1, find max among
                    if (points[i].y > points[j].y)
                        lAS[j] + 1
                    else = 1

                Maintain a globalMax and return the globalMax
                please be aware the if the globalMax == 1, means there does not exist 2 points can form a line with positive slope, we should return 0.
         */

        //solution below is based on scenario 1.
        if (points.length < 2) {
            return 0;
        }
        Arrays.sort(points, new Comparator<Point>(){
            public int compare(Point p1, Point p2) {
                return p1.x - p2.x;
            }
        });
        //lAS[i]: Longest Ascending Sequence ending at i with respect to y
        int[] lAS = new int[points.length];
        lAS[0] = 1;
        int globalMax= 1;
        for (int i = 1; i < lAS.length; i++) {
            int curMax = 0;
            for (int j = 0; j < i; j++) {
                if (points[i].x != points[j].x && points[i].y > points[j].y) {
                    curMax = Math.max(lAS[j], curMax);
                }
            }
            lAS[i] = curMax + 1;
            if (lAS[i] > globalMax) {
                globalMax = lAS[i];
            }
        }
        return globalMax < 2 ? 0 : globalMax;
    }
}
