package datastructure_implementation;

import java.util.Arrays;
import java.util.Objects;

/**
 * Design a HashMap
 */
public class HashMap<K, V> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int SCALE_FACTOR = 2;
    private static final int DEFAULT_CAPACITY = 16;
    private Entry<K, V>[] array;
    private int size;
    private float loadFactor;

    public HashMap() {
        //calling another constructor
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    public HashMap(int capacity, float loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity cannot be less than 0");
        }
        this.loadFactor = loadFactor;
        array = (Entry<K, V>[]) new Entry[capacity];
    }

    /**
     * Entry is static since:
     * 1. It is very closely bonded to HashMap
     * 2. We probably need to access this class outside the HashMap class
     * @param <K> key
     * @param <V> value
     */
    public static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        private void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * check if two keys are equal
     * @param k1
     * @param k2
     * @return if equals return true; else return false;
     */
    public boolean equalKeys(K k1, K k2) {
        return Objects.equals(k1, k2);
    }

    /**
     * check if two values are equal
     * @param v1
     * @param v2
     * @return if equals return true; else return false;
     */
    public boolean equalValues(V v1, V v2) {
        return Objects.equals(v1, v2);
    }

    /**
     * insert/add a new Entry in the hashmap. if the new Entry key already exists in the hashmap, update the value to the new value.
     * @param key the key of the new Entry
     * @param value the value of the new Entry
     * @return if the new Entry key already exists in the hashmap, return the old value; if not, return null
     */
    public V put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> head = array[index];
        Entry<K, V> curr = head;
        while (curr != null) {
            if (equalKeys(curr.getKey(), key)) {
                V oldValue = curr.getValue();
                curr.setValue(value);
                return oldValue;
            }
            curr = curr.next;
        }
        Entry<K, V> newNode = new Entry<>(key, value);
        newNode.next = head;
        array[index] = newNode;
        size++;
        if (needRehashing()) {
            rehashing();
        }
        //return null if there was no mapping for the key.(A null return can also indicate that null was previously associated with the key.
        return null;
    }

    /**
     * remove the Entry with the specific key in the hashmap
     * @param key
     * @return if the key exists in the hashmap, return its value; if not, return return null;
     */
    public V remove(K key) {
        int index = getIndex(key);
        Entry<K, V> head = array[index];
        Entry<K, V> prev = null;
        while (head != null) {
            if (equalKeys(head.getKey(), key)) {
               if (prev != null) {
                   prev.next = head.next;
               } else {
                   array[index] = head.next;
               }
               size--;
               return head.getValue();
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    /**
     * check if the provided key is exists in the hashmap
     * @param key
     * @return if exists, return true; else return false;
     */
    public boolean containsKey(K key) {
        int index = getIndex(key);
        Entry<K, V> head = array[index];
        while (head != null) {
            //head.getKey() and key all possible to be null
            if (head.getKey().equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * check if the value provided is associated with any key.
     * @param value
     * @return if yes, return true; else return false;
     */
    public boolean containsValue(V value) {
        for (Entry<K, V> head : array) {
            while (head != null) {
                //head.getValue() and value all possible to be null
                if (equalValues(head.getValue(), value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    /**
     * get the value of an Entry with the provided key
     * @param key
     * @return if the Entry exists, return the value; else return null.
     */
    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> head = array[index];
        while (head != null) {
            if (equalKeys(head.getKey(), key)) {
                return head.getValue();
            }
            head = head.next;
        }
        return null;
    }

    /**
     *
     * @return size of the hashmap
     */
    public int size() {
        return size;
    }

    /**
     *
     * @return if the hashmap is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * clear all the Entries in the hashmap, and reset the size to 0
     */
    public void clear() {
        Arrays.fill(this.array, null);
        size = 0;
    }


    /**
     * @param key
     * @return return the hashcode of the key
     */
    private int hash(K key) {
        //null key always in the 0 index
        if (key == null) {
            return 0;
        }
        return key.hashCode() & 0x7FFFFFFF;//make sure the hashcode is non-negative
    }

    /**
     * get the index of the target Entry in the underlying array.
     * @param key
     * @return the index in array
     */
    private int getIndex(K key) {
        return hash(key) % array.length;
    }

    /**
     * check if rehashing is needed
     * @return if needed, return true; else return false
     */
    private boolean needRehashing() {
        return (size + 0.0f) / array.length >= loadFactor;
    }

    /**
     * rehashing process
     */
    private void rehashing() {
        Entry<K, V>[] oldArray = array;
        array = (Entry<K, V>[]) new Entry[SCALE_FACTOR * array.length];
        for (Entry<K, V> head : oldArray) {
            while (head != null) {
                Entry<K, V> nextNode = head.next;
                int index = hash(head.getKey());
                head.next = array[index];
                array[index] = head;
                head = nextNode;
            }
        }
    }
}
