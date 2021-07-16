package com.ljm.resource.rx.myopeartors.filteropt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class SkipDemo {

    public static void main(String[] args) throws InterruptedException {
        skipDemo();
        Thread.sleep(3000);
    }
    public static void skipDemo(){
        CreateObservable.createObservable().skipLast(3).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        CreateObservable.createObservable().skip(3, TimeUnit.SECONDS, Schedulers.newThread()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        CreateObservable.createObservable().skip(3).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
