package com.ljm.resource.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liaojiamin
 * @Date:Created in 11:04 2020/4/15
 */
public class MyThread implements Runnable{
    protected String name;
    public MyThread(){}
    public MyThread(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(100);
            System.out.println("run target targetname: "+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new MyThread("testJDKThreadPool" + Integer.toString(i)));
        }
    }
}
