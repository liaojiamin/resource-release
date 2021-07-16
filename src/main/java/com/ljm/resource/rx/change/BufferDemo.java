package com.ljm.resource.rx.change;

import com.alibaba.fastjson.JSON;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.Arrays;
import java.util.List;

public class BufferDemo {
    static List<String> integerStr = Arrays.asList("12","234","345","567","7","456");

    public static void main(String[] args) {
        bufferClosingSelectorDemo();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void bufferClosingSelectorDemo(){
        Observable.just(integerStr).buffer(2, 3).subscribe(new Consumer<List<List<String>>>() {
            @Override
            public void accept(List<List<String>> lists) throws Exception {
                System.out.println(JSON.toJSON(lists));
            }
        });
    }

    public static void bufferDemo(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                for (int i = 0; i < 6; i++) {
                    emitter.onNext( i);
                }
            }
        }).buffer(3).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }

    public static void flatMapDemo(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Observable.fromIterable(integerStr).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        emitter.onNext(s);
                    }
                });
            }
        }).flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                return Observable.just(s);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
    public static void mapDemo(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Observable.fromIterable(integerStr).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        emitter.onNext(s);
                    }
                });
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return Integer.valueOf(s) + 1;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
