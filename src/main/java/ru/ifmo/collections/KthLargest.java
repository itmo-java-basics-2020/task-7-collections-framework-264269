package ru.ifmo.collections;

import java.util.Map;
import java.util.TreeMap;

/**
 * Design a class to find the kth largest element in a stream. k is from 1 to numbers.length.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Constructor accepts an integer k and an integer array numbers, which contains initial elements from the stream.
 * For each call to the method KthLargest.add(), return the element representing the kth largest element in the stream.
 */
public class KthLargest {
    private int k;
    private TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public KthLargest(int k, int[] numbers) {
        this.k = k;
        for (int number :
                numbers) {
            treeMap.put(number, 1);
        }
    }

    public int add(int val) {
        Integer prev = treeMap.get(val);
        if (prev != null) {
            treeMap.put(val, prev + 1);
        } else {
            treeMap.put(val, 1);
        }

        var it = treeMap.descendingMap();
        int count = 0;

        for (Map.Entry<Integer, Integer> entry : it.entrySet()) {
            count += entry.getValue();
            if (count >= k) {
                return entry.getKey();
            }
        }

        throw new IllegalArgumentException("There are less than " + k + " elements");
    }
}