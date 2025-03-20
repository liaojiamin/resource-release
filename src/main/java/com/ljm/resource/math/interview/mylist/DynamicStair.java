package com.ljm.resource.math.interview.mylist;

/**
 * Created by jiamin5 on 2023/3/11.
 * f(n) = f(n-1) + f(n-2)
 * n > 2
 * n=1 : f(1) = 1
 * n=2 : f(2) = 2
 * n=3 : f(3) = 3
 * f(3) = f(1) + f(2) = 1 + 2 = 3;
 */
public class DynamicStair {

    public static void main(String[] args) {
        DynamicStair dynamicStair = new DynamicStair();
        System.out.println(dynamicStair.countStair(7));
        System.out.println(dynamicStair.countStair_2(7));
    }

    public int countStair_2(int stairNum){
        if(stairNum == 0){
            return 0;
        }
        if(stairNum == 1 || stairNum == 2){
            return 1;
        }
        int next = 2;
        int nextAndNext = 1;
        int last = 1;
        for (int i = 2; i < stairNum; i++) {
            last = next + nextAndNext;
            nextAndNext = next;
            next = last;
        }
        return last;
    }

    public int countStair(int stairNum){
        if(stairNum == 0){
            return 0;
        }
        if(stairNum == 1 || stairNum == 2){
            return stairNum;
        }
        return countStair(stairNum - 1) + countStair(stairNum - 2);
    }
}
