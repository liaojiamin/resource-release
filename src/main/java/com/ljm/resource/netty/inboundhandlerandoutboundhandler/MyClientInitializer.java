package com.ljm.resource.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import redis.clients.jedis.Pipeline;


/**
 * Created by jiamin5 on 2023/2/14.
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //加入一个出站handler对数据进行编码
        pipeline.addLast(new MyLongToByteEncoder());
        //加入一个自定义handler，处理业务
        pipeline.addLast(new MyClientHandler());
        System.out.println("处理器添加完成");
    }
}
