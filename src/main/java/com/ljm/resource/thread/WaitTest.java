package com.ljm.resource.thread;

import lombok.Synchronized;

/**
 * Created by jiamin5 on 2023/4/7.
 */
public class WaitTest {

    public static void main(String[] args) throws InterruptedException {
        WaitTest waitTest = new WaitTest();
        Thread t1 = new Thread(() -> waitTest.sync(), "t1");
        Thread t2 = new Thread(() -> waitTest.sync(), "t2");
        t1.start();
        t2.start();
        Thread.sleep(12000);
        synchronized (WaitTest.class) {
            WaitTest.class.notifyAll();
        }
    }

    public static  void sync() {
        try {
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    WaitTest.class.wait();
                }
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
