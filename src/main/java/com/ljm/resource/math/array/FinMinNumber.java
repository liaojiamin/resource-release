package com.ljm.resource.math.array;

/**
 * 将数组中所有数据合并成一个数，求出能排成的最小数
 *
 * @author liaojiamin
 * @Date:Created in 12:00 2021/6/8
 */
public class FinMinNumber {

    public static String pringMinNumber(int[] array) {
        if (array == null || array.length <= 0) {
            return "";
        }
        if (array.length == 1) {
            return String.valueOf(array[0]);
        }
        String[] str = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            str[i] = String.valueOf(array[i]);
        }
        quickSort(str, 0, str.length - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : str) {
            System.out.print(s +", ");
            stringBuilder.append(s);
        }
        System.out.println();
        return stringBuilder.toString();
    }

    /**
     * 快排法，按从小到大排序字符串，字符串大小规则自定义
     * */
    public static String[] quickSort(String[] str, Integer left, Integer right) {
        if (left < right) {
            Integer temp = quickSortSwap(str, left, right);
            quickSort(str, left, temp - 1);
            quickSort(str, temp + 1, right);
        }
        return str;
    }

    public static Integer quickSortSwap(String str[], Integer left, Integer right) {
        if (left < right) {
            String position = str[left];
            while (left < right){
                while (left < right && myCompareTO(str[right], position) > 0) {
                    right--;
                }
                if (left < right) {
                    str[left] = str[right];
                    left++;
                }
                while (left < right && myCompareTO(str[left], position) < 0) {
                    left++;
                }
                if (left < right) {
                    str[right] = str[left];
                    right--;
                }
            }
           str[left] = position;
        }
        return left;
    }

    /**
     * 字符串大小规则比较，
     * ab > ba => a>b
     * ab == ba => a==b
     * ab<ba =>  a<b
     * */
    public static Integer myCompareTO(String a, String b){
        String ab = a+b;
        String ba = b+a;
        return ab.compareTo(ba);
    }

    public static void main(String[] args) {
        int[] str_1 = {12, 34, 1, 34, 777, 33, 99, 86, 56, 9};
        System.out.println(pringMinNumber(str_1));
    }


}
