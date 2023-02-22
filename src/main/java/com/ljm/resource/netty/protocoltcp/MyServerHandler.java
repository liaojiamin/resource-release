package com.ljm.resource.netty.protocoltcp;

import com.ljm.resource.maintest.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by jiamin5 on 2023/2/14.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        //接收到数据并处理
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println();
        System.out.println("服务器接收到细腻如下：");
        System.out.println("长度= "+ len);
        System.out.println("内容= "+ new String(content, Charset.forName("UTF-8")));
        System.out.println("服务器接收到消息包数量= "+ (++count));

        String responseContent = UUID.randomUUID().toString();
        int responseLen = responseContent.length();
        byte[] responseContent2 = responseContent.getBytes("UTF-8");
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(responseContent2);
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
