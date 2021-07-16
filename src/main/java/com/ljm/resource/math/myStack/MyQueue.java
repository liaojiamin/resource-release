package com.ljm.resource.math.myStack;

/**
 * @author liaojiamin
 * @Date:Created in 11:37 2021/3/9
 */
public class MyQueue {
    private MyStack<Integer> myStack1;
    private MyStack<Integer> myStack2;
    private boolean isAdd;

    public MyQueue(){
        myStack1 = new MyStack<>();
        myStack2 = new MyStack<>();
        isAdd = false;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public MyStack<Integer> getMyStack1() {
        return myStack1;
    }

    public void setMyStack1(MyStack<Integer> myStack1) {
        this.myStack1 = myStack1;
    }

    public MyStack<Integer> getMyStack2() {
        return myStack2;
    }

    public void setMyStack2(MyStack<Integer> myStack2) {
        this.myStack2 = myStack2;
    }
}
