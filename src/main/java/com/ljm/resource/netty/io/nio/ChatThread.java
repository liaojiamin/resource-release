package com.ljm.resource.netty.io.nio;

import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author liaojiamin
 * @Date:Created in 17:47 2021/7/27
 */
public class ChatThread extends Thread {

    private Selector selector;
    private SocketChannel socket;

    public ChatThread(Selector selector, SocketChannel socket) {
        super();
        this.selector = selector;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //等待连接建立
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您要发送给服务端的消息");
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            try {
                //用户已输入，注册写事件，将输入的消息发送给客户端
                socket.register(selector, SelectionKey.OP_WRITE, ByteBuffer.wrap(s.getBytes()));
                //唤醒之前因为监听OP_READ而阻塞的select()
                selector.wakeup();
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }
    }
}

