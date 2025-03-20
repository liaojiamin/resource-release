package com.ljm.resource.netty.dubborpc.netty;

import com.ljm.resource.netty.dubborpc.provider.HelloServiceImpl;
import com.ljm.resource.netty.dubborpc.publicinterface.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jiamin5 on 2023/3/1.
 */
public class NettyClient {
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static NettyClientHandler client;
    private int count = 0;

    public Object getBean(final Class<?> serviceClass, final String providerName) {

        HelloService helloService = (HelloService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{serviceClass},
//                (proxy, method, args) -> {
//                    System.out.println("（proxy， method， args）进入..." + (++count) + "次");
//                    if(client == null){
//                        initClient();
//                    }
//                    client.setPara(providerName + args[0]);
//                    return executor.submit(client).get();
//                }
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("（proxy， method， args）进入..." + (method + ", " + Arrays.toString(args))+ (++count) + "次");
                        if (client == null) {
                            initClient();
                        }
                        client.setPara(providerName + args[0]);
                        return executor.submit(client).get();
                    }
                }
        );
        return helloService;
    }

    private static void initClient() {
        client = new NettyClientHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(client);
                    }
                });

        try {
            bootstrap.connect("127.0.0.1", 7000).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
