package com.ljm.resource.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.List;

/**
 * @author liaojiamin
 * @Date:Created in 15:14 2020/5/29
 */
public class BloomTest {
    public static void main(String[] args) throws IOException {
        Jedis jedis = JedisPoolTools.getJedis();
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.incr("books");
        pipe.incr("books");
        Response<List<Object>> value = pipe.exec();
        pipe.close();
        System.out.println(value.get());
    }
}
