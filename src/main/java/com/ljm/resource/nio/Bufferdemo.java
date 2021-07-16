package com.ljm.resource.nio;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author liaojiamin
 * @Date:Created in 16:13 2020/4/8
 */
public class Bufferdemo {
    public static void main(String[] args) throws IOException {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            ByteBuffer b = ByteBuffer.allocateDirect(1000);
        }
        System.out.println(System.currentTimeMillis() - begin);
    }
}
