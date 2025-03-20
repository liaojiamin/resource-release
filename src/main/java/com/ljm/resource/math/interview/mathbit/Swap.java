package com.ljm.resource.math.interview.mathbit;

/**
 * Created by jiamin5 on 2023/3/23.
 * 不用额外变量交换数据
 */
public class Swap {
    public static void swap(int[] arr, int a, int b){
        if(a == b){
            return;
        }
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b]; //a ^ b ^ b = a
        arr[a] = arr[a] ^ arr[b]; // a ^ b ^ a = b
    }

    public static void main(String[] args) {


//        int a = 16;
//        int b = 603;
//
//        System.out.println(a);
//        System.out.println(b);
//
//
//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;
//
//
//        System.out.println(a);
//        System.out.println(b);




        int[] arr = {3,1,100};

        int i = 0;
        int j = 1;

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        System.out.println(arr[i] + " , " + arr[j]);


//        System.out.println(arr[0]);
//        System.out.println(arr[2]);
//
//        swap(arr, 0, 1);
//
//        System.out.println(arr[0]);
//        System.out.println(arr[2]);
    }
}
