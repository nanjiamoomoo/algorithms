package datastructure_implementation;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement the least recently used cache. It should provide set(), get() operations. If not exists, return null
 */
public class LRUCache<K, V> {

    private Map<K, Node> cacheMap;
    private Node<K, V> head;
    private Node<K, V> tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>();
    }

    public static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        public Node() {

        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public void update(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return this.key;
        }
        public V getValue() {
            return this.value;
        }
    }

    /**
     * If the key already exists, update it with the new value and put on the head of the list.
     * If the key does not exist, a dd a new element in the LRU cache.
     * @param key
     * @param value
     */
    public void set(K key, V value) {

        Node<K, V> node = cacheMap.get(key);
        //case 1: key already exists
        if (node != null) {
            remove(node);
        } else {
            //case 2: key does not exist
            //case 2.1: if the cache is full
            //case 2.2: if the cache is not full
            if (isFull()) {
                node = tail;
                remove(tail);
            } else {
                node = new Node<>();
            }

        }
        node.update(node.getKey(), value);
        add(node);
    }

    /**
     * remove the designated element/node from the LRU
     * @param node
     */
    private void remove(Node<K, V> node) {
        cacheMap.remove(node.getKey());
        //if only one element
        if (head == tail) {
            head = tail = null;
            return;
        }
        //if remove from head
        if (node == head) {
            head = node.next;
            node.next = null;
            return;
        }
        //if remove from tail
        if (node == tail) {
            tail = node.prev;
            node.prev = null;
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;

        //another way to write it
//        if (node.prev != null) {
//            node.prev.next = node.next;
//        }
//        if (node.next != null) {
//            node.next.prev = node.prev;
//        }
//        if (node.prev == null) {
//            head = node.next;
//        }
//        if (node.next == null) {
//            tail = tail.prev;
//        }
//        node.prev = null;
//        node.next = null;

    }

    /**
     * add a new element/node in the LRU
     * @param node
     */
    private void add(Node<K, V> node) {
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        cacheMap.put(node.getKey(), node);
    }

    /**
     * Get the value based on the key
     * @param key
     * @return Return the value of the element if the key exists; else return false;
     */
    public V get(K key) {
       Node<K, V> node = cacheMap.get(key);
       if (node != null) {
           remove(node);
           add(node);
           return node.value;
       }
       return null;
    }

    /**
     *
     * @return Return true if the LRU is full; else return false;
     */
    public boolean isFull() {
        return cacheMap.size() == capacity;
    }

    /**
     *
     * @return If the LRU is empty, return true; else return false;
     */
    public boolean isEmpty() {
        return cacheMap.size() == 0;
    }
}
