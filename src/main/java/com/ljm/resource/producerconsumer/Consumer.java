package com.ljm.resource.producerconsumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author liaojiamin
 * @Date:Created in 16:31 2020/4/14
 */
public class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer id= "+ Thread.currentThread().getId());
        Random r = new Random();
        try{
            PCData pcData = queue.take();
            if(null != pcData){
                int re = pcData.getIntData() * pcData.getIntData();
                System.out.println(MessageFormat.format("{0} * {1} ={2}", pcData.getIntData(), pcData.getIntData(), re));
                Thread.sleep(r.nextInt(SLEEPTIME));
            }
        }catch (Exception e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
