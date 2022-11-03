package com.ljm.resource.netty.httpDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * Created by jiamin5 on 2022/10/17.
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("对应的Channel + "+ ctx.channel()
                + " pipeline="+ ctx.channel().pipeline()
                + " 通过pipeline 获取Channel"+ ctx.pipeline().channel());
        System.out.println("当前ctx的handler=" + ctx.handler());

        if(msg instanceof HttpRequest){
            System.out.println("ctx类型="+ ctx.getClass());
            System.out.println("pipeline hashCode"+ ctx.pipeline().hashCode()
                    + " TestHttpServerHandler hash=" + this.hashCode());
            System.out.println("msg 类型+"+ msg.getClass());
            System.out.println("客户端地址"+ctx.channel().remoteAddress());

            //获取
            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 favicon.ico, 不做响应");
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("Hello, i am server", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        }
    }
}
