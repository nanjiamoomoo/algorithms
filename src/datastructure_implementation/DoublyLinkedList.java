package datastructure_implementation;

/**
 * Design your implementation of the doubly linked list. A node in a singly linked list should have three attributes: val and next and prev. val is the value of the current node, and next is a pointer/reference to the next node, and prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement these functions in your linked list class:
 *
 * get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
 * addAtTail(val) : Append a node of value val to the last element of the linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
 * deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 */
public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {

    }

    public static class Node {
        int value;
        Node next;
        Node prev;

        public Node() {
            this(0);
        }

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * Get the value of the index-th node in the linked list.
     *
     * @param index
     * @return value if index is valid; else, return -1.
     */
    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        Node curr = head;
        while (index-- != 0) {
            curr = curr.next;
        }
        return curr.value;
    }

    /**
     * @return size of the linked list
     */
    public int size() {
        return size;
    }

    /**
     * if the linked list is empty or not
     *
     * @return return true if empty; else return false;
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add a node of the give value before the first element of the linked list. After the insertion, the new node will be the head of the linked list.
     *
     * @param value the value of the new node
     */
    public void addAtHead(int value) {
        Node newNode = new Node(value);
        size++;
        if (head == null) {
            tail = newNode;
            head = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     *
     * @param value the value of the new node
     */
    public void addAtTail(int value) {
        Node newNode = new Node(value);
        size++;
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;

    }

    /**
     * Add a node of the given value before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     *
     * @param index
     * @param value
     */
    public void addAtIndex(int index, int value) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(value);
            return;
        }
        if (index == size) {
            addAtTail(value);
            return;
        }
        //at this stage, there are at least two nodes in the list
        Node newNode = new Node(value);
        Node curr = head;
        while (index-- != 0) {
            curr = curr.next;
        }
        curr.prev.next = newNode;
        newNode.next = curr;
        newNode.prev = curr.prev;
        curr.prev = newNode;
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     *
     * @param index
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            deleteAtHead();
            return;
        }
        if (index == size - 1) {
            deleteAtTail();
            return;
        }
        Node curr = head;
        while (index-- != 0) {
            curr = curr.next;
        }
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        size--;
    }

    /**
     * remove the first node in the linked list
     */
    public void deleteAtHead() {
        if (size == 0) {
            return;
        }
        size--;
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        head = head.next;
        head.prev = null;

    }

    /**
     * remove the last node in the linked list
     */
    public void deleteAtTail() {
        if (size == 0) {
            return;
        }
        size--;
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        tail = tail.prev;
        tail.next = null;

    }
}
