package com.ljm.resource.rx.myopeartors.changeopt;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BufferDemo {
    public static void main(String[] args) throws InterruptedException {
        bufferDemo();
        Thread.sleep(3000);
    }
    public static void bufferDemo(){
        Observable myOb = Observable.just(Lists.newArrayList(1,22,3,44,5,66,7,88,9));
        myOb.buffer(5, 1).subscribe(new Consumer<List<ArrayList<Integer>>>() {
            @Override
            public void accept(List<ArrayList<Integer>> arrayLists) throws Exception {
                System.out.println(JSON.toJSON(arrayLists));
            }
        });
        myOb.buffer(2,2,TimeUnit.SECONDS).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("second:" + JSON.toJSON(o));
            }
        });
        Observable<List<Integer>> bufferObservable = myOb.buffer(500, TimeUnit.MILLISECONDS);

    }
}
