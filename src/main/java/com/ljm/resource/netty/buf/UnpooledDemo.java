package com.ljm.resource.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by jiamin5 on 2022/11/21.
 */
public class UnpooledDemo {

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            byteBuf.writeByte(i);
        }
        for (int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.getByte(i));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(byteBuf.readByte());
        }
    }
}
