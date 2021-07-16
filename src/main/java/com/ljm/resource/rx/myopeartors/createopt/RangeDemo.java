package com.ljm.resource.rx.myopeartors.createopt;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RangeDemo {
    public static void main(String[] args) {
        rangeDemo();
    }

    public static void rangeDemo(){
        Observable myob = Observable.range(1,100);
        myob.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
