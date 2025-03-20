package com.ljm.resource.math.interview.mylist;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by jiamin5 on 2023/3/28.
 * 两个队列实现栈
 */
public class TwoQueueImplementStack {

    public static class TwoQueueStack{
        private Queue<Integer> pushQueue;
        private Queue<Integer> popQueue;

        public TwoQueueStack(){
            this.pushQueue = new LinkedList<>();
            this.popQueue = new LinkedList<>();
        }

        public void push(int value){
            pushQueue.offer(value);
        }

        public Integer poll(){
            if(pushQueue.isEmpty()){
                throw new RuntimeException("栈空");
            }
            while (pushQueue.size() > 1){
                popQueue.offer(pushQueue.poll());
            }
            int temp = pushQueue.poll();
            Queue<Integer> tempQueue = pushQueue;
            pushQueue = popQueue;
            popQueue = tempQueue;
            return temp;
        }

        public Integer peek(){
            if(pushQueue.isEmpty()){
                throw new RuntimeException("栈空");
            }
            while (pushQueue.size() > 1){
                popQueue.offer(pushQueue.poll());
            }
            int temp = pushQueue.peek();
            popQueue.offer(pushQueue.poll());
            Queue<Integer> tempQueue = pushQueue;
            pushQueue = popQueue;
            popQueue = tempQueue;
            return temp;
        }

        public Boolean isEmpty(){
            return pushQueue.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack myStack = new TwoQueueStack();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
