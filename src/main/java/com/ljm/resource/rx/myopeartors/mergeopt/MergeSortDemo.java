package com.ljm.resource.rx.myopeartors.mergeopt;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class MergeSortDemo {
    public static void main(String[] args) throws InterruptedException {
        mergeDemo();
        Thread.sleep(40000);
    }

    public static Flowable getIOData1(){
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> flowableEmitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    flowableEmitter.onNext(i);
                }
                System.out.println(Thread.currentThread().getName());
            }
        }, BackpressureStrategy.DROP).subscribeOn(Schedulers.io()).filter(temp -> temp > 2);
    }

    public static Flowable getIOData2(){
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> flowableEmitter) throws Exception {
                for (int i = 10; i < 21; i++) {
                    flowableEmitter.onNext(i);
                }
                System.out.println(Thread.currentThread().getName());
            }
        }, BackpressureStrategy.DROP).subscribeOn(Schedulers.io()).filter(temp -> temp > 12);
    }

    public static Flowable getIOData3(){
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> flowableEmitter) throws Exception {
                for (int i = 20; i < 30; i++) {
                    flowableEmitter.onNext(i);
                }
                System.out.println(Thread.currentThread().getName());
            }
        }, BackpressureStrategy.DROP).subscribeOn(Schedulers.io()).filter(temp -> temp > 22);
    }

    public static Flowable getIOData4(){
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> flowableEmitter) throws Exception {
                for (int i = 30; i < 41; i++) {
                    flowableEmitter.onNext(i);
                }
                System.out.println(Thread.currentThread().getName());
            }
        }, BackpressureStrategy.DROP).subscribeOn(Schedulers.io()).filter(temp -> temp > 32);
    }

    public static void mergeDemo(){
        List<String> result = new ArrayList<>();

        Flowable.merge(getIOData1(), getIOData2(), getIOData3(), getIOData4())
                .subscribeOn(Schedulers.io()).flatMap(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                return Flowable.just(o);
            }
        }).observeOn(Schedulers.computation())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println(o);
                    }
                });
    }
}
