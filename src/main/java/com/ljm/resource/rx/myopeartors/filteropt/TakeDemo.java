package com.ljm.resource.rx.myopeartors.filteropt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;

public class TakeDemo {

    public static void main(String[] args) {
        takeDemo();
    }
    public static void takeDemo(){
        CreateObservable.createObservable().takeLast(1).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        CreateObservable.createObservable().take(2).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
