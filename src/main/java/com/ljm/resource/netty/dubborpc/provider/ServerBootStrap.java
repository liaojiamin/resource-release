package com.ljm.resource.netty.dubborpc.provider;

import com.ljm.resource.netty.dubborpc.netty.NettyServer;

/**
 * Created by jiamin5 on 2023/3/1.
 */
public class ServerBootStrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 7000);
    }
}
