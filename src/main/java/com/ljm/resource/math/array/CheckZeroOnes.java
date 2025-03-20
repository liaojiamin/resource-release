package com.ljm.resource.math.array;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by jiamin5 on 2023/3/4.
 * 1869. 哪种连续子字符串更长: https://leetcode.cn/problems/longer-contiguous-segments-of-ones-than-zeros/
 */
public class CheckZeroOnes {
    public static boolean checkZeroOnes(String s) {
        if(s == null || s.length() <= 0){
            return false;
        }
        int maxOne = 0;
        int maxZero = 0;
        int countOne = 0;
        int countZero = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1'){
                countOne++;
                countZero = 0;
                if(maxOne < countOne){
                    maxOne = countOne;
                }
            }else {
                countZero++;
                countOne=0;
                if(maxZero < countZero){
                    maxZero = countZero;
                }
            }
        }
        System.out.println(maxOne + " : "+ maxZero);
        return maxOne > maxZero;
    }

    public static void main(String[] args) {
        System.out.println(checkZeroOnes("101010110101110000110000000"));
    }

}
