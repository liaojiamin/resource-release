package com.ljm.resource.rx.myopeartors.mergeopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class CombineLatestDemo {

    public static void main(String[] args) {
        combineLatestDemo();
    }

    public static void combineLatestTest(){

    }

    public static void combineLatestDemo(){
        // a, b, function  a中只取后一个元素和b中所有元素分别发射到fun中
        Observable.combineLatest(CreateObservable.createObservable(), Observable.just(999, 333), new BiFunction<Object, Integer, Object>() {
            @Override
            public Object apply(Object o, Integer integer) throws Exception {
                return (Integer)o + integer;
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
