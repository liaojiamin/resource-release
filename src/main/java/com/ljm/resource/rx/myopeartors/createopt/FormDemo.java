package com.ljm.resource.rx.myopeartors.createopt;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.observers.FutureObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FormDemo {
    public static void main(String[] args) throws InterruptedException {
        mapOriginDemo();
        Thread.sleep(1000);
    }

    public static void mapOriginDemo(){
        Map<String, Object> map = new HashMap<>();
        map.put("test", 100);
        map.put("demo", 200);
        Observable.just(map).flatMap(new Function<Map<String, Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Map<String, Object> stringObjectMap) throws Exception {
                for (Object o : stringObjectMap.values()) {
                    System.out.println(o);
                    return Observable.just(o);
                }
                return Observable.empty();
            }
        }).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Exception {
                return Integer.valueOf(o.toString()) + 9;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }

    public static void fromDemo(){
        Observable.fromFuture(new Future<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return "123";
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                Thread.sleep(3000);
                return "234234234";
            }
        }, 1, TimeUnit.MILLISECONDS).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
        Observable.fromIterable(Lists.newArrayList(1,2,3,4,5,6,7,8,9,33))
                .map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return String.valueOf(integer) + ":" + System.currentTimeMillis();
            }
        }).flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                return Observable.fromArray(s);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(JSON.toJSON(o));
            }
        });
    }
}
