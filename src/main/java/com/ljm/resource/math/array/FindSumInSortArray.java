package com.ljm.resource.math.array;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在有序数组中查找和为 s的两个数字
 * @author liaojiamin
 * @Date:Created in 17:38 2021/6/29
 */
public class FindSumInSortArray {

    public static void main(String[] args) {
//        System.out.println("310919c471ed565a9ba7df0fdd025b9c".hashCode());
//        System.out.println(Hashing.murmur3_128().newHasher().putString("feb67dff-7ef9-ea1d-1bfb-dfb06ef7e96b", StandardCharsets.UTF_8).hash().toString());
//        System.out.println((long)(Double.valueOf(60.00)* Double.valueOf(100)));
        int[] array = new int[]{1,2,3,7,8,11,14,16,17,18,23,47,59,67,79,83,222,344,556,778};
//        System.out.println(findSumNum(array, 25));
        int[] arrayAll = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14};
//        System.out.println(findAllSumList(arrayAll, 14));
//        System.out.println(findSumInList(array, 18));
        List<int[]> allCombom = findAllCombination(arrayAll, 9);
        for (int[] mySumNum : allCombom) {
            for (int i = 0; i < mySumNum.length; i++) {
                if(mySumNum[i] > 0){
                    System.out.print(mySumNum[i] + ",");
                }
            }
            System.out.println();
        }
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
     * 有序数组中找出所有和为s的数字的组合,
     * */
    public static List<int[]> findAllCombination(int[] array, int s){
        if(array == null || array.length <= 0){
            return Lists.newArrayList();
        }
        int positionBig = array.length - 1;

        List<int[]> allCombination = new ArrayList<>();
        while (array[positionBig] >= s){
            if(array[positionBig] == s){
                allCombination.add(new int[]{array[positionBig]});
            }
            positionBig--;
        }
        while (positionBig > 0){
            int positionSmall = positionBig - 1;
            int[] temp = new int[positionBig+1];
            temp[0] = array[positionBig];
            int tempPosition = 1;
            while (positionSmall >= 0){
                int sum = 0;
                for (int i=0;i<temp.length;i++){
                    sum+=temp[i];
                }
                sum += array[positionSmall];
                if(sum > s){
                    positionSmall --;
                    continue;
                }
                if(sum < s){
                    temp[tempPosition] = array[positionSmall];
                    tempPosition ++;
                    positionSmall--;
                    continue;
                }
                if(sum == s){
                    temp[tempPosition] = array[positionSmall];
                    allCombination.add(temp);
                    temp = new int[positionBig];
                    tempPosition = 0;
                    temp[tempPosition] = array[positionBig];
                    tempPosition ++;
                    positionSmall --;
                    continue;
                }
            }
            positionBig --;

        }
        return allCombination;
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
