package com.ljm.resource.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * Created by jiamin5 on 2023/3/1.
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String result;//返回结果
    private String para;//客户端调用方法时，传入的参数

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive 开始执行");
        context = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead 被调用");
        result = msg.toString();
        notify();
    }


    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call1 开始执行");
        context.writeAndFlush(para);
        //等待channelRead 方法获取到服务器的结果后，唤醒
        wait();
        System.out.println("call2 开始执行");
        return result;
    }

    void setPara(String para){
        this.para = para;
    }
}
