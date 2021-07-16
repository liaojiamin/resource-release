package com.ljm.resource.rx.myopeartors.createopt;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

public class RepeatDemo {

    public static void main(String[] args) throws InterruptedException {
        repeatDemo();
        Thread.sleep(3000);
    }

    public static void repeatDemo() {
        Long time = System.currentTimeMillis();
        Observable.just(Lists.newArrayList(1, 2, 3, 4)).repeat(3).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                System.out.println(JSON.toJSON(integers));
            }
        });
    }
}
