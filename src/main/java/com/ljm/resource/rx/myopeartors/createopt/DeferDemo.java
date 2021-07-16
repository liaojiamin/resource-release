package com.ljm.resource.rx.myopeartors.createopt;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;

import java.util.concurrent.Callable;

public class DeferDemo {
    public static void main(String[] args) throws InterruptedException {
        deferDemo();
        Thread.sleep(3000);
    }
    public static void deferDemo(){
        Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just("qwe","asd","zxc","cvb").subscribeOn(Schedulers.computation())
                        .observeOn(Schedulers.computation()).map(new Function<String, String>() {
                            @Override
                            public String apply(String s) throws Exception {
                                return (s += "myPage").toUpperCase();
                            }
                        });
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
