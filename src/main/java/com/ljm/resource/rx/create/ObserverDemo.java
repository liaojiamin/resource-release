package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ObserverDemo {
    public static void observableCreate(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1992);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void main(String[] args) {
        observableCreate();
        Observable.just("www.zhenai.com hello").subscribe(new DisposableObserver<String>() {
            public void onNext(String s) {
                System.out.println(s);
            }

            public void onError(Throwable throwable) {
                System.out.println("on Error");
                throwable.printStackTrace();
            }

            public void onComplete() {

            }
        });
    }
}
