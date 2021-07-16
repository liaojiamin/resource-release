package com.ljm.resource.rx.myopeartors;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class CreateObservable {
    public static Observable createObservable() {
        Integer count = 10;
        return Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                for (Integer i = 0; i < count; i++) {
//                    Thread.sleep(300);
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        });
    }
}
