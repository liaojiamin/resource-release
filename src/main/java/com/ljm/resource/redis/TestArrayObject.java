package com.ljm.resource.redis;

import redis.clients.jedis.Jedis;

/**
 * @author liaojiamin
 * @Date:Created in 13:11 2020/11/14
 */
public class TestArrayObject {
    public static void main(String[] args) {
        Jedis jedis = JedisPoolTools.getJedis();
        jedis.del("books");
        for (int i = 0; i < 512; i++) {
            jedis.hset("books", String.valueOf(i), String.valueOf(i));
        }
        System.out.println(jedis.objectEncoding("books"));
        jedis.hset("books", "hello", "512");
        System.out.println(jedis.objectEncoding("books"));
    }
}
