package com.ljm.resource.math.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jiamin5 on 2023/2/1.
 */
public class Permutation_2 {

    public static void main(String[] args) {
//        permutationArray("qqa");
        repeatStrPermutationArray("kms");
    }


    /**
     * 包含重复字符的字符串全排列
     * */
    public static void repeatStrPermutationArray(String str){
        if(str == null){
            return;
        }
        char[] strArray = str.toCharArray();
        List<String> strList = new ArrayList<>();
        repeatStrPermutationCharArray(strArray, 0,strList);
        Collections.sort(strList);
        for (String s : strList) {
            System.out.println(s);
        }
    }
    public static void repeatStrPermutationCharArray(char[] strArray, Integer start, List<String> strList){
        if(start == strArray.length -1){
            if(!strList.contains(String.valueOf(strArray))){
                strList.add(String.valueOf(strArray));
//                printChar(strArray);
            }
        }
        for (int i = start; i < strArray.length; i++) {
            char temp = strArray[i];
            strArray[i] = strArray[start];
            strArray[start] = temp;
            repeatStrPermutationCharArray(strArray, start +1, strList);
            temp = strArray[i];
            strArray[i] = strArray[start];
            strArray[start] = temp;
        }
    }

    /**
     * 不包含重复元素的字符串的全排列
     * */
    public static void permutationArray(String str) {
        char[] strArray = str.toCharArray();
        permutationCharArray(strArray, 0);
    }

    public static void permutationCharArray(char[] strArray, Integer start) {
        if (strArray == null || strArray.length <= 1 || start == strArray.length - 1) {
            printChar(strArray);
        }
        for (int i = start; i <= strArray.length - 1; i++) {
            char temp = strArray[i];
            strArray[i] = strArray[start];
            strArray[start] = temp;
            permutationCharArray(strArray, start + 1);
            temp = strArray[i];
            strArray[i] = strArray[start];
            strArray[start] = temp;
        }

    }

    public static void printChar(char[] strArray) {
        if (strArray == null || strArray.length <= 0) {
            return;
        }
        for (char c : strArray) {
            System.out.print(c + ",");
        }
        System.out.println();
    }
}
