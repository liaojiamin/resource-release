package com.ljm.resource.rx.create;

import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class OomDemo {
    public static void main(String[] args) throws InterruptedException {
        missBackPressureDemo();
        Thread.sleep(900000);
    }

    public static void missBackPressureDemo(){
        Flowable flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 300; i++) {
                    System.out.println("发射数据: " + i);
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.LATEST);

        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("on subscribe");
                subscription.request(30);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("消费： "+integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("oncomplete");
            }
        });
    }

    public static void oomDemoFix(){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i=0;;i++){
                    System.out.println(Thread.currentThread().getName() + " onNext : "+ i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(50);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(Thread.currentThread().getName() + "消费数据: "+ integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public static void oomDemo(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i=0;;i++){
                    System.out.println(Thread.currentThread().getName() + " onNext : "+ i);
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.single())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " consumer : "+ integer);
                    }
                });
    }
}
