package stackandqueue;

import java.util.LinkedList;

public class SortWithThreeStacks {

    /**
     * Given one stack with integers, sort it with two additional stacks (total 3 stacks).
     * <p>
     * After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order
     * <p>
     * Assumption:
     * The given stack is not null.
     * The time complexity should be O(n log n).
     *
     * @param s1
     */
    public void sort(LinkedList<Integer> s1) {

        /*
                s1[ x x x x x x
                s2[
                s3[

                We know that two stacks can achieve sorting
                We can define method sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int size)
                to sort the top size elements.
                We can partition and merge sort.
                    first step is to sort the top size/2 elements and elements end in s1.
                    the second step is to move the sorted size / 2 elements to s2
                    the third step is to sort the remaining half (size - size/2) elements and element end in s1
                    the fourth step is to move the sorted size - size /2 elements to s3
                    them merge sort them back to s1.

                to sort the top n elements in s1, we do
                    sort(s1, s2, s3, n / 2), elements end in s1
                    move n / 2 elements from s1 to s2
                    sort(s1, s2, s3, n - n / 2), elements end in s1
                    move n - n / 2 elements from s1 to s3
                    merge them back to s1

         */
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        LinkedList<Integer> s3 = new LinkedList<Integer>();
        sort(s1, s2, s3, s1.size());

    }

    private void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int size) {
        // when the soring size is 1, we don't need to sort
        if (size <= 1) {
            return;
        }

        sort(s1, s2, s3, size / 2);
        for (int i = 0; i < size / 2; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        sort(s1, s2, s3, size - size / 2);
        for (int i = 0; i < size - size / 2; i++) {
            s3.offerFirst(s1.pollFirst());
        }

        int i = 0;
        int j = 0;
        while (i < size / 2 && j < size - size / 2) {
            if (s2.peekFirst() >= s3.peekFirst()) {
                s1.offerFirst(s2.pollFirst());
                i++;
            } else {
                s1.offerFirst(s3.pollFirst());
                j++;
            }
        }
        while (i < size / 2) {
            s1.offerFirst(s2.pollFirst());
            i++;
        }
        while (j < size - size / 2) {
            s1.offerFirst(s3.pollFirst());
            j++;
        }
    }

    /*
       Another solution:
       sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int size)
       We can partition the top n elements into half
       step1: move top n / 2 elements from s1 to s2
       then sort top n / 2 elements in s2 and n - n / 2elements in s1
       sort(s2, s1, s3, n / 2)
       sort(s1, s2, s3, n - n / 2)
       then we merge them into s3
       at last we need to make sure all elements are sorted in s1. Then we move back to s1.


        public void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int size) {
        //base case, 当s1中要被sort的元素只有一个的时候，那就直接返回
        if (size <= 1) {
            return;
        }
        //将原先的stack进行对半分
        int size1 = size / 2;
        int size2 = size - size / 2;
        for (int i = 0; i < size2; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        //子问题就是对我的s1中顶部的size1个元素和s2中顶部的size2个元素进行sort
        sort(s2, s1, s3, size2);
        sort(s1, s2, s3, size1);
        //s1顶部size1个元素已经sorted， s2顶部size2个元素已经sorted, 用s3将他们合并
        int i = 0;
        int j = 0;
        while (i < size1 && j < size2) {
            if (s1.peekFirst() <= s2.peekFirst()) {
                s3.offerFirst(s1.pollFirst());
                i++;
            } else {
                s3.offerFirst(s2.pollFirst());
                j++;
            }
        }
        while (i < size1) {
            s3.offerFirst(s1.pollFirst());
            i++;
        }
        while (j < size2) {
            s3.offerFirst(s2.pollFirst());
            j++;
        }
        // s3顶部size个元素是有序的，而且从上到下是descending
        //将s3顶部size个元素放到s1中
        for (int k = 0; k < size; k++) {
            s1.offerFirst(s3.pollFirst());
        }
    }



     */


    public static void main(String[] args) {
        LinkedList<Integer> s = new LinkedList();
        s.offerFirst(2);
        s.offerFirst(3);
        s.offerFirst(1);
        s.offerFirst(1);
        s.offerFirst(2);
        s.offerFirst(4);
        SortWithThreeStacks sortWithThreeStacks = new SortWithThreeStacks();
        sortWithThreeStacks.sort(s);
    }
}
