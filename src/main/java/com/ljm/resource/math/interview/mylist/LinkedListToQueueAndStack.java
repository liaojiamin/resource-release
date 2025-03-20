package com.ljm.resource.math.interview.mylist;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jiamin5 on 2023/3/11.
 * 单链表 实现队列 与 栈
 */
public class LinkedListToQueueAndStack {

    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V v) {
            value = v;
            next = null;
        }
    }


    /**
     * 单向链表实现栈
     */
    public static class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size();
        }

        public void push(V value) {
            Node<V> thisNode = new Node<V>(value);
            if (head == null) {
                head = thisNode;
            } else {
                thisNode.next = head;
                head = thisNode;
            }
            size++;
        }

        public V pop() {
            Node<V> thisNode = null;
            if (head == null) {
                return null;
            }
            thisNode = head;
            head = head.next;
            size--;
            return thisNode.value;
        }

        public V peek() {
            if (head != null) {
                return head.value;
            }
            return null;
        }

    }

    /**
     * 单向链表实现队列
     */
    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private Integer size;


        public MyQueue() {
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

        // 添加到队列尾部
        public void offer(V value) {
            Node<V> node = new Node<V>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        //推出队列头元素
        public V poll() {
            Node<V> targetNode = null;
            if (head == null) {
                return null;
            } else {
                targetNode = head;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return targetNode.value;
        }

        //获取第一个元素值
        public V peek() {
            if (head != null) {
                return head.value;
            }
            return null;
        }
    }
}
