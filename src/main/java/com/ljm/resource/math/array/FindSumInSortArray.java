package com.ljm.resource.math.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 在有序数组中查找和为 s的两个数字
 * @author liaojiamin
 * @Date:Created in 17:38 2021/6/29
 */
public class FindSumInSortArray {

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,7,8,11,14,16,17,18,23,47,59,67,79,83,222,344,556,778};
        System.out.println(findSumNum(array, 25));
        int[] arrayAll = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        System.out.println(findAllSumList(arrayAll, 15));
        System.out.println(findSumInList(array, 18));
    }

    /**
     * 非有序数组中找出和为s的两个数
     * */
    public static boolean findSumInList(int[] array, int s){
        if(array == null || array.length <= 0){
            return false;
        }
        boolean exists = false;
        Map<Integer, Integer> sumKeyValue = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            Integer key = array[i];
            if(sumKeyValue.containsKey(s - key)){
                System.out.println(key +" + "+ (s-key) +" = "+ s);
                exists = true;
            }
            sumKeyValue.put(key, key);
        }
        return  exists;
    }

    /**
     * 找出所有和为s的连续正数序列
     * */
    public static boolean findAllSumList(int[] array, int s){
        if(array == null || array.length <= 0){
            return false;
        }
        boolean exists = false;
        int positionBig = array.length -1;
        int positionSmall = 0;
        while (array[positionBig] > s){
            positionBig --;
        }
        positionSmall = positionBig-1;
        while (positionBig > 0){
            Integer sum = 0;
            for (int i = positionSmall; i <= positionBig; i++) {
                sum+=array[i];
            }
            if(sum == s){
                System.out.println(array[positionSmall] + " ~ "+ array[positionBig] +" = "+ s);
                exists = true;
                positionBig--;
                positionSmall = positionBig-1;
                continue;
            }
            if(sum < s){
                positionSmall--;
                if(positionSmall < 0){
                    return exists;
                }
                continue;
            }
            if(sum > s){
                positionBig --;
                continue;
            }
        }
        return exists;
    }

    /**
     * 找出递增排序数组中的两个数字，使得这两个数字的和为 s
     * */
    public static boolean findSumNum(int[] array, int s){
        if(array == null || array.length <= 0){
            return false;
        }
        int positionBig = array.length -1;
        int positionSmall = 0;
        while (array[positionBig] + array[positionSmall] != s){
            if(positionBig < positionSmall){
                return false;
            }
            if(array[positionBig] + array[positionSmall] > s){
                positionBig --;
            }
            if(array[positionBig] + array[positionSmall] < s){
                positionSmall++;
            }
        }
        System.out.println(array[positionSmall] + " + "+ array[positionBig] +" = "+ s);
        return true;
    }
}
