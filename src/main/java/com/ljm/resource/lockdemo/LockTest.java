package com.ljm.resource.lockdemo;

/**
 * @author liaojiamin
 * @Date:Created in 10:26 2020/4/23
 */
public class LockTest {
    private static final int CREATE = 20000000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < CREATE; i++) {
            createStringBuffer("Java", "Performance");
        }
        long bufferCost = System.currentTimeMillis() - start;
        System.out.println("createStringBUffer: "+ bufferCost + "ms");

    }

    public static String createStringBuffer(String str1, String str2){
        StringBuffer sb = new StringBuffer();
        sb.append(str1);
        sb.append(str2);
        return sb.toString();
    }
}
