package com.ljm.resource.jvm;

/**
 * @author liaojiamin
 * @Date:Created in 14:38 2020/5/7
 */
public class HoldCPUMain {
    public static class HoldCpuTesk implements Runnable{

        @Override
        public void run() {
            while (true){
                double a = Math.random()* Math.random();
            }
        }
    }
    public static class LazyTask implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new HoldCpuTesk()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }
}
