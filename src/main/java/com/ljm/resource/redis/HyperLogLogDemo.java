package com.ljm.resource.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by jiamin5 on 2022/11/28.
 */
public class HyperLogLogDemo {

    public static void main(String[] args) {
        Jedis jedis = JedisPoolTools.getJedis();
        String user_one = "user1";
        Integer user1 = 0;
        Integer user2= 0;
        String user_two = "user2";
        for (int i = 0; i < 10000; i++) {
            if(i%2 == 0){
                jedis.pfadd("countUv"+user_one, user_one + i);
                user1++;
            }else {
                jedis.pfadd("countUv"+user_two, user_two + i);
                user2++;
            }
        }
        System.out.println("user1UV： " + jedis.pfcount("countUv"+user_one));
        System.out.println("user1Uv_real： "+ user1);

        System.out.println("user2UV： " + jedis.pfcount("countUv"+user_two));
        System.out.println("user2Uv_real： "+ user2);
    }
}
