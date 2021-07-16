package com.ljm.resource.rx.myopeartors.assistopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

public class DelayDemo {

    public static void main(String[] args) throws InterruptedException {
        delayDemo();
        Thread.sleep(3000);
    }
    public static void delayDemo(){
        CreateObservable.createObservable().delay(1, TimeUnit.SECONDS).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
