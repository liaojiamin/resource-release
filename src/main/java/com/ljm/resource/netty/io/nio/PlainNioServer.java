package com.ljm.resource.netty.io.nio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liaojiamin
 * @Date:Created in 17:27 2021/7/27
 */
public class PlainNioServer {
    public static void main(String[] args) {
        try {
            //服务初始化
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            //设置为非阻塞
            serverSocket.configureBlocking(false);
            //绑定端口
            serverSocket.bind(new InetSocketAddress("localhost", 9999));
            //注册OP_ACCEPT事件（即监听该事件，如果有客户端发来连接请求，则该键在select()后被选中）
            Selector selector = Selector.open();
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            Calendar ca = Calendar.getInstance();
            System.out.println("服务端开启了");
            System.out.println("=========================================================");
            //轮询服务
            while (true) {
                //选择准备好的事件
                selector.select();
                //已选择的键集
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                //处理已选择键集事件
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    //处理掉后将键移除，避免重复消费(因为下次选择后，还在已选择键集中)
                    it.remove();
                    //处理连接请求
                    if (key.isAcceptable()) {
                        //处理请求
                        SocketChannel socket = serverSocket.accept();
                        socket.configureBlocking(false);
                        //注册read，监听客户端发送的消息
                        socket.register(selector, SelectionKey.OP_READ);
                        //keys为所有键，除掉serverSocket注册的键就是已连接socketChannel的数量
                        String message = "连接成功 你是第" + (selector.keys().size() - 1) + "个用户";
                        //向客户端发送消息
                        socket.write(ByteBuffer.wrap(message.getBytes()));
                        InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
                        //输出客户端地址
                        System.out.println(ca.getTime() + "\t" + address.getHostString() +
                                ":" + address.getPort() + "\t");
                        System.out.println("客戶端已连接");
                        System.out.println("=========================================================");
                    }

                    if (key.isReadable()) {
                        SocketChannel socket = (SocketChannel) key.channel();
                        InetSocketAddress address = (InetSocketAddress) socket.getRemoteAddress();
                        System.out.println(ca.getTime() + "\t" + address.getHostString() +
                                ":" + address.getPort() + "\t");
                        ByteBuffer bf = ByteBuffer.allocate(1024 * 4);
                        int len = 0;
                        byte[] res = new byte[1024 * 4];
                        //捕获异常，因为在客户端关闭后会发送FIN报文，会触发read事件，但连接已关闭,此时read()会产生异常
                        try {
                            while ((len = socket.read(bf)) != 0) {
                                bf.flip();
                                bf.get(res, 0, len);
                                System.out.println(new String(res, 0, len));
                                bf.clear();
                            }
                            System.out.println("=========================================================");
                        } catch (IOException e) {
                            //客户端关闭了
                            key.cancel();
                            socket.close();
                            System.out.println("客戶端已断开");
                            System.out.println("=========================================================");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器异常，即将关闭..........");
            System.out.println("=========================================================");
        }
    }

//    public static void main(String[] args) throws IOException {
//        PlainNioServer plainNioServer = new PlainNioServer();
//        plainNioServer.server(9999);
//    }
//    public void server(int port) throws IOException {
//        // 实例化ServerSocketChannel
//        ServerSocketChannel serverChannel = ServerSocketChannel.open();
//        serverChannel.configureBlocking(false);
//        ServerSocket ssocket = serverChannel.socket();
//        InetSocketAddress address = new InetSocketAddress(port);
//        ssocket.bind(address);
//        Selector select = Selector.open();
//        serverChannel.register(select, SelectionKey.OP_ACCEPT);
//        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
//        for (;;){
//            try {
//                select.select();
//            }catch (Exception e){
//                e.printStackTrace();
//                break;
//            }
//            Set<SelectionKey> readyKeys = select.selectedKeys();
//            Iterator<SelectionKey> iterator = readyKeys.iterator();
//            while (iterator.hasNext()){
//                SelectionKey key = iterator.next();
//                iterator.remove();
//                try {
//                    if (key.isAcceptable()) {
//                        ServerSocketChannel server = (ServerSocketChannel)key.channel();
//                        SocketChannel client  = server.accept();
//                        client .configureBlocking(false);
//                        client .register(select, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
//                        System.out.println("Accepted connection from " + client);
//                    }
//                    if(key.isWritable()){
//                        SocketChannel client = (SocketChannel) key.channel();
//                        ByteBuffer buffer = (ByteBuffer) key.attachment();
//                        while (buffer.hasRemaining()){
//                            if(client.write(buffer) == 0){
//                                break;
//                            }
//                        }
//                        client.close();;
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                    key.cancel();
//                    key.channel().close();
//                }
//            }
//        }
//
//    }
}
