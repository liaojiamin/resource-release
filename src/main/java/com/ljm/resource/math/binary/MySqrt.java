package com.ljm.resource.math.binary;

/**
 * Created by jiamin5 on 2023/3/7.
 */
public class MySqrt {

    public static void main(String[] args) {
        MySqrt mySqrt = new MySqrt();
        System.out.println(mySqrt.mySqrt(2));
    }
    public int mySqrt(int x) {
        if(x <= 1){
            return x;
        }
        return mySqrt(x, 1,  x-1);
    }

    public int mySqrt(int x, int start, int end){
        if(end - start == 1 || end == start){
            return start;
        }
        //找star ～ end中间这个数
        int middle = start + (end - start)/2;
        long thisMiddleValue = (long) middle * middle;
        if(thisMiddleValue == x){
            return middle;
        }
        if (thisMiddleValue < x){
            return mySqrt(x, middle, end);
        }
        return mySqrt(x, start, middle);
    }
}
