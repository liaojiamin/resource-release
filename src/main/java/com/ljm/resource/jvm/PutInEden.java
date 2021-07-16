package com.ljm.resource.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaojiamin
 * @Date:Created in 16:22 2020/5/8
 */
public class PutInEden {
    public static void main(String[] args) throws InterruptedException {
        Map<Object,Object> map = new HashMap<>();
        byte[] b1;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            b1 = new byte[100];
            map.put(System.nanoTime(), b1);
            Thread.sleep(100);
        }
    }
}
