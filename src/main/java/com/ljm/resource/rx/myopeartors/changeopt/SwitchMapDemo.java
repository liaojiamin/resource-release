package com.ljm.resource.rx.myopeartors.changeopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class SwitchMapDemo {
    public static void main(String[] args) throws InterruptedException {
        switchMapDemo();
        Thread.sleep(3000);
    }

    public static void switchMapDemo(){
        CreateObservable.createObservable().switchMap(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                return Observable.just("switchMap", o).delay(1, TimeUnit.SECONDS);
            }
        }).observeOn(Schedulers.newThread()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(Thread.currentThread().getName());
                System.out.println(o);
            }
        });
    }
}
