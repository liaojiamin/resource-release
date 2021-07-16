package com.ljm.resource.rx.create;

import com.alibaba.fastjson.JSON;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TakeDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.addAll(Arrays.asList(12, 2, 34577, 45890806, 566786787));
        list.add(790);
        Observable.just(list).flatMap(new Function<List<Integer>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<Integer> list) throws Exception {
                return Observable.fromIterable(list);
            }
        }).take(4).doOnNext(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                o = Integer.valueOf(o.toString()) + 2;
                System.out.println("do something o: "+ JSON.toJSON(o));
            }
        }).doAfterNext(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                o = Integer.valueOf(o.toString()) - 2;
                System.out.println("do after next o: " + JSON.toJSON(o));
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("subscribe o:" + JSON.toJSON(o));
            }
        });
    }
}
