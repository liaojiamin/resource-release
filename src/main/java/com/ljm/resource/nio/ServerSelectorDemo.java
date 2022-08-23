package com.ljm.resource.nio;


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
 * @Date:Created in 15:11 2022/7/29
 */
public class ServerSelectorDemo {

    public void selector() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //注册监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            //获取所有key集合
            Set selectedKeys = selector.selectedKeys();
            Iterator iterator = selectedKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                if((selectionKey.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                    //接受服务端请求
                    SocketChannel socketChannel = serverSocketChannel1.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                }else if ((selectionKey.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    while (true){
                        byteBuffer.clear();
                        //读取数据
                        int n = socketChannel.read(byteBuffer);

                        if(n <= 0){
                            break;
                        }
                        byteBuffer.flip();
                    }
                    iterator.remove();
                }
            }
        }

    }
}
