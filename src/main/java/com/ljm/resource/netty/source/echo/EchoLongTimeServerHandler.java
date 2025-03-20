package com.ljm.resource.netty.source.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.Callable;

/**
 * Created by jiamin5 on 2023/3/1.
 */
public class EchoLongTimeServerHandler extends ChannelInboundHandlerAdapter {

    static final EventExecutorGroup group = new DefaultEventExecutorGroup(16);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final Object msgCop = msg;
        final ChannelHandlerContext ctxCop = ctx;
        group.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                ByteBuf buf = (ByteBuf) msgCop;
                byte[] req = new byte[buf.readableBytes()];
                buf.readBytes(req);
                String body = new String(req, "UTF-8");
                Thread.sleep(10*1000);
                System.out.println(body + "  "+ Thread.currentThread().getName());
                String reqString = "hello i am server~~~";
                ByteBuf resp = Unpooled.copiedBuffer(reqString.getBytes());
                ctxCop.writeAndFlush(resp);
                return null;
            }
        });
        System.out.println("go on~~");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
