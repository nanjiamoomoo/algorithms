package LinkedList;

import merge.ListNode;

public class PartitionLinkedList {

    /**
     * Given a linked list and a target value T, partition it such that all nodes less than T are listed before the nodes larger than or equal to target value T. The original relative order of the nodes in each of the two partitions should be preserved.
     * Examples: L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
     * @param head
     * @param target
     * @return
     */
    public ListNode partition(ListNode head, int target) {

        /*
             We can generate two linked lists. the first linked list is less than; the second linked list is larger or equal to

             2   4   3   5   1  null
                                c
             first: 2  1
                       one
             second:  4  3   5
                            two

             at last one.next = head of the second linked

             Please note that it is possible two.next != null, we have to set it to null.
         */
        ListNode dummyOne = new ListNode(-1);
        ListNode dummyTwo = new ListNode(-1);
        ListNode one = dummyOne;
        ListNode two = dummyTwo;
        ListNode curr = head;
        while (curr != null) {
            if (curr.value < target) {
                one.next = curr;
                one = one.next;
            } else {
                two.next = curr;
                two = two.next;
            }
            curr = curr.next;
        }
        one.next = dummyTwo.next;
        two.next = null;

        return dummyOne.next;

    }
}
