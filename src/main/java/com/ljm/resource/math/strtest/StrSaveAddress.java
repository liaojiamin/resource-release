package com.ljm.resource.math.strtest;

/**
 * @author liaojiamin
 * @Date:Created in 17:49 2020/11/3
 */
public class StrSaveAddress {
    private static final String str3 = "hello world";

    public static void strMain() {
        String str1 = new String("hello world");
        String str2 = "hello world".toUpperCase();

        String str4 = str3;
        String str5 = str3;
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
        System.out.println(str4 == str5);
    }

    public static void main(String[] args) {
        strMain();
    }
}
