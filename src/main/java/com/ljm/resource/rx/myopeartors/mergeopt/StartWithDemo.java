package com.ljm.resource.rx.myopeartors.mergeopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class StartWithDemo {

    public static void main(String[] args) {
        startWithDemo();
    }

    public static void startWithDemo(){
        CreateObservable.createObservable().startWith(Observable.just(111,222,333)).startWith(987).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}


