package merge;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedArray {
    class Element {
        int x;
        int y;
        public Element(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    /**
     * Merge K sorted array into one big sorted array in ascending order.
     * Assumption: The input arrayOfArrays is not null, none of the arrays is null either.
     * @param arrayOfArrays
     * @return
     */
    public int[] merge(int[][] arrayOfArrays) {
        /*
            This is graph problem.
            We can use a min heap to find the next smallest element and generate its neighbors
                Data Structure:
                    1. minHeap
                    2. element {x, y} x identifies its array, y identify its index in the array
         */
        PriorityQueue<Element> minHeap = new PriorityQueue<>(new Comparator<Element>() {
            public int compare(Element e1, Element e2) {
                Integer i1 = arrayOfArrays[e1.x][e1.y];
                Integer i2 = arrayOfArrays[e2.x][e2.y];
                return i1.compareTo(i2);
            }
        });
        int size = 0;
        for (int i = 0; i < arrayOfArrays.length; i++) {
            size += arrayOfArrays[i].length;
            if (arrayOfArrays[i].length != 0) {
                Element ele = new Element(i, 0);
                minHeap.offer(ele);
            }
        }
        int[] res = new int[size];
        int k = 0;
        while (!minHeap.isEmpty()) {
            Element curr = minHeap.poll();
            int x = curr.x;
            int y = curr.y;
            res[k++] = arrayOfArrays[x][y];
            if (y < arrayOfArrays[x].length - 1) {
                Element newEle = new Element(x, y + 1);
                minHeap.offer(newEle);
            }
        }
        return res;
        //TC: klogk + (n-k)logk = nlogk. k is the # of arrays, n is the total elements in the arraysOfArrays
        //SC: O(k)
    }

    public static void main(String[] args) {
        int[][] array = {{3},{1,2,3,4,5}};
        MergeKSortedArray mergeKSortedArray = new MergeKSortedArray();
        mergeKSortedArray.merge(array);
    }
}
