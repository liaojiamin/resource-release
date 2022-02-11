package com.ljm.resource.netty.chat.Serializer;

/**
 * 序列化方式定义
 * @author liaojiamin
 * @Date:Created in 18:05 2021/11/2
 */
public interface Serializer {

    /**
     * 序列化算法
     * */
    byte getSerializerAlgorithm();

    /**
     * java对象转二进制
     * */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
