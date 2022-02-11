package com.ljm.resource.netty.chat.Serializer.impl;

import com.ljm.resource.netty.chat.Serializer.Serializer;
import com.ljm.resource.netty.chat.Serializer.SerializerAlgorithm;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;


/**
 * @author liaojiamin
 * @Date:Created in 10:32 2021/11/3
 */
public class ProtoStuffSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.PROTO_STUFF;
    }

    @Override
    public byte[] serialize(Object object) {
        Schema<Object> schema = (Schema<Object>)RuntimeSchema .getSchema(object.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(512);
        return ProtostuffIOUtil.toByteArray(object, schema, buffer);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        T t = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, t, schema);
        return t;
    }
}
