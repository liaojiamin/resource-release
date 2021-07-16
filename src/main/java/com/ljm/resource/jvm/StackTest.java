package com.ljm.resource.jvm;

/**
 * @author liaojiamin
 * @Date:Created in 14:00 2020/4/29
 */
public class StackTest {
    public static class MyThread extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int i=0;
        try {
            for (i = 0; i < 10; i++) {
                new MyThread().start();
            }
        }catch (Exception e){
            System.out.println("count thread is " + i);
        }
    }
}
