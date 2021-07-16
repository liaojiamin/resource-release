package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.FutureObserver;

public class ActionDemo {
    public static void main(String[] args) {
        actionDemo("qwe","asd","zxc","cvb","fgh");
    }
    public static void threadSubscribe(){
        Observable.fromFuture(new FutureObserver<Integer>());
    }
    public static void actionDemo(String ...names){
        Observable.fromArray(names).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("is myName :"+ s);
            }
        });
    }
}
