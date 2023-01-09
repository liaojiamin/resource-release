package com.ljm.resource.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created by jiamin5 on 2022/11/23.
 */
public class NettyByteBuf02 {

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world!哈哈哈", Charset.forName("UTF-8"));

        if (byteBuf.hasArray()) {
            byte[] bytes = byteBuf.array();
            System.out.println(new String(bytes, Charset.forName("UTF-8")));

            System.out.println("byteBuf： " + byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.maxCapacity());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());

            System.out.println(byteBuf.getByte(0));
            System.out.println(byteBuf.readableBytes());
            System.out.println(byteBuf.getCharSequence(0, 4, Charset.forName("UTF-8")));
        }
    }
}
