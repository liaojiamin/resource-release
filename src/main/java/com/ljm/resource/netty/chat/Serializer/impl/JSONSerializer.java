package com.ljm.resource.netty.chat.Serializer.impl;

import com.alibaba.fastjson.JSON;
import com.ljm.resource.netty.chat.Serializer.Serializer;
import com.ljm.resource.netty.chat.Serializer.SerializerAlgorithm;

/**
 * @author liaojiamin
 * @Date:Created in 18:08 2021/11/2
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
