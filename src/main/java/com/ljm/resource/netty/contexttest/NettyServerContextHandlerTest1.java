package com.ljm.resource.netty.contexttest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liaojiamin
 * @Date:Created in 16:44 2023/2/23
 */
public class NettyServerContextHandlerTest1 extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("test1 get msg: "+ msg );
        ctx.fireChannelRead(msg);
//        ctx.pipeline().fireChannelRead(msg);
//        ctx.read();
//        ctx.pipeline().fireChannelRead(new String("this msg send for test1"));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("执行 test1 中channelReadComplete");
//        ctx.fireChannelRead(new String("this msg send for test1"));
//        ctx.pipeline().fireChannelRead(new String("this msg send for test1"));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " is online test1");
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+ "is offline~");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("is handlerAdded");
    }
}
