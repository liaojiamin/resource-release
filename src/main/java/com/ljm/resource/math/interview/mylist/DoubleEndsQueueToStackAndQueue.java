package com.ljm.resource.math.interview.mylist;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by jiamin5 on 2023/3/27.
 *  用双向链表实现 队列 以及 栈
 */
public class DoubleEndsQueueToStackAndQueue {

    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T value) {
            Node<T> node = new Node<T>(value);
            if (head == null || tail == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.last = node;
                head = node;
            }
        }

        public void addFromBottom(T value) {
            Node<T> node = new Node<T>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.last = tail;
                tail.next = node;
                tail = node;
            }
        }

        public Node<T> popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> positionNode = head;
            head = head.next;
            if (head != null) {
                head.last = null;
            }
            positionNode.next = null;
            return positionNode;
        }

        public Node<T> popFromBottom() {
            if (tail == null) {
                return null;
            }
            Node<T> positionNode = tail;
            tail = tail.last;
            if (tail != null) {
                tail.next = null;
            }
            positionNode.last = null;
            return positionNode;
        }

        public Boolean isEmpty() {
            return head == null && tail == null;
        }
    }


    public static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead().value;
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;
        public MyQueue(){
            queue = new DoubleEndsQueue();
        }

        public void push(T value){
            queue.addFromHead(value);
        }

        public T poll(){
            return queue.popFromBottom().value;
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
