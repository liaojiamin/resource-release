package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class RepeatDemo {
    public static void main(String[] args) {
        repeatWhenDemo();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static  void retryWhenDemo(){
        Observable.just(1,2,3,"123").retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.delay(5, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<Serializable>() {
            @Override
            public void accept(Serializable serializable) throws Exception {

                System.out.println(serializable);
            }
        });
    }
    public  static void retryDemo(){
        Observable.just(1,2,3,"123").retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                Integer testTemp = 100/0;
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        if(throwable instanceof ArithmeticException){
                            return Observable.just(1000);
                        }
                        if(testTemp > 0){
                            return null;
                        }
                        return Observable.error(throwable);
                    }
                });
            }
        }).subscribe(new Observer<Serializable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Serializable serializable) {
                System.out.println(serializable.toString());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void repeatWhenDemo(){
        Observable.just(1,22,33,4,5,6,"2", 2,4,45).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                return objectObservable.delay(5, TimeUnit.SECONDS);
            }
        }).observeOn(Schedulers.io()).subscribe(new Observer<Serializable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Serializable serializable) {
                System.out.println("on next:"+ serializable);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("on complete");
            }
        });
    }
    public static  void repeatDemo(){
        Observable.just(1,2,3,4,3434,55).repeat(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
