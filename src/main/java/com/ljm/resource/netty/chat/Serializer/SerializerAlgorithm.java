package com.ljm.resource.netty.chat.Serializer;

/**
 * @author liaojiamin
 * @Date:Created in 18:07 2021/11/2
 */
public interface SerializerAlgorithm {
    /**
     * json 序列化标识
     */
    byte JSON = 1;

    /**
     * protostuff 序列化标识
     */
    byte PROTO_STUFF = 2;

    /**
     * kryo 序列化标识
     */
    byte KRYO = 3;
}
