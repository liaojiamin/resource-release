package com.ljm.resource.rx.create;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.plugins.RxJavaPlugins;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

public class CreateDemo {
    public static final List<String> list = Arrays.asList("asdf","qweqwe","xcvbxc","this","is "," beijing","operral","verynice");
    private static final String TAG = "TAG log ";


    public static void main(String[] args) {
        createDemoTest();
    }

    public static void testHook(){
        Consumer<Throwable> errorHandler = (Consumer<Throwable>) RxJavaPlugins.getErrorHandler();
        BiFunction<Observable, Observer, Observer> onObservableSubscribe = (BiFunction<Observable, Observer, Observer>) RxJavaPlugins.getOnObservableSubscribe();
        Function<Observable, Observable> onObservableAssembly = (Function<Observable, Observable>) RxJavaPlugins.getOnObservableAssembly();
        System.out.println("123123+ errorHandler: "+ errorHandler);
        System.out.println("123123+ onObservableSubscribe: "+ onObservableSubscribe);
        System.out.println("123123+ onObservableAssembly: "+ onObservableAssembly);
    }

    public static void createDemoTest(){
       new ObservableCreate(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onError(new NullPointerException("123123"));
                emitter.onComplete();
                emitter.onNext(123);
            }
        }).subscribe(new Observer() {
           @Override
           public void onSubscribe(Disposable d) {
               System.out.println(d);
           }

           @Override
           public void onNext(Object o) {
               System.out.println(o);
           }

           @Override
           public void onError(Throwable e) {
               System.out.println("error: "+ e.getMessage());
           }

           @Override
           public void onComplete() {
               System.out.println("onComplete");
           }
       });
        Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            public void onSubscribe(Disposable d) { System.out.println(TAG+ "onSubscribe() called with: d = [" + d + "]"); }
            public void onNext(String value) { System.out.println(TAG+ "onNext() called with: value = [" + value + "]"); }
            public void onError(Throwable e) {
                System.out.println(TAG+ "onError() called with: e = [" + e + "]");
            }
            public void onComplete() {
                System.out.println(TAG+ "onComplete() called");
            }
        });
    }
    public static void create(){
        Observable.create(new ObservableOnSubscribe<Integer>(){
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                if(!observableEmitter.isDisposed()){
                    for(int i=0; i<100; i++){
                        observableEmitter.onNext(i);
                    }
                }
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                String nowDate = new Date().toString();
                return integer + "  " +nowDate;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }


    public static void fromDemo(){
    }


    public static  void switchOnNextDemo(){
        Observable.switchOnNext(new ObservableSource<ObservableSource<?>>() {
            @Override
            public void subscribe(Observer<? super ObservableSource<?>> observer) {
                observer.onNext(Observable.fromIterable(list));
            }
        }).subscribe(new Observer<Object>() {
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
    }

    public static void deferDemo(){
        Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                Thread.sleep(3000);
                return Observable.fromIterable(list);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(" int this onsubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println(" on next: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public static void rangeDemo(){
        Observable.range(100, 2).subscribe(new Observer<Integer>() {
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
}
