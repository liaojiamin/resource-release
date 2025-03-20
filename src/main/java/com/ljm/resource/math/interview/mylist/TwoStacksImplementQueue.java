package com.ljm.resource.math.interview.mylist;

import java.util.Stack;

/**
 * Created by jiamin5 on 2023/3/28.
 * 两个栈实现队列
 */
public class TwoStacksImplementQueue {
    public static class TwoStacksQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public TwoStacksQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }


        /**
         * push 中数据导入pop中
         */
        public void pushToPop() {
            if (!popStack.isEmpty()) {
                return;
            }
            if (pushStack.isEmpty()) {
                return;
            }
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }

        public void add(int value) {
            pushStack.push(value);
        }

        public int poll() throws Exception {
            pushToPop();
            if (popStack.isEmpty()) {
                throw new Exception("数据空");
            }
            return popStack.pop();
        }

        public int peek() throws Exception {
            pushToPop();
            if (popStack.isEmpty()) {
                throw new Exception("数据空");
            }
            return popStack.peek();
        }
    }

    public static void main(String[] args) throws Exception {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
