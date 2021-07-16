package com.ljm.resource.rx.change;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FlatMapDemo {

    static List<String> integerStr = Arrays.asList("12","234","345","567","7","456");

    public static void main(String[] args) {
        flatMapDemo();
    }

    public static void mapDemo(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                for (String s : integerStr) {
                    emitter.onNext(Integer.valueOf(s));
                }
            }
        }).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Exception {
                if(Integer.valueOf(o.toString()) > 10){
                    String tempStr = o.toString();
                    return tempStr + ": "+ new Date().getTime();
                }
                return null;
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(d.isDisposed());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext :" + o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("on error exception:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }


    public static void flatMapDemo(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                for (String s : integerStr) {
                    emitter.onNext(s);
                }
            }
        }).flatMap(new Function<Object, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Object o) throws Exception {
                if(Integer.valueOf(o.toString())> 10)
                return Observable.just(o.toString() +": "+  new Date().getTime());
                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        return;
                    }
                });
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("on subscribe");
            }

            @Override
            public void onNext(String integer) {
                System.out.println(integer);
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
