package com.ljm.resource.rx.myopeartors.createopt;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class IntervalDemo {
    public static void main(String[] args) throws InterruptedException {
        intervalDemo();
        Thread.sleep(4000);
    }

    public static void intervalDemo(){
        Observable myOb = Observable.interval(3,3, TimeUnit.MILLISECONDS);
        Observable secondOb = Observable.interval(1, TimeUnit.SECONDS, Schedulers.newThread());
        Observable threadOb = Observable.intervalRange(1, 100, 1, 10, TimeUnit.MILLISECONDS);
        threadOb.subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });
    }
}
