package com.ljm.resource.netty.chat.server;

import com.ljm.resource.netty.chat.common.MsgConstant;
import com.ljm.resource.netty.chat.common.MsgCounter;
import com.ljm.resource.netty.chat.common.MsgRepository;
import com.ljm.resource.netty.chat.protocal.codec.PacketCodeC;
import com.ljm.resource.netty.chat.protocal.packet.MsgPacket;
import com.ljm.resource.netty.chat.protocal.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Optional;

/**
 * @author liaojiamin
 * @Date:Created in 11:53 2021/11/3
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("CLIENT: " + getRemoteAddress(ctx) + " 接入连接");
        // 往channel map中添加channel信息
        ZhangServer.putChannel(getHostString(ctx), ctx.channel());
    }

    public static String getRemoteAddress(ChannelHandlerContext ctx) {
        return Optional.ofNullable(ctx.channel().remoteAddress().toString()).orElse("").replace("/", "");
    }

    public static String getHostString(ChannelHandlerContext ctx) {
        String address = getRemoteAddress(ctx);
        return address.substring(0, address.indexOf(":"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf requestByteBuf = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
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
                break;
            }
            case MsgConstant.MSG_SESSION_TWO: {
                MsgCounter.count();
                printMsg(msgPacket);
                sendMsg(ctx, MsgConstant.MSG_SESSION_TWO);
                break;
            }
            case MsgConstant.MSG_SESSION_THREE: {
                MsgCounter.count();
                printMsg(msgPacket);
                sendMsg(ctx, MsgConstant.MSG_SESSION_THREE);
                break;
            }
            default:
                break;
        }

    }

    public void sendMsg(ChannelHandlerContext ctx, Integer sessionId) {
        MsgPacket liMsgPacket = MsgRepository.getInstance().getZhangMsgPacket(sessionId);
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(liMsgPacket);
        ctx.writeAndFlush(byteBuf);
    }

    public void printMsg(MsgPacket packet) {
//        System.out.println("李大爷说：【" + packet.getSession() + ":" + packet.getContent() + "】");
    }

}
