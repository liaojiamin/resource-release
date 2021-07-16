package com.ljm.resource.redis;

/**
 * @author liaojiamin
 * @Date:Created in 15:37 2020/5/28
 */
public class AscIIDemo {
    public static void main(String[] args) {
        String str = "hello";
        for (int i = 0; i < str.length(); i++) {
            System.out.println(Byte.valueOf((byte)str.charAt(i)) + " : "+ str.charAt(i) );
        }
    }
}
