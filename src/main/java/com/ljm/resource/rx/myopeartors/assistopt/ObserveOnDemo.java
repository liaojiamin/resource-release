package com.ljm.resource.rx.myopeartors.assistopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ObserveOnDemo {

    public static void main(String[] args) throws InterruptedException {
        observeOnDemo();
        Thread.sleep(3000);
    }
    public static void observeOnDemo(){
        CreateObservable.createObservable().observeOn(Schedulers.computation()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o + " myThreadName: "+ Thread.currentThread().getName());
            }
        });
    }
}
