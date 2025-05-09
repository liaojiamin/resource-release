package com.ljm.resource.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by jiamin5 on 2023/3/25.
 */
public class CallableTest{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1. 创建MyCallable
        MyCallable myCallable = new MyCallable();
        //2. 创建FutureTask，传入Callable
        FutureTask futureTask = new FutureTask(myCallable);
        //3. 创建Thread线程
        Thread t1 = new Thread(futureTask);
        //4. 启动线程
        t1.start();
        //5. 做一些操作
        //6. 要结果
        Object count = futureTask.get();
        System.out.println("总和为：" + count);
    }
}

class MyCallable implements Callable {

    @Override
    public Object call() throws Exception {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            count += i;
        }
        return count;
    }
}
