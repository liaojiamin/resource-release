package com.ljm.resource.math.singleton;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author liaojiamin
 * @Date:Created in 14:44 2020/10/27
 */
public class TestSingletonRunnableMain {
    private Boolean lock;

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        int num = 100;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        final Set<String> set = Collections.synchronizedSet(new HashSet<String>());
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("nameThreadFactory-01").build();
        ExecutorService executorService = new ThreadPoolExecutor(100, 100, 1,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), nameThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < num; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                        SingletonThree  singletonThree = SingletonThree.getInstance();
                        set.add(singletonThree.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(2000);
        System.out.println("in more thread get singleton");
        for (String s : set) {
            System.out.println(s);
        }
        executorService.shutdown();

    }
}
