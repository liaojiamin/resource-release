package com.ljm.resource.netty.contexttest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liaojiamin
 * @Date:Created in 17:43 2023/2/23
 */
public class NettyCientContextHandlerTest extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
