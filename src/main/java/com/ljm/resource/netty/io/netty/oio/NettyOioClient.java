package com.ljm.resource.netty.io.netty.oio;

import com.ljm.resource.netty.echoclient.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;

/**
 * @author liaojiamin
 * @Date:Created in 10:28 2021/8/10
 */
public class NettyOioClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "1234"));

    public static void main(String[] args) throws SSLException, InterruptedException {
        EventLoopGroup group = new OioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(OioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(HOST, PORT))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            System.out.println("EchoClient.main ServerBootstrap配置启动完成");
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }

    }
}
