package com.ljm.resource.rx.myopeartors.assistopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.Notification;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DoDemo {
    public static void main(String[] args) {
        doDemo();
    }

    public static void doDemo(){
        CreateObservable.createObservable().doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                disposable.dispose();
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        CreateObservable.createObservable().doOnNext(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("do on next: " + o);
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        CreateObservable.createObservable().doOnEach(new Consumer<Notification>() {
            @Override
            public void accept(Notification notification) throws Exception {
                System.out.println("do on each: "+notification.getValue());
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
