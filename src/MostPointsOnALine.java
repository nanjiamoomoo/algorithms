import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class MostPointsOnALine {

    /**
     * Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can be crossed by a single line in 2D space.
     * Assumption: The given array is not null and it has at least 2 points
     * Examples: <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3(<0, 0>, <1, 1>, <3, 3> are on the same line)
     * @param points
     * @return The largest number of points that can be crossed by a single line in 2D space.
     */
    public int most(Point[] points) {
        /*
            Q: how to determine if several points are on the same line?
                same slope and passing through one common points

            solution
                for each point, we will create a hashMap<Double, Integer> to count how many points are on the same line passing through current point for each different slope
                1. a corner case is where they have the same x coordinate (infinite slope), we need to a separate variable to count the number
                2. the second corner case is the same x and y coordinates, we need a separate variable to count the number
                then we maintain a globalMax to record the maximum number of points on a single line
         */
        if (points.length == 2) {
            return 2;
        }
        int globalMax = 2;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> countDiffSlopes = new HashMap<>();
            int countSameX = 0;
            int countSameXY = 0;
            int currMax = 1;
            for (int j = 0; j < points.length; j++) {
                if (points[i].x != points[j].x) {
                    double slope = (points[j].y - points[i].y + 0.0) / (points[j].x - points[i].x);
                    int count = countDiffSlopes.getOrDefault(slope, 0) + 1;
                    currMax = Math.max(currMax, count);
                    countDiffSlopes.put(slope, count);
                } else if (points[i].y != points[j].y){
                    countSameX++;
                } else {
                    countSameXY++;
                }
            }
            currMax = Math.max(countSameX + countSameXY, currMax + countSameXY);
            globalMax = Math.max(globalMax, currMax);
        }
       return globalMax;
    }

    public static void main(String[] args) {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 3);
        Point point3 = new Point(1, 1);
        Point point4 = new Point(2, 3);
        Point point5 = new Point(1, 1);
        Point point6 = new Point(2, 2);
        Point point7 = new Point(3, 4);
        Point point8 = new Point(9, 10);
        Point[] points = {point1, point2, point3, point4, point5, point6, point7, point8};
        MostPointsOnALine mostPointsOnALine = new MostPointsOnALine();
        System.out.println(mostPointsOnALine.most(points));


    }
}
