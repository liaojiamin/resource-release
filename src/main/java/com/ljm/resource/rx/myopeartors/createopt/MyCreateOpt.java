package com.ljm.resource.rx.myopeartors.createopt;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.util.List;

public class MyCreateOpt {
    private static List<Integer> intList = Lists.newArrayList(1,2,3,4);
    public static void main(String[] args) throws InterruptedException {
        myTestFlatMapSchedulers();
        Thread.sleep(140000);
    }

    public static void myDemo(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                intList.forEach(vo -> emitter.onNext(vo));
//                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe isDisposed: "+ d.isDisposed());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("on Error！");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("finish！");
                    }
                });
    }

    public static void myTestFlatMapSchedulers(){
        Observable.create(emitter -> {
            intList.forEach(intTemp -> emitter.onNext(intTemp));
            emitter.onComplete();
        }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation())
        .flatMap(intStr -> Observable.just(intStr).subscribeOn(Schedulers.computation())
                .filter(filterInt -> Integer.valueOf(filterInt.toString()) > 2))
                .observeOn(Schedulers.computation())
        .subscribe(intTemp -> System.out.println(intTemp));
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (Integer integer : intList) {
                    System.out.println(Thread.currentThread().getName() + " : send");
                    emitter.onNext(integer);
                }
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread()).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                return Observable.just(integer).subscribeOn(Schedulers.computation()).filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        System.out.println(Thread.currentThread().getName() + ": filter one integer: "+ integer);
                        return integer > 2;
                    }
                });
            }
        }).observeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(Thread.currentThread().getName()+ " : onSubscribe :" + o);
            }
        });
    }

    public static void myTestSchedulers(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (Integer integer : intList) {
                    System.out.println(Thread.currentThread().getName() + " : send");
                    emitter.onNext(integer);
                }
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.newThread())
                .filter(integer -> {
            System.out.println(Thread.currentThread().getName() + " : filter one");
            return integer > 2;
        }).observeOn(Schedulers.io())
                .filter(integer -> {
            System.out.println(Thread.currentThread().getName() + " : filter two");
            return integer > 3;
        }).observeOn(Schedulers.computation())
                .subscribe(integer -> {
                    System.out.println(Thread.currentThread().getName() + " : "+ integer );
                });
    }

    public static void myENT(){
        Observable.never().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        Observable.error(new IOException()).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("error test:" + o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        Observable.empty().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("begin");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext" + o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("end");
            }
        });
    }

    public static void myCreate(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if(!emitter.isDisposed()){
                    for (int i = 0; i < 5; i++) {
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                }
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("over");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("on next: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void myJust(){
        Observable.range(8, 10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.print(integer);
            }
        });
        Observable.just(intList,1,2,3).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("OnNext :" + JSON.toJSON(o));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
