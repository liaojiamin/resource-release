package com.ljm.resource.weiss;

/**
 * 求给定字符串的全排列
 * @author liaojiamin
 * @Date:Created in 16:21 2020/9/14
 */
public class MyPermute {
    public static void main(String[] args) {
        String s = "abc";
        char[] strArray = s.toCharArray();
        permute(strArray, 0, strArray.length -1);
    }

    public static void permute(char[] list, int low, int high){
        int i;
        if (low == high) {
            String cout = "";
            for (i = 0; i <= high; i++)
                cout += list[i];
            System.out.println(cout);
        } else {
            for (i = low; i <= high; i++) {
                char temp = list[low];
                list[low] = list[i];
                list[i] = temp;
                permute(list, low + 1, high);
                temp = list[low];
                list[low] = list[i];
                list[i] = temp;
            }
        }
    }
}
