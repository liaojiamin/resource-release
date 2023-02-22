package com.ljm.resource.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import redis.clients.jedis.Pipeline;

/**
 * Created by jiamin5 on 2023/2/14.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("收到服务器ip= "+ ctx.channel().remoteAddress());
        System.out.println("收到服务器消息："+ msg);
    }

    //重写channelActive发送数据
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("myClientHandler 发送数据");
        ctx.writeAndFlush(123456L);

        //分析
        //abcdabcdabcdabcd 16个字节
        //处理器钱一个handler是myLongToByteEncode
        //myLongToByteEncode 的父类是 MessageToByteEncoder
        //MessageToByteEncoder 中write方法，当传入的数据类型下与定义的数据类型不匹配时候，跳过encoder方法
//        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
    }

}
