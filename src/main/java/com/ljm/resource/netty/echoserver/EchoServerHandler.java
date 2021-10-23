package com.ljm.resource.netty.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Echo服务响应传入消息
 * @author liaojiamin
 * @Date:Created in 10:56 2021/7/27
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 处理接收的数据
     * 此处只是做一次中转
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf in = (ByteBuf) msg;
        String updateMsg = " server rocket update!";
        in.writeBytes(updateMsg.getBytes());
        System.out.println(Thread.currentThread().getName() + " Server received: " + in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 一次情况下调用
     * 此处只做了关闭连接来通知远程节点发生了错误
     * */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
