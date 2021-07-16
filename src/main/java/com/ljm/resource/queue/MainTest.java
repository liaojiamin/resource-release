package com.ljm.resource.queue;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liaojiamin
 * @Date:Created in 10:05 2020/4/16
 */
public class MainTest {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(100,
                200 , 0L, TimeUnit.SECONDS,
                new PriorityBlockingQueue<Runnable>());
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new MyThread("testThreadPoolExecutor3_" + Integer.toString((999-i))));
        }
    }
}
