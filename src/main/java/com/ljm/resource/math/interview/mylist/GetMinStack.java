package com.ljm.resource.math.interview.mylist;

import java.util.Stack;

/**
 * Created by jiamin5 on 2023/3/27.
 * 实现一个特殊功能的栈，在原有 栈 的基础上能实现获取最小值，并且每个操作都是O(1)
 */
public class GetMinStack {

    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1(){
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int newNum) {
           if(stackMin.isEmpty() || newNum <= this.getmin()){
               stackMin.push(newNum);
           }
           stackData.push(newNum);
        }

        public int pop() {
            if(stackData.isEmpty()){
                throw new RuntimeException("stack isEmpty");
            }
            int thisPop = stackData.pop();
            if(thisPop == this.getmin()){
                stackMin.pop();
            }
            return thisPop;
        }

        public int getmin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("stack isEmpty");
            }
            return stackMin.peek();
        }
    }

    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            stackData = new Stack<Integer>();
            stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (stackMin.isEmpty() || newNum < getmin()) {
                stackMin.push(newNum);
            } else {
                stackMin.push(stackMin.peek());
            }
            stackData.push(newNum);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            stackMin.pop();
            return stackData.pop();
        }

        public int getmin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getmin());
        stack1.push(4);
        System.out.println(stack1.getmin());
        stack1.push(1);
        System.out.println(stack1.getmin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getmin());

        System.out.println("=============");

        MyStack2 stack2 = new MyStack2();
        stack2.push(3);
        System.out.println(stack2.getmin());
        stack2.push(4);
        System.out.println(stack2.getmin());
        stack2.push(1);
        System.out.println(stack2.getmin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getmin());
    }
}
