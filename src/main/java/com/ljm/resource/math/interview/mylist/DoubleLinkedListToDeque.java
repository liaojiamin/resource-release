package com.ljm.resource.math.interview.mylist;

/**
 * Created by jiamin5 on 2023/3/11.
 */
public class DoubleLinkedListToDeque {
    public static class Node<V> {
        public V value;
        public Node<V> last;
        public Node<V> next;

        public Node(V v) {
            value = v;
            last = null;
            next = null;
        }
    }


    public static class MyDeque<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyDeque() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void pushHead(V value) {
            Node<V> newNode = new Node<V>(value);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                head.last = newNode;
                newNode.next = head;
                newNode.last = null;
                head = newNode;
            }
            size++;
        }

        public void pushTail(V value) {
            Node<V> newNode = new Node<V>(value);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail;
                newNode.next = null;
                tail = newNode;
            }
            size++;
        }

        public V pollHead() {
            Node<V> thisNode = head;
            if (head == null) {
                return null;
            }
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
            }
            size--;
            return thisNode.value;
        }

        public V pollTail() {
            Node<V> thisNode = tail;
            if (head == null) {
                return null;
            }
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
            }
            size--;
            return tail.value;
        }

        public V peekHead() {
            if (head != null) {
                return head.value;
            }
            return null;
        }

        public V peekTail() {
            if (tail != null) {
                return tail.value;
            }
            return null;
        }

    }
}
