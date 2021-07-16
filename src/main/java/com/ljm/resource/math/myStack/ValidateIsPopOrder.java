package com.ljm.resource.math.myStack;

/**
 * 存在栈A的入栈系列S，判断给出的序列B是否可能是A 的出序列，例如1,2,3,4,5 入栈，4,5,3,2,1 出栈
 * @author liaojiamin
 * @Date:Created in 16:54 2021/4/2
 */
public class ValidateIsPopOrder {

    public static boolean validateIsPopOrder(int[] orderPush, int[] orderPop){
        if(orderPush == null || orderPop == null){
            return false;
        }
        if(orderPush.length != orderPop.length){
            return false;
        }
        MyStack myStack = new MyStack();
        int length = orderPop.length;
        int pushPosition = 0;
        int popPosition = 0;
        while (pushPosition < length || popPosition < length){
            while (myStack.size() > 0 && (int)myStack.getTop() == orderPop[popPosition] && popPosition < length){
                myStack.pop();
                popPosition++;
            }
            if(pushPosition < length){
                myStack.push(orderPush[pushPosition]);
                pushPosition ++;
            }
            if(pushPosition == length && myStack != null && (int)myStack.getTop() != orderPop[popPosition]){
                return false;
            }
        }
        return !(myStack.size() != 0);
    }

    public static void main(String[] args) {
        int[] push = {1,2,3,4,5};
//        int[] pop = {4,5,3,2,1};
//        int[] pop = {3,2,1,5,4};
        int[] pop = {4,3,5,1,2};
        System.out.println(validateIsPopOrder(push, pop));

    }
}
