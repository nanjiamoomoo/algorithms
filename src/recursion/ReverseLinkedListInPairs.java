package recursion;

class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int valve) {
        this.value = next.value;
        next = null;
    }
}

public class ReverseLinkedListInPairs {


    /**
     * Reverse pairs of elements in a singly-linked list.
     *
     * Examples:
     * 1. L = null, after reverse is null
     * 2. L = 1 -> null, after reverse is 1 -> null
     * 3. L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
     * 4. L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
     *
     * @param head
     * @return
     */
    public ListNode reverseSinglyLinkedListInPairs(ListNode head) {

        /*
               1 -> 2 -> 3 -> 4 -> null


               this is after child returns
               1 -> 2 -> 3  <- 4 (returned Head)
              head       |
                         ....

               this is what we return on current level
               2 -> 1 -> 4 -> 3 -> ....

               1. 1 connect to 4
               2. 2 connect to 1
               then return

               next = head.next //record 2
               head.next = new head;
               1 -> 4
               next.next = head

         */

        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = reverseSinglyLinkedListInPairs(head.next.next);
        newHead.next = head;
        return newHead;

    }

}
