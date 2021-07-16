package com.ljm.resource.rx.myopeartors.filteropt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class FirstDemo {

    public static void main(String[] args) {
        firstdemo();
    }
    public static void firstdemo(){
        Observable.empty().single(3433).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        Observable.empty().first(789).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        CreateObservable.createObservable().take(1, TimeUnit.MILLISECONDS, Schedulers.newThread()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        Observable.empty().firstOrError().subscribe(new SingleObserver<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("on Error is not one item");
            }
        });
        CreateObservable.createObservable().first(5).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
