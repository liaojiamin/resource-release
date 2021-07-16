package com.ljm.resource.rx.create;

import com.alibaba.fastjson.JSON;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.addAll(Arrays.asList(12,2,34577,45890806,566786787));
        list.add(790);
        Observable.just(list).map(new Function<List<Integer>, List<String>>() {
            public List<String> apply(List<Integer> integers) throws Exception {
                List<String> strings = new ArrayList<String>();
                for (Integer integer : integers) {
                    strings.add(integer.toString() + integer);
                }
                System.out.println(JSON.toJSON(strings));
                return strings;
            }
        }).flatMap(new Function<List<String>, ObservableSource<?>>() {
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromArray(strings);
            }
        }).subscribe(new Consumer<Object>() {
            public void accept(Object obj) throws Exception {
                List<String> strings = (List<String>) obj;
                strings = strings.stream().filter(str -> str.length()>8).collect(Collectors.toList());
                System.out.println(strings.size());
                System.out.println(JSON.toJSON(strings));
            }
        });
    }
}
