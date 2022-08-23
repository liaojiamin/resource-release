package com.ljm.resource.netty.io.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author liaojiamin
 * @Date:Created in 10:13 2022/8/18
 */
public class NIOClientFileCopyDemo {
    private static final String filePath = "E:\\learn\\问题汇总\\test_md.md";

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("can not collection server 127.0.0.1:6666");
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        byte[] bytes = new byte[1024];
        while (bufferedInputStream.read(bytes) >= 0) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            socketChannel.write(byteBuffer);
            System.out.println("info: " + new String(byteBuffer.array()) + " size: " + byteBuffer.array().length);
        }
        socketChannel.close();
        bufferedInputStream.close();
    }
}
