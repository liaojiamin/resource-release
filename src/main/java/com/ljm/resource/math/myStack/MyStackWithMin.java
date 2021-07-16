package com.ljm.resource.math.myStack;

import java.util.Random;

/**
 * 包含min方法的栈实现
 * @author liaojiamin
 * @Date:Created in 16:02 2021/4/2
 */
public class MyStackWithMin {
    private static MyStack dataStack = new MyStack();
    private static MyStack minStack = new MyStack();

    public static void push(int num){
        if(dataStack.size() == 0 && minStack.size() == 0){
            dataStack.push(num);
            minStack.push(num);
        }else {
            dataStack.push(num);
            if((int)minStack.getTop() > num){
                minStack.push(num);
            }else {
                minStack.push(minStack.getTop());
            }
        }
    }

    public static int pop(){
        if(dataStack.size() == 0 || minStack.size() == 0){
            return Integer.MAX_VALUE;
        }
        minStack.pop();
        return (int)dataStack.pop();
    }

    public static int min(){
        if(minStack.size() == 0){
            return Integer.MAX_VALUE;
        }
        return (int)minStack.pop();
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int temp = random.nextInt(100);
            System.out.println(temp);
            push(temp);
        }
        System.out.println(min());
    }
}
