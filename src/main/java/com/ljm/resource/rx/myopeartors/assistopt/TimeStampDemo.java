package com.ljm.resource.rx.myopeartors.assistopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TimeStampDemo {

    public static void main(String[] args) {
        timestampDemo();
    }
    public static void timestampDemo(){
        CreateObservable.createObservable().timestamp(Schedulers.newThread()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
