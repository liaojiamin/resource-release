package com.ljm.resource.rx.myopeartors.mergeopt;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.reactivex.*;
import io.reactivex.functions.Consumer;

import java.util.List;

public class MergeDemo {

    public static void main(String[] args) {
        andDemo();
    }

    public static Observable<List<String>> getNet(){
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                emitter.onNext(Lists.newArrayList("qwe","asd","zxc","cvb","dfg"));
            }
        });
    }
    public static Observable<List<String>> getLocal(){
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                emitter.onNext(Lists.newArrayList("iop","asd","nm,","vbn","fgh"));
            }
        });
    }
    public static void andDemo(){
        Observable.merge(getNet(), getLocal()).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                System.out.println(JSON.toJSON(strings));
            }
        });
//        CreateObservable.createObservable().mergeWith(Observable.just(99,88,77,66,55)).mergeWith(new SingleSource() {
//            @Override
//            public void subscribe(SingleObserver observer) {
//                observer.onSuccess(6545);
//            }
//        }).subscribe(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                System.out.println(o);
//            }
//        });
    }
}
