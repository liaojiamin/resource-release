package com.ljm.resource.opt;

/**
 * @author liaojiamin
 * @Date:Created in 15:33 2020/3/25
 */
public class StringOpt {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str1 == str3.intern());
    }
}
