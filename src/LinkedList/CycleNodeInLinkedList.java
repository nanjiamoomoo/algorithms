package LinkedList;

import merge.ListNode;

import java.util.HashSet;
import java.util.Set;

public class CycleNodeInLinkedList {

    /**
     * Check if a given linked list has a cycle. Return the node where the cycle starts. Return null if there is no cycle.
     * @param head
     * @return
     */
    public ListNode cycleNode(ListNode head) {
        /*
            how to find if a node has a cycle?
            We can use two pointers: fast and slow
            if there is a cycle, fast and slow will eventually meet.

            if there is a cycle, the next step is to add all the nodes in the loop in a Set

            the third step is to traverse from head until we find the first node in the loop set and return

         */
        if (head == null) {
            return null;
        }
        ListNode cycleLoopNode = hasCycle(head);
        if (cycleLoopNode == null) {
            return null;
        }

        //step 2:
        Set<ListNode> loopSet = new HashSet<>();
        loopSet.add(cycleLoopNode);
        ListNode start = cycleLoopNode.next;
        while (loopSet.add(start)) {
            start = start.next;
        }

        //step 3:
        while (!loopSet.contains(head)) {
            head = head.next;
        }
        return head;
    }

    /**
     *
     * @param head
     * @return if there is a cycle, return a node in the cycle loop. If there is no cycle, return null
     */
    private ListNode hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return fast;
            }
        }
        return null;
    }
}
