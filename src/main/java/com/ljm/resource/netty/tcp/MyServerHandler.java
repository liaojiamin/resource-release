package com.ljm.resource.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by jiamin5 on 2023/2/14.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        //将buffer转城字符串
        String message = new String(buffer, Charset.forName("UTF-8"));
        System.out.println("服务器端接收到数据： "+ ctx.channel().remoteAddress() + " msg: "+ message + "\t");
        System.out.println("服务器端接受到的消息量= "+ (++count));

        //服务器回送数据给客户端,随机一个数字
        ByteBuf responseBytebuf = Unpooled.copiedBuffer(UUID.randomUUID().toString() + " ", Charset.forName("UTF-8"));
        ctx.channel().writeAndFlush(responseBytebuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
