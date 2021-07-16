package com.ljm.resource.rx.myopeartors.mergeopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SwitchDemo {

    public static void main(String[] args) {
        switchDemo();
    }
    public static void switchDemo(){
        CreateObservable.createObservable().switchMap(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                return Observable.just((Integer)o + 100);
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
