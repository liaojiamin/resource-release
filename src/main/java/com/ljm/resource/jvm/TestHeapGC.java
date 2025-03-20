package com.ljm.resource.jvm;

/**
 * @author liaojiamin
 * @Date:Created in 18:19 2020/4/28
 */
public class TestHeapGC {
    public static void main(String[] args) {
        byte[] b1 = new byte[1024*1024/2];
        byte[] b2 = new byte[1024*1024*19];
        b2 = null;
        b2 = new byte[1024*1024*16];
//        System.gc();
    }
}
