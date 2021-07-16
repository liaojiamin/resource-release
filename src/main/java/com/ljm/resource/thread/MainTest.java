package com.ljm.resource.thread;

import java.util.concurrent.*;

/**
 * @author liaojiamin
 * @Date:Created in 16:11 2020/4/13
 */
public class MainTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        自己demo
//        Client client = new Client();
//        Data data = client.request("my name");
//        System.out.println("request over");
//        try {
//            System.out.println("sleep 2000ms");
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("data = "+ data.getResult());
        FutureTask<String> futureTask = new FutureTask<>(new NewRealData("my name"));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(futureTask);
        System.out.println("request over");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("data = "+ futureTask.get());
    }
}
