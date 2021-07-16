package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxjavaJustDemo {
    public static void main(String[] args) {
        Observable.just("hello zhenai.com").subscribe(
                new Consumer<String>() {
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                }
        );
    }
}
