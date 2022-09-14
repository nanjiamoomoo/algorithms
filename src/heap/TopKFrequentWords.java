package heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {

    /**
     * Given a composition with different kinds of words, return a list of the top K most frequent words in the composition.
     * Assumption:
     * the composition is not null and is not guaranteed to be sorted
     * K >= 1 and K could be larger than the number of distinct words in the composition, in this case, just return all the distinct words
     *
     * Examples
     * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [“b”, “c”]
     * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 4 frequent words are [“b”, “c”, "a", "d"]
     * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5 frequent words are [“b”, “c”, "a", "d"]
     *
     * @param combo
     * @param k
     * @return a list of words ordered from most frequent one to least frequent one (the list could be of size K or smaller than K)
     */
    public String[] topKFrequentWords(String[] combo, int k) {
        /*
            Step1: find distinct words in the combo[] array and count the frequency of each word
                    We can use a Map<String, Integer>, Key is the word(String), Value is the count
            Step2: We can traverse the Map, and use a minHeap of size k (maybe smaller than k if k is larger than the number of distinct words) to
                    store current top K frequent words. The element in the heap is the entry<String, Integer>

            Step3: After we complete traverse the entire map, the heap should contain the top k frequent word. We poll element one by one and store in the final result
         */
        if (combo.length == 0) {
            return new String[0];
        }
        //step1:
        Map<String, Integer> map = new HashMap<>();
        for (String s : combo) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        //make sure k is smaller or equal to number of distinct words
        k = k > map.size() ? map.size() : k;

        //Step2:
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry1.getValue() - entry2.getValue();
            }
        });

        //traverse map
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }

        //step3:
        String[] res = new String[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = minHeap.poll().getKey();
        }
        return res;

        /*
            TC:
            Step1: O(n)
            Step2: In worst scenario, there are n distinct words. for k offer, the klogk, the rest n - k, offer: 2(n-k)logk
                    its bout (2n-k)logk
            Step3: klogk

            Total is n + 2nlogk = nlogk

            SC: O(n)
         */
    }
}
