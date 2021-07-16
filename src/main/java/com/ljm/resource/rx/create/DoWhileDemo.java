package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

import java.util.Arrays;

public class DoWhileDemo {
    public static void main(String[] args) {
        doWhileDemo();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void doWhileDemo(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                for (int i = 0; i < 6; i++) {
                    emitter.onNext( i);
                }
            }
        }).buffer(3).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
