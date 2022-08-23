package com.ljm.resource.netty.chat.client;

import com.ljm.resource.netty.chat.common.MsgConstant;
import com.ljm.resource.netty.chat.common.MsgCounter;
import com.ljm.resource.netty.chat.common.MsgRepository;
import com.ljm.resource.netty.chat.protocal.codec.PacketCodeC;
import com.ljm.resource.netty.chat.protocal.packet.MsgPacket;
import com.ljm.resource.netty.chat.protocal.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author liaojiamin
 * @Date:Created in 17:52 2021/11/2
 */
public class Clienthandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;
        //解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
        //判断是否是消息请求数据报
        if (!(packet instanceof MsgPacket)) {
            return;
        }
        MsgPacket msgPacket = (MsgPacket) packet;
        Integer session = msgPacket.getSession();
        switch (session) {
            case MsgConstant
                    .MSG_SESSION_ONE: {
                MsgCounter.count();
                printMsg(msgPacket);
                sendMsg(ctx, MsgConstant.MSG_SESSION_ONE);
                break;
            }
            case MsgConstant.MSG_SESSION_TWO:
            case MsgConstant.MSG_SESSION_THREE: {
                MsgCounter.count();
                printMsg(msgPacket);
                break;
            }
            default:
                break;
        }
    }

    private void sendMsg(ChannelHandlerContext ctx, Integer sessionId) {
        MsgPacket liMsgPacket = MsgRepository.getInstance().getliMsgPacket(sessionId);
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(liMsgPacket);
        ctx.writeAndFlush(byteBuf);
    }

    private void printMsg(MsgPacket packet) {
//        System.out.println("张大爷说：【" + packet.getSession() + ":" + packet.getContent() + "】");
    }
}
