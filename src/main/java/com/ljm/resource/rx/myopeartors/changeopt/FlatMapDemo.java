package com.ljm.resource.rx.myopeartors.changeopt;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class FlatMapDemo {

    public static void main(String[] args) throws InterruptedException {
        flatMapDemo();
        Thread.sleep(3000);
    }

    public static void flatMapDemo(){
        Observable myOb = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("123123123");
                emitter.onNext(345345345);
                emitter.onNext(Lists.newArrayList(123,123,123));
                emitter.onComplete();
            }
        });
        myOb.flatMapIterable(object -> Lists.newArrayList("TestFlatMapIterable", object)).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        myOb.flatMap(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                return Observable.just(o.toString() + "function1");
            }
        }, new Function<Throwable, ObservableSource>() {
            @Override
            public ObservableSource apply(Throwable throwable) throws Exception {
                return Observable.just(throwable.getMessage());
            }
        }, new Callable<ObservableSource>() {
            @Override
            public ObservableSource call() throws Exception {
                return Observable.just("function3");
            }
        }).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        myOb.map(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
//                return Observable.fromIterable(Lists.newArrayList(o));
                if(o instanceof String){
                    return o + " String";
                }
                if(o instanceof Integer){
                    return String.valueOf(o) + " Integer";
                }
                if(o instanceof Long){
                    return String.valueOf(o) + " Long";
                }
                List<Integer> list  = (ArrayList)o;
                list.add(789);
                return JSON.toJSON(list);
            }
        }).flatMap(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                if(o.toString().indexOf("123123123") != -1 ){
                    System.out.println("index of 123123123");
                    return Observable.fromIterable(Lists.newArrayList(o));
                }
                return Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                        emitter.onNext(o);
                        Integer temp = 1/0;
                        emitter.onComplete();
                    }
                });

            }
        }, 3).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("on next : "+ JSON.toJSON(o));
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
