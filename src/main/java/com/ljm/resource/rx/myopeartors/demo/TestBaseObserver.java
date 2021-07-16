package com.ljm.resource.rx.myopeartors.demo;

import com.ljm.resource.rx.myopeartors.CreateObservable;

public class TestBaseObserver {
    public static void main(String[] args) {
        demo();
    }
    public static void demo(){
        CreateObservable.createObservable().subscribe(new BaseObserver() {
            @Override
            public void onNext(Object o) {
                super.onNext(o);
                System.out.println(o);
            }
        });
    }
}
