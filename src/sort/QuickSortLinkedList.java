package sort;

import merge.ListNode;

public class QuickSortLinkedList {

    /**
     * Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The quick sort algorithm should be used to solve this problem.
     *
     * Examples
     * null, is sorted to null
     * 1 -> null, is sorted to 1 -> null
     * 1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
     * 4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
     *
     * @param head
     * @return
     */
    public ListNode quickSort(ListNode head) {
        /*
            Step1: choose the head (most left node) as the partition node
            Step2: traverse the entire list and partition the linked into two linked list
                   the nodes on the left side are smaller than the partition node
                   the nodes on the right side are larger than the partition node

            Step3: on each recursion level, we need to keep record the smallest and largest node
                   The left side linked list largest node should connect to the partition node
                   The partition node should connect to the smallest node in the right side linked list

                   then return smallest and largest node on this layer

           TC: Average O(nlogn)
           SC: O(n)

         */
        ListNode[] res = quickSortHelper(head);
        return res[0];
    }

    private ListNode[] quickSortHelper(ListNode head) {
        if (head == null || head.next == null) {
            ListNode[] res = new ListNode[2];
            res[0] = head;
            res[1] = head;
            return res;
        }

        ListNode[] partitions = partitionAndConnect(head);
        ListNode[] left = quickSortHelper(partitions[0]);
        ListNode[] right = quickSortHelper(partitions[1]);
        ListNode[] res = new ListNode[2];
        res[0] = left[0] == null ? partitions[2] : left[0];
        res[1] = right[0] == null ? partitions[2] : right[1];
        if (left[0] != null) {
            left[1].next = partitions[2];
        }
        partitions[2].next = right[0];
        return res;
    }


    private ListNode[] partitionAndConnect(ListNode head) {
        ListNode[] res = new ListNode[3];
        ListNode mid = head;
        int pivot = head.value;
        head = head.next;
        ListNode dummyOne = new ListNode(-1);
        ListNode dummyTwo = new ListNode(-1);
        ListNode one = dummyOne;
        ListNode two = dummyTwo;
        while (head != null) {
            if (head.value < pivot) {
                one.next = head;
                one = one.next;
            } else if (head.value > pivot) {
                two.next = head;
                two = two.next;
            }
            head = head.next;
        }
        one.next = null;
        two.next = null;
        mid.next = null;

        res[0] = dummyOne.next;
        res[1] = dummyTwo.next;
        res[2] = mid;
        return res;
    }
}
