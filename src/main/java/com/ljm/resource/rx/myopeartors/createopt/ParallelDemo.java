package com.ljm.resource.rx.myopeartors.createopt;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ParallelDemo {
    private static Lock lock = new ReentrantLock();
    private static Integer temp1=0;
    private static Integer temp2=0;
    private static Integer temp3=0;
    private static Integer tempRx=0;
    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0, 10000).forEach(intStr -> temp1 +=intStr);
        IntStream.range(0, 10000).parallel().forEach(intStr -> temp2 +=intStr);
        IntStream.range(0, 10000).forEach(i -> {
            lock.lock();
            try {
                temp3 +=i;
            } finally {
                lock.unlock();
            }
        });

        System.out.println("串行执行的大小：" + temp1);
        System.out.println("并行执行的大小：" + temp2);
        System.out.println("加锁并行执行的大小：" + temp3);
        Flowable.range(0, 10000).parallel().runOn(Schedulers.computation())
                .sequential().subscribe(intStr -> tempRx += intStr);
        Thread.sleep(1000);
        System.out.println("Rx执行结果： "+tempRx);

    }
}
