package com.ljm.resource.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author liaojiamin
 * @Date:Created in 16:03 2022/8/22
 */
public class GroupChatClient {
    private SocketChannel socketChannel;
    private Selector selector;
    private Integer port = 6667;
    private String HOST = "127.0.0.1";
    private String userName;

    /**
     * 初始化
     * */
    public GroupChatClient() throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, port));
        selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(userName + " is ok");

    }

    public void sendInfo(String info){
        info = userName + " say "+ info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readInfo(){
        try{
           int readChannels = selector.select();
           if(readChannels > 0){
               Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
               while (iterator.hasNext()){
                   SelectionKey selectionKey = iterator.next();
                   if(selectionKey.isReadable()){
                       SocketChannel readSocketChannel = (SocketChannel) selectionKey.channel();
                       ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                       readSocketChannel.read(byteBuffer);
                       String msg = new String(byteBuffer.array());
                       System.out.println("读取消息： "+ msg.trim());
                   }
                   iterator.remove();
               }
           }else {
//               System.out.println("没有可用通道.....");
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatClient groupChatClient = new GroupChatClient();
        new Thread(){
            @Override
            public void run(){
                while (true){
                    groupChatClient.readInfo();
                    try {
                        Thread.currentThread().sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.next();
            groupChatClient.sendInfo(s);
        }
    }
}
