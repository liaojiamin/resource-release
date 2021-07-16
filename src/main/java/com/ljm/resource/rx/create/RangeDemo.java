package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RangeDemo {
    public static void main(String[] args) {
        rangeDemo();
    }
    public static void rangeDemo(){
        Observable.range(10, 10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

                System.out.println(integer);
            }
        });
    }
}
