package com.ljm.resource.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by jiamin5 on 2023/2/13.
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * ctx 上下文对象
     * in 入站ByteBuf
     * out list集合，将解码后的数据传给下一个Handler
     *
     * */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() >= 8){
            list.add(byteBuf.readLong());
        }
    }
}
