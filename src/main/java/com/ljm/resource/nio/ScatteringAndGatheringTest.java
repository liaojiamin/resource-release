package com.ljm.resource.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author liaojiamin
 * @Date:Created in 14:18 2022/8/17
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(1024);
        byteBuffers[1] = ByteBuffer.allocate(1024);

        SocketChannel socketChannel = serverSocketChannel.accept();
        int byteRead = 0;
        while (true){
           while (socketChannel.read(byteBuffers) > 0){
               System.out.println("byteReader + " + byteRead++);
               Arrays.asList(byteBuffers).stream().map(buffer -> "pisotion = "+ buffer.position() + ", limit = "+buffer.limit()).forEach(System.out::println);
           }
           Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
           System.out.println(byteBuffers.toString());
           long byteWirte = 0;
           while (socketChannel.write(byteBuffers) > 0){
               byteWirte++;
           }
           Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
            System.out.println("byteRead = " + byteRead + ", byteWrite = " + byteWirte + ", messagelength = " + 999);

        }
    }
}
