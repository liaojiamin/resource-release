package com.ljm.resource.netty.chat.protocal.codec;

import com.ljm.resource.netty.chat.Serializer.Serializer;
import com.ljm.resource.netty.chat.Serializer.impl.JSONSerializer;
import com.ljm.resource.netty.chat.Serializer.impl.KryoSerializer;
import com.ljm.resource.netty.chat.Serializer.impl.ProtoStuffSerializer;
import com.ljm.resource.netty.chat.protocal.commond.Commond;
import com.ljm.resource.netty.chat.protocal.packet.MsgPacket;
import com.ljm.resource.netty.chat.protocal.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Packet编解码类
 * 通信协议
 * 魔数0x12345678(4字节)|版本号(1字节)|序列化算法(1字节)|指令(1字节)|数据长度(4字节)|数据(N字节)
 * @author liaojiamin
 * @Date:Created in 18:02 2021/11/2
 */
public class PacketCodeC {
    public static final PacketCodeC INSTANCE = new PacketCodeC(new JSONSerializer());
    private static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Commond.MSG, MsgPacket.class);

        serializerMap = new HashMap<>();
        Serializer jsonSerializer = new JSONSerializer();
        serializerMap.put(jsonSerializer.getSerializerAlgorithm(), jsonSerializer);
        Serializer kryoSerializer = new KryoSerializer();
        serializerMap.put(kryoSerializer.getSerializerAlgorithm(), kryoSerializer);
        Serializer protoStuffSerializer = new ProtoStuffSerializer();
        serializerMap.put(protoStuffSerializer.getSerializerAlgorithm(), protoStuffSerializer);
    }

    private Serializer serializer;

    public PacketCodeC(Serializer serializer) {
        this.serializer = serializer;
    }

    public ByteBuf encode(Packet packet){
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 序列化 Java 对象
        byte[] bytes = serializer.serialize(packet);
        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        // 跳过 magic number
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        //指令
        byte command = byteBuf.readByte();

        //数据包长度
        int length = byteBuf.readInt();
        byte[]  bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends  Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if(requestType != null && serializer != null){
            return serializer.deserialize(requestType,  bytes);
        }
        return null;
    }

    public Serializer getSerializer(byte serializeAlgorithm){
        return serializerMap.get(serializeAlgorithm);
    }

    public Class<? extends Packet> getRequestType(byte command){
        return packetTypeMap.get(command);
    }

}

































