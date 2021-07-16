package com.ljm.resource.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author liaojiamin
 * @Date:Created in 9:57 2020/5/28
 */
public class JedisPoolTools {
    public static Jedis getJedis() {
        //创建连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(25);
        config.setMaxIdle(20);
        config.setMinIdle(5);
        JedisPool pool = new JedisPool(config, "10.51.7.27", 6379);
        Jedis jedis = pool.getResource();
        //登录，如果没有设置密码这段可以省略
        //jedis.auth("1234");
        //选择DB0数据库
        jedis.select(0);
        return jedis;
    }

    public static void main(String[] args) {
        getJedis();
    }
}
