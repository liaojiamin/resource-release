package com.ljm.resource.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.List;

/**
 * @author liaojiamin
 * @Date:Created in 12:01 2020/11/14
 */
public class WatchTest {
    public static void main(String[] args) {
        Jedis jedis = JedisPoolTools.getJedis();
        jedis.watch("books");
        jedis.set("books" , "100");
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.incr("books");
        pipe.incr("books");
        try {
            Response<List<Object>> value = pipe.exec();
            pipe.close();
            System.out.println(value.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
