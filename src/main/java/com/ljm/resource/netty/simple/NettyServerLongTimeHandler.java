package com.ljm.resource.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 耗时读取测试
 * Created by jiamin5 on 2022/9/19.
 */
public class NettyServerLongTimeHandler extends ChannelInboundHandlerAdapter {

    //读取数据事件，可以读取客户端发送来的信息
    /**
     *
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("client return message: "+ buf.toString(CharsetUtil.UTF_8));

        Thread.sleep(10 * 1000);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端 喵喵2", CharsetUtil.UTF_8));
        System.out.println("go on ...");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hellow, client~ miaomiao", CharsetUtil.UTF_8));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
