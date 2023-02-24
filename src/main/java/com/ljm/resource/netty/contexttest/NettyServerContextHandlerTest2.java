package com.ljm.resource.netty.contexttest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liaojiamin
 * @Date:Created in 16:44 2023/2/23
 */
public class NettyServerContextHandlerTest2 extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("test2 get msg: "+ msg);
        ctx.read();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("执行 test2 中channelReadComplete");
        ctx.fireChannelRead(new String("this msg send for test2"));
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
