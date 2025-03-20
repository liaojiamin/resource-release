package com.ljm.resource.threadlocal;

import com.ljm.resource.thread.Data;

import java.util.Date;

/**
 * @author liaojiamin
 * @Date:Created in 10:33 2020/4/21
 */
public class ThreadLocalDemo implements Runnable{
    public static final ThreadLocal<Date> localvar = new ThreadLocal<>();
    private long time;
    public ThreadLocalDemo(long time){
        this.time = time;
    }

    @Override
    public void run() {
        Date d = new Date(time);
        for (int i = 0; i < 50000; i++) {
            localvar.set(d);
            if(localvar.get().getTime() != time){
                System.out.println("id = " + time + " localvar "+localvar.get().getTime());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo(new Date().getTime());
            new Thread(threadLocalDemo).start();
//            Thread.sleep(100);
        }

    }
}
