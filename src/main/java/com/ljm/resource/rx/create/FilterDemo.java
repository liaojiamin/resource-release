package com.ljm.resource.rx.create;

import com.alibaba.fastjson.JSON;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.addAll(Arrays.asList(12, 2, 34577, 45890806, 566786787));
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
                return io.reactivex.Observable.fromArray(strings);
            }
        }).filter(new Predicate<Object>() {
            @Override
            public boolean test(Object obj) throws Exception {
                List<String> tempList = (List<String>) obj;
                if (tempList.stream().filter(vo -> vo.length() > 4).map(vo -> vo).count() > 8) {
                    return true;
                }
                return false;
            }
        }).subscribe(new Consumer<Object>() {
            public void accept(Object obj) throws Exception {
                List<String> strings = (List<String>) obj;
                System.out.println(strings.size());
                System.out.println(JSON.toJSON(strings));
            }
        });
    }
}
