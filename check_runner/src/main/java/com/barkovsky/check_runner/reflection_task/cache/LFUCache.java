package com.barkovsky.check_runner.reflection_task.cache;

import com.barkovsky.check_runner.reflection_task.cache.api.ICache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LFUCache<K, V> implements ICache<K, V> {

    private final int capacity;
    private int size;
    private int minFrequency;
    private final Map<K, Node> content;
    private final Map<Integer, DoubleLinkedList> frequencyBucket;

    public LFUCache(int capacity) {
        this.capacity = capacity;

        this.content = new HashMap<>(capacity);
        this.frequencyBucket = new HashMap<>();
    }

    @Override
    public void add(K key, V value) {
        if (this.content.containsKey(key)) {
            Node node = content.get(key);
            node.value = value;
            updateNode(node);
        } else {

            this.size++;

            if (this.size > this.capacity) {
                DoubleLinkedList minFreqList = this.frequencyBucket.get(this.minFrequency);
                this.content.remove(minFreqList.tail.prev.key);
                minFreqList.remove(minFreqList.tail.prev);
                this.size--;
            }

            this.minFrequency = 1;
            Node newNode = new Node(key, value);

            DoubleLinkedList curList = frequencyBucket.getOrDefault(1, new DoubleLinkedList());
            curList.add(newNode);
            this.frequencyBucket.put(1, curList);
            this.content.put(key, newNode);
        }
    }

    @Override
    public Optional<V> get(K key) {
        Node node = this.content.get(key);
        if (node == null) {
            return Optional.empty();
        }
        updateNode(node);
        return Optional.of(node.value);
    }

    @Override
    public void delete(K key) {
        Node node = this.content.get(key);
        if (node != null) {
            int frequency = node.frequency;
            DoubleLinkedList list = this.frequencyBucket.get(frequency);
            list.remove(node);
            this.content.remove(key);
            this.size--;
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    @Override
    public void clear() {
        this.content.clear();
    }

    private void updateNode(Node node) {
        int nodeFreq = node.frequency;
        DoubleLinkedList curList = frequencyBucket.get(nodeFreq);
        curList.remove(node);

        if (nodeFreq == minFrequency && curList.listSize == 0) {
            minFrequency++;
        }

        node.frequency++;

        DoubleLinkedList newList = frequencyBucket.getOrDefault(node.frequency, new DoubleLinkedList());
        newList.add(node);
        frequencyBucket.put(node.frequency, newList);
    }

    private class Node {
        private final K key;
        private V value;
        private int frequency;
        private Node prev;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    private class DoubleLinkedList {
        private int listSize;
        private final Node head;
        private final Node tail;

        private DoubleLinkedList() {
            this.head = new Node(null, null);
            this.tail = new Node(null, null);
            head.next = tail;
            tail.prev = head;
        }

        private void add(Node curNode) {
            Node nextNode = head.next;
            curNode.next = nextNode;
            curNode.prev = head;
            head.next = curNode;
            nextNode.prev = curNode;
            listSize++;
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            listSize--;
        }

    }
}
