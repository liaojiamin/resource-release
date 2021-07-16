package com.ljm.resource.rx.myopeartors.filteropt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;

public class OfTypeDemo {

    public static void main(String[] args) {
        ofTypeDemo();
    }
    public static void ofTypeDemo(){
        CreateObservable.createObservable().cast(Number.class).ofType(Number.class).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
