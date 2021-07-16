package com.ljm.resource.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaojiamin
 * @Date:Created in 9:55 2020/5/28
 */
public class RedisWithReentrantLock {
    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<Map<String, Integer>>();

    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis){
        this.jedis = jedis;
    }

    private boolean _lock(String key){
        return jedis.set(key, "", "nx", "ex", 5L) != null;
    }

    private void unLock(String key){
        jedis.del(key);
    }

    private Map<String, Integer> currentLockers(){
        Map<String, Integer> refs = lockers.get();
        if(refs != null){
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    public boolean lock(String key){
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if(refCnt != null){
            refs.put(key, refCnt + 1);
            return true;
        }
        boolean ok = this._lock(key);
        if(!ok){
            return false;
        }
        refs.put(key, 1);
        return true;
    }

    public  boolean unlock(String key){
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if(refCnt == null){
            return false;
        }
        refCnt -= 1;
        if(refCnt > 0){
            refs.put(key, refCnt);
        }else {
            refs.remove(key);
            this.unLock(key);
        }
        return true;
    }

    public static void main(String[] args) {
        Jedis jedis = JedisPoolTools.getJedis();
        RedisWithReentrantLock redisLock = new RedisWithReentrantLock(jedis);
        System.out.println(redisLock.lock("codehole"));
        System.out.println(redisLock.lock("codehole"));
        System.out.println(redisLock.unlock("codehole"));
        System.out.println(redisLock.unlock("codehole"));
    }




}
