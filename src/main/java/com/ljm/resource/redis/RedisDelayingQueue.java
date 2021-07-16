package com.ljm.resource.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

/**
 * @author liaojiamin
 * @Date:Created in 11:26 2020/5/28
 */
public class RedisDelayingQueue<T> {
    static class TaskItem<T> {
        private String id;
        public T msg;
    }

    private Type TaskType = new TypeReference<TaskItem<T>>() {
    }.getType();
    private Jedis jedis;
    private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        TaskItem<T> task = new TaskItem<>();
        task.id = UUID.randomUUID().toString();
        task.msg = msg;
        String s = JSON.toJSONString(task);
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);
    }

    public void handleMsg(T msg) {
        System.out.println(msg);
    }

    public void loop() {
        while (!Thread.interrupted()) {
            //取出所有在key中score值在0 ~ 当前时间节点的所有所有value
            Set<String> values = jedis.zrangeByScore(queueKey, 1, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            String s = values.iterator().next();
            //zrem 移除redis中queuekey对应的成员s，成功
            if (jedis.zrem(queueKey, s) > 0) {
                //反序列化
                TaskItem<T> task = JSON.parseObject(s, TaskType);
                this.handleMsg(task.msg);
            }
        }
    }

    public static void main(String[] args) {
        Jedis jedis = JedisPoolTools.getJedis();
        RedisDelayingQueue<String> queue = new RedisDelayingQueue<>(jedis, "q-demo");
        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    queue.delay("coderhelo" + i);
                }
            }
        };
        Thread consumer = new Thread() {
            @Override
            public void run() {
                queue.loop();
            }
        };
        producer.start();
        consumer.start();
//        try {
//            producer.join();
//            Thread.sleep(6000);
//            consumer.interrupt();
//            consumer.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
