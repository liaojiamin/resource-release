package com.ljm.resource.rx.myopeartors.filteropt;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class DistinctDemo {
    public static void main(String[] args) {
        distinctDemo();
    }
    public static void distinctDemo(){
        Observable myOb = Observable.just(1,1,1,1,2,2,2,2);
        myOb.distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
