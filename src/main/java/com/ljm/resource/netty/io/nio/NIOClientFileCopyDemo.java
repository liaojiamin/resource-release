package com.ljm.resource.netty.io.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author liaojiamin
 * @Date:Created in 10:13 2022/8/18
 */
public class NIOClientFileCopyDemo {
    private static final String filePath = "/Users/jiamin/Desktop/《斗破苍穹》.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("can not collection server 127.0.0.1:6666");
            }
        }
        FileChannel fileChannel = new FileInputStream(filePath).getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int count = 0;
        while (fileChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            System.out.println("info: " + new String(byteBuffer.array()) + " size: " + byteBuffer.array().length);
            count++;
            byteBuffer.clear();
        }
        System.out.println("last version :"+ count);
    }
}
