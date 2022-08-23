package com.ljm.resource.netty.io.nio;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liaojiamin
 * @Date:Created in 10:00 2022/8/18
 */
public class NIOServerFileCopyDemo {
    private static final String resultFilePath = "E:\\learn\\问题汇总\\test_md_1.md";
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 6666));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(resultFilePath));
        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("server socket wait 1 second, no client collection");
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("客户端连接后，注册的Selectionkey数量" + selector.keys().size());
            System.out.println("selectionKeys 数量 = " + selector.selectedKeys().size());
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("Server socket connectioned with client socketChannel: " + socketChannel.hashCode());
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接后，注册的Selectionkey数量" + selector.keys().size());
                    System.out.println("客户端连接后，注册在监听的注册过的通道中有哪些事件" + selector.selectedKeys().size());
                }else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.read(byteBuffer)> 0){
                        bufferedOutputStream.write(byteBuffer.array());
                        bufferedOutputStream.flush();
                        System.out.println("client write byteBuffer length" + byteBuffer.array().length);
                        System.out.println("server get data from client: " + new String(byteBuffer.array()));
                        byteBuffer.clear();
                    }
                }
                key = null;
            }
        }
    }
}
