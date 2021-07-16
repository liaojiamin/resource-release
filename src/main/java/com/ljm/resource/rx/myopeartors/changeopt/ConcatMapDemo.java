package com.ljm.resource.rx.myopeartors.changeopt;

import com.alibaba.fastjson.JSON;
import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class ConcatMapDemo {

    public static void main(String[] args) {
        concatMapDemo();
    }

    public static void concatMapDemo(){
        CreateObservable.createObservable().concatMap(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                return Observable.just("testConcatMap", o);
            }
        }).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println(JSON.toJSON(o));
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
