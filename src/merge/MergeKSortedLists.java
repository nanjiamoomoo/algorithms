package merge;


import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    /**
     * Merge K sorted lists into one big sorted list in ascending order.
     * Assumption: ListOfLists is not null, and none of the lists is null.
     * @param listOfLists
     * @return
     */
    public ListNode merge(List<ListNode> listOfLists) {
        /*
            Data structure: minHeap
                we can use minHeap to find the next smallest node and generate its neighbors
         */
        PriorityQueue<ListNode>  minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.value < o2.value ? -1 : o1.value > o2.value ? 1 : 0;
            }
        });
        if (listOfLists.size() == 0) {
            return null;
        }
        for (ListNode head : listOfLists) {
            minHeap.offer(head);
        }
        ListNode head = minHeap.peek();
        ListNode prev = null;
        while (!minHeap.isEmpty()) {
            ListNode curr = minHeap.poll();
            if (prev != null) {
                prev.next = curr;
            }
            if (curr.next != null) {
                minHeap.offer(curr.next);
            }
            prev = curr;
        }
        return head;
    }
}
