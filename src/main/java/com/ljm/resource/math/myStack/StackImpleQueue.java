package com.ljm.resource.math.myStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 利用两个栈实现队列
 * @author liaojiamin
 * @Date:Created in 11:35 2021/3/9
 */
public class StackImpleQueue {
    private MyQueue myQueue = new MyQueue();


    /**
     * 添加元素到队列
     * */
    public void append(Integer item){
        if(isEmpty()){
            myQueue.getMyStack1().push(item);
            return;
        }
        //上次是append，无需change
        boolean needChange = !myQueue.isAdd();
        MyStack<Integer> notEmpty = change(needChange);
        notEmpty.push(item);

    }

    /**
     * 从队列中删除位置
     * */
    public Integer del(){
        if(isEmpty()){
            return -1;
        }
        //上次是add则需要change
        MyStack<Integer> notEmpty = change(myQueue.isAdd());
        return notEmpty.pop();
    }

    /**
     * 队列是否为空
     */
    public boolean isEmpty(){
        return myQueue.getMyStack1().isEmpty() && myQueue.getMyStack2().isEmpty();
    }

    /**
     * 将非空栈中数据弹出并添加到另一个栈
     * */
    public MyStack<Integer> change(boolean needChange){
        if(isEmpty()){
            return myQueue.getMyStack1();
        }
        MyStack<Integer> emptyStack = myQueue.getMyStack1().isEmpty() ? myQueue.getMyStack1() : myQueue.getMyStack2();
        MyStack<Integer> notEmptyStack = myQueue.getMyStack1().isEmpty() ?  myQueue.getMyStack2() : myQueue.getMyStack1();
        if(needChange){
            while (!notEmptyStack.isEmpty()){
                emptyStack.push(notEmptyStack.pop());
            }
            //切换操作标记位
            myQueue.setAdd(!myQueue.isAdd());
            return emptyStack;
        }
        return notEmptyStack;
    }

    public static void main(String[] args) {
        StackImpleQueue stackImpleQueue = new StackImpleQueue();
//        for (int i = 0; i < 100; i++) {
//            stackImpleQueue.append(i);
//        }
//        while (!stackImpleQueue.isEmpty()){
//            System.out.println(stackImpleQueue.del());
//        }
//        stackImpleQueue.append(1);
//        stackImpleQueue.append(2);
//        stackImpleQueue.append(3);
//        System.out.println(stackImpleQueue.del());
//        System.out.println(stackImpleQueue.del());
//        stackImpleQueue.append(4);
//        System.out.println(stackImpleQueue.del());
//        System.out.println(stackImpleQueue.del());
        List<String> list = new ArrayList<>();
        list.add("14");
        System.out.println("14".matches("[0-19]"));
        String temp = String.join(",", list.stream()
                .filter(str -> str != null && str.matches("[0-15]")).collect(Collectors.toList()));
        System.out.println(temp);
    }

}
