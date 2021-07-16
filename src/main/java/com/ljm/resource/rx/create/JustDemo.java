package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.io.Serializable;

public class JustDemo {
    public static void main(String[] args) {
        justTest();
    }
    public static void justTest(){
        Observable.empty().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        Observable.just("asdf","qwer",1,0,2).subscribe(new Observer<Serializable>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println(disposable);
            }

            @Override
            public void onNext(Serializable serializable) {
                System.out.println(serializable);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
