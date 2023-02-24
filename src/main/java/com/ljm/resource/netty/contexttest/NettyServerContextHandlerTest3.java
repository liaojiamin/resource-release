package com.ljm.resource.netty.contexttest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liaojiamin
 * @Date:Created in 16:44 2023/2/23
 */
public class NettyServerContextHandlerTest3 extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("test3 get msg: "+ msg);
        ctx.read();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("执行 test3 中channelReadComplete");
        ctx.writeAndFlush(new String("this msg send for test3"));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " is online");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+ "is offline~");
    }
}
