package com.ljm.resource.rx.myopeartors.filteropt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class FilterDemo {

    public static void main(String[] args) {
        filterDemo();
    }
    public static void filterDemo(){
        CreateObservable.createObservable().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 4;
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
