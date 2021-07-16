package com.ljm.resource.rx.conne;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConnectOpt {
    public static void main(String[] args) {
        hotObservable();
    }

    public static void codeObservable(){
        List<Integer> list = Arrays.asList(1,2,3,44,33,43,43,43,25,4,5645,75,785,6,353,45);
         Observable observable = Observable.fromIterable(list);
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("subsvribe one:" + integer);
            }
        });
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("subsvribe two:" + integer);
            }
        });
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("subsvribe three:" + integer);
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void hotObservable(){
        Observable<Long> observable = Observable.create(e -> Observable.interval(100L, 100L , TimeUnit.MILLISECONDS)
                .subscribe(e::onNext));
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subScribe one :" + aLong);
            }
        });
        System.out.println("one over");
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscribe two:"+ aLong);
            }
        });
        System.out.println("two over");
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("Subscirbe three:"+ aLong);
            }
        });
        System.out.println("three over");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void connectObservable(){
        ConnectableObservable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> observableEmitter) throws Exception {
                Observable.interval(100L,100L,  TimeUnit.MILLISECONDS).subscribe(observableEmitter::onNext);
            }
        }).observeOn(Schedulers.newThread()).publish();
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subScribe one :" + aLong);
            }
        });
        System.out.println("one over");
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("subscribe two:"+ aLong);
            }
        });
        System.out.println("two over");
        observable.connect();
        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("Subscirbe three:"+ aLong);
            }
        });
        System.out.println("three over");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
