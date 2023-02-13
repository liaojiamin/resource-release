package com.ljm.resource.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by jiamin5 on 2023/2/13.
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        //入站Handler进行解码 MyByteToLongDecoder
        channelPipeline.addLast(new MyByteToLongDecoder());
        channelPipeline.addLast(new MyServerHandler());
    }
}
