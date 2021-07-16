package com.ljm.resource.math.myStack;

import java.util.Arrays;

/**
 * @author liaojiamin
 * @Date:Created in 18:07 2020/12/2
 */
public class MyStack<E> {
    private Object[] elementData;
    private Integer top;
    private Integer capacity;
    private Integer position;

    /**
     * 数组，指针，位置，容量信息 初始化
     * */
    public MyStack() {
        elementData = new Object[10];
        top = -1;
        capacity = elementData.length - 1;
        position = 0;
    }

    /**
     * 扩容
     * */
    public void reSize() {
        if (position == capacity) {
            Integer newCapacity = capacity + (capacity >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
            capacity = newCapacity;
        }
    }

    /**
     * 添加推入数据
     * */
    public E push(E e) {
        reSize();
        top = position;
        elementData[position++] = e;
        return e;
    }

    /**
     * 删除，推出数据
     * */
    public E pop(){
        if(top == -1){
            return null;
        }
        E e = (E)elementData[top--];
        position --;
        elementData[position] = 0;
        return e;
    }

    /**
     * 查看栈顶元素
     * */
    public E getTop(){
        return (E)elementData[top];
    }

    /**
     * 判断是否为空
     * */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 当前栈存储数据个数
     * */
    public int size(){
        return position;
    }

    public void printMyStack(){
        if(top != -1){
            for (Integer i = top; i >= 0; i--) {
                System.out.print(elementData[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<Integer>();
        for (int i = 0; i < 100; i++) {
            myStack.push(i);
        }
        Integer result = 0;
        while (result != null){
            result = myStack.pop();
            System.out.println(result);
        }
    }
}
