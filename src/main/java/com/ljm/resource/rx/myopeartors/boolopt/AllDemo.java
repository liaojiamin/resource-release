package com.ljm.resource.rx.myopeartors.boolopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class AllDemo {

    public static void main(String[] args) {
        allDemo();
    }
    public static void allDemo(){
        CreateObservable.createObservable().all(new Predicate() {
            @Override
            public boolean test(Object o) throws Exception {
                return o instanceof Integer;
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }


}
