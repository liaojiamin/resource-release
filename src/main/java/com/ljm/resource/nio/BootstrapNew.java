package com.ljm.resource.nio;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liaojiamin
 * @Date:Created in 16:14 2020/3/2
 */
public class BootstrapNew {
    public static void main(String[] args) throws InterruptedException {
        BootstrapNew bootstrapNew = new BootstrapNew();
        Worker worker = bootstrapNew.newWorker();
        Wrapper wrapper = new Wrapper();
        wrapper.setWorker(worker);
        wrapper.setParam("hello");

        wrapper.setListener(new Listener() {
            @Override
            public void result(Object result) {
                System.out.println(result);
            }
        });
        CompletableFuture future = CompletableFuture.supplyAsync(() -> bootstrapNew.doWork(wrapper));
        try {
            future.get(800, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            wrapper.getListener().result("time out exception");
        }
        Thread.sleep(2000);
    }

    private Wrapper doWork(Wrapper wrapper){
        Worker worker = wrapper.getWorker();
        String result = worker.action(wrapper.getParam());
        wrapper.getListener().result(result);
        return wrapper;
    }

    private Worker newWorker(){
        return new Worker() {
            @Override
            public String action(Object object) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return object + "world";
            }
        };
    }
}
