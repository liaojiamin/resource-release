package com.ljm.resource.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 群聊服务器
 * @author liaojiamin
 * @Date:Created in 15:49 2022/8/22
 */
public class GroupChatService {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private Integer port = 6667;

    /**
     * 数据初始化
     */
    public GroupChatService() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        System.out.println("listen thread name "+ Thread.currentThread().getName());
        try {
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " 上线 ");
                        }
                        if (selectionKey.isReadable()) {
                            readData(selectionKey);
                        }
                        iterator.remove();
                    }
                }else {
//                    System.out.println("等待....");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭各种管道");
        }
    }

    /**
     * 读取数据
     */
    public void readData(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(byteBuffer);
            if (count > 0) {
                String msg = new String(byteBuffer.array());
                System.out.println("from 客户端： " + msg);
                sendInfoToOtherClients(msg, socketChannel);
            }
        } catch (IOException e) {
            try {
                System.out.println(socketChannel.getRemoteAddress()+ "离线了....");
                key.cancel();
                socketChannel.close();
            }catch (Exception e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 消息转发到其他客户端
     */
    public void sendInfoToOtherClients(String msg, SocketChannel socketChannel) {
        try {
            System.out.println("消息转发中.....");
            System.out.println("sendInfoToOtherClients thread name "+ Thread.currentThread().getName());
            for (SelectionKey selectionKey : selector.keys()) {
                Channel targetChannel = selectionKey.channel();
                if (targetChannel instanceof SocketChannel && targetChannel != socketChannel) {
                    SocketChannel targetSocketChannel = (SocketChannel) targetChannel;
                    targetSocketChannel.write(ByteBuffer.wrap(msg.getBytes()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatService groupChatService = new GroupChatService();
        groupChatService.listen();
    }
}
