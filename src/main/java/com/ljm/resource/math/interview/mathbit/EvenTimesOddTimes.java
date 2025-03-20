package com.ljm.resource.math.interview.mathbit;

/**
 * Created by jiamin5 on 2023/3/23.
 */
public class EvenTimesOddTimes {
    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr){
        if(arr == null || arr.length <= 0){
            return;
        }
        int temp = 0;
        for (int i : arr) {
            temp^=i;
        }
        System.out.println(temp);
    }

    // arr中，有两种数出现奇数次，找出这两
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^=i;
        }
        //得到结果中第一个1的数
        int rightOne = eor & (-eor);
        int getOne = 0;
        for (int i : arr) {
            //分组异或，第一个1位置相同的分同一组，统一异或，得到其中一个奇数
            if ((rightOne & i) != 0){
                getOne^=i;
            }
        }
        System.out.println(getOne + " ......." + (getOne ^ eor));

    }


    //第一个1 的位置
    public static int getLast1(int n){
        /**
         * n = 5
         * n                0101
         * ~n               1010
         * ~n+1             1011
         * n & ((~n) + 1)   0001
         * */
        return n & ((~n) + 1);
    }

    public static int bit1counts(int n) {
        if(n == 0){
            return 0;
        }
        int count = 0;
        while (n != 0){
            int k = getLast1(n);
            count++;
            n^=k;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(7 & -7);
        System.out.println(getLast1(7));
        int[] nums = {1,1,1,1,2,2,2,2,2,1,2};
        printOddTimesNum1(nums);
        System.out.println(bit1counts(7));
        int[] nums2 = {1,1,1,1,11,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5};
        printOddTimesNum2(nums2);

    }
}
