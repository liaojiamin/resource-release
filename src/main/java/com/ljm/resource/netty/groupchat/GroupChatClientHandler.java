package com.ljm.resource.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by jiamin5 on 2022/11/29.
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
