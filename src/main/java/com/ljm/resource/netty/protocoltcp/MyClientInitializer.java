package com.ljm.resource.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by jiamin5 on 2023/2/14.
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new MyMessageEncoder()); //加入编码器
        channelPipeline.addLast(new MyMessageDecoder());//加入解码器
        channelPipeline.addLast(new MyClientHandler());
    }
}
