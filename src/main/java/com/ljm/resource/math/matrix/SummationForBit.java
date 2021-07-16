package com.ljm.resource.math.matrix;

/**
 * @author liaojiamin
 * @Date:Created in 16:39 2021/7/8
 */
public class SummationForBit {
    public static void main(String[] args) {
        System.out.println(sumForBit(12,13));
    }

    /**
     * 利用位运算做加法
     *
     * */
    public static int sumForBit(int num1, int num2){
        int sum = 0;
        int curry = 0;
        do{
            sum = num1 ^ num2;
            curry = (num1 & num2)<<1;
            num1 = sum;
            num2 = curry;
        }while (curry != 0);
        return sum;
    }
}
