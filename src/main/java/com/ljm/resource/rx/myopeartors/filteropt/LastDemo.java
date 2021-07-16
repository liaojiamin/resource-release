package com.ljm.resource.rx.myopeartors.filteropt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;

public class LastDemo {

    public static void main(String[] args) {
        lastDemo();
    }

    public static void lastDemo(){
        CreateObservable.createObservable().last(9909090).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        CreateObservable.createObservable().takeLast(2).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
