package com.ljm.resource.rx.myopeartors.filteropt;

import com.google.common.collect.Lists;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.single.SingleObserveOn;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class SortedDemo {
    public static void main(String[] args) throws InterruptedException {
        sortedDemo();
        Thread.sleep(40000);
    }

    public static void sortedDemo(){
        Single<List<Integer>>  list = Observable.fromIterable(Lists.newArrayList(7,4,3,1235,4,56,5,12,3,5,6,5,766,7345,234))
                .toSortedList().observeOn(Schedulers.computation());
        list.flatMap(new Function<List<Integer>, SingleSource<?>>() {
            @Override
            public SingleSource<?> apply(List<Integer> list) throws Exception {
                return SingleObserveOn.just(list);
            }
        }).subscribe(new SingleObserver<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
