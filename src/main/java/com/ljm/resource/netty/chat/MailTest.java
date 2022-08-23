package com.ljm.resource.netty.chat;

import com.ljm.resource.netty.chat.client.LiClient;
import com.ljm.resource.netty.chat.common.MsgConstant;
import com.ljm.resource.netty.chat.common.MsgRepository;
import com.ljm.resource.netty.chat.protocal.codec.PacketCodeC;
import com.ljm.resource.netty.chat.protocal.packet.MsgPacket;
import com.ljm.resource.netty.chat.server.ZhangServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import static com.ljm.resource.netty.chat.client.LiClient.*;
/**
 * @author liaojiamin
 * @Date:Created in 14:08 2021/11/3
 */
public class MailTest {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = ZhangServer.bootstrap();
        ChannelFuture serverChannelFuture = ZhangServer.bind(serverBootstrap, PORT);

        Bootstrap clientBootstrap = LiClient.bootstrap();
        ChannelFuture clientChannelFuture = LiClient.connect(clientBootstrap, HOST, PORT, MAX_RETRY);
        serverChannelFuture.sync().await();
        clientChannelFuture.sync().await();
        Thread.sleep(500);
        for (int i = 0; i < MsgConstant.COUNT_LEVEL_3; i++) {
            MsgPacket one = MsgRepository.getInstance().getZhangMsgPacket(MsgConstant.MSG_SESSION_ONE);
            MsgPacket two = MsgRepository.getInstance().getliMsgPacket(MsgConstant.MSG_SESSION_TWO);
            MsgPacket three = MsgRepository.getInstance().getliMsgPacket(MsgConstant.MSG_SESSION_THREE);
            sendMsg(ZhangServer.getChannel(LiClient.HOST), one);
            sendMsg(clientChannelFuture.channel(), two);
            sendMsg(clientChannelFuture.channel(), three);

        }
    }

    public static void sendMsg(Channel channel, MsgPacket packet){
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(packet);
        channel.writeAndFlush(byteBuf);
    }
}
