package com.ljm.resource.netty.shangguigu;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liaojiamin
 * @Date:Created in 16:18 2022/10/11
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建 bossGroup workerGroup两个线程组
        //bossGroup只处理链接请求，真正和客户端业务处理的交给workerGroup完成
        //两个线程组都是无限循环，
        //bossGroup和workerGroup含有的子线程NioEventLoop的个数默认是 实际CPU核*2
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class) //使用NioSocketChannel 作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)// 设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动连接状态
                    .handler(null) //handler 方法是给bossGroup添加handler
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道初始化对象(匿名对象), childHandler给workerGroup 添加handler
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("客户SocketChannel hashCode="+ ch.hashCode());
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println(".... 服务器 is ready");
            ChannelFuture cf = bootstrap.bind(6668).sync();
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(cf.isSuccess()){
                        System.out.println("监听端口6668 成功");
                    }else {
                        System.out.println("监听端口6668 成功");
                    }
                }
            });
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
