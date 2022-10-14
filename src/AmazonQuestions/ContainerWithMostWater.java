package AmazonQuestions;

public class ContainerWithMostWater {

    /**
     * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
     *
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     *
     * Return the maximum amount of water a container can store.
     *
     * Notice that you may not slant the container.
     * @param height
     * @return
     */
    public int mostWater(int[] height) {
        /*
            We can use the two pointers to solve the problem
            one on the left and one on the right
            we compare array[left] with array[right] and we move the pointer with smaller height.
            because when we move left or right toward each other, the width of the container gets smaller,
            only when we move the smaller height pointer, the new rectangular formed can possibly have a larger area
         */
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while (left < right) {
            area = Math.max(area, (right - left) * (Math.min(height[left], height[right])));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return area;
    }
}
