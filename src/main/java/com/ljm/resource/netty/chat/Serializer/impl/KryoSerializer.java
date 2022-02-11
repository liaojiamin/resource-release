package com.ljm.resource.netty.chat.Serializer.impl;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.ljm.resource.netty.chat.Serializer.Serializer;
import com.ljm.resource.netty.chat.Serializer.SerializerAlgorithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.function.Supplier;

/**
 * @author liaojiamin
 * @Date:Created in 10:00 2021/11/3
 */
public class KryoSerializer implements Serializer {
    private static final ThreadLocal<Kryo> kryoLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        return kryo;
    });

    /**
     * 获得当前线程的 Kryo 实例
     *
     * @return 当前线程的 Kryo 实例
     */
    public static Kryo getInstance() {
        return kryoLocal.get();
    }

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.KRYO;
    }

    @Override
    public byte[] serialize(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        Kryo kryo = getInstance();
        kryo.writeClassAndObject(output, object);
        output.flush();
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        Kryo kryo = getInstance();
        return (T) kryo.readClassAndObject(input);
    }
}
