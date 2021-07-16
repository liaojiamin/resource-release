package com.ljm.resource.queue;

/**
 * @author liaojiamin
 * @Date:Created in 10:01 2020/4/16
 */
public class MyThread implements Runnable, Comparable<MyThread>{
    private String name;
    public MyThread(){}
    public MyThread(String name){
        this.name = name;
    }
    @Override
    public int compareTo(MyThread o) {
        int me = Integer.parseInt(this.name.split("_")[1]);
        int other = Integer.parseInt(o.name.split("_")[1]);
        if(me > other) {
            return 1;
        }else if(me < other) {
            return -1;
        }else {
            return 0;
        }
    }
    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println(name + " ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
