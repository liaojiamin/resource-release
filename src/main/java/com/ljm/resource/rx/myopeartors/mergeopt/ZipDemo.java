package com.ljm.resource.rx.myopeartors.mergeopt;

import com.google.common.collect.Lists;
import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;

public class ZipDemo {

    public static void main(String[] args) {
        zipDemo();
    }
    public static void zipDemo(){
        Observable.zip(Observable.just(Lists.newArrayList(1, 2, 3, 4, 5, 6)),
                Observable.just(Lists.newArrayList(111,222,333,444,555,666)),
                new BiFunction<ArrayList<Integer>, ArrayList<Integer>, Object>() {
                    @Override
                    public Object apply(ArrayList<Integer> integers, ArrayList<Integer> integers2) throws Exception {
                         integers.addAll(integers2);
                         return integers;
                    }
                }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        CreateObservable.createObservable().zipWith(Observable.just(999, 888,777,666,555,444,333,222,111,000), new BiFunction() {
            @Override
            public Object apply(Object o, Object o2) throws Exception {
                return (Integer)o + (Integer)o2;
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
