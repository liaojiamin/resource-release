package com.ljm.resource.rx.myopeartors.assistopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class SubscribeDemo {

    public static void main(String[] args) {
        subscribeDemo();
    }
    public static void subscribeDemo(){
        CreateObservable.createObservable().forEachWhile(new Predicate() {
            @Override
            public boolean test(Object o) throws Exception {
                System.out.println(o);
                return !(o != null);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("teststesttesttesttesttest");
            }
        });
        CreateObservable.createObservable().forEach(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
