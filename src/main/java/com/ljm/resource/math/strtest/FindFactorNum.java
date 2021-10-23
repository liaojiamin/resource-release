package com.ljm.resource.math.strtest;

/**
 * 查询数字n的所有因数
 * @author liaojiamin
 * @Date:Created in 10:05 2021/7/26
 */
public class FindFactorNum {
    public static void main(String[] args) {
        int[] arrayNum = findFactor(1679);
        for (int i = 0; i < arrayNum.length; i++) {
            if(arrayNum[i] != 0){
                System.out.print(arrayNum[i]+" ");
            }
        }

    }

    /**
     * 考虑正数情况
     * */
    public static int[] findFactor(int n){
        if(n == 0){
            return null;
        }
        int[] arrayNum = new int[n];
        int position = 0;
        for (int i = 1; i < n; i++) {
            if(n % i == 0){
                arrayNum[position++] = i;
            }
        }
        return arrayNum;
    }

}
