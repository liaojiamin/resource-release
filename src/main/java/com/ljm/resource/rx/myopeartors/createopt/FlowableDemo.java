package com.ljm.resource.rx.myopeartors.createopt;

import com.google.common.collect.Lists;
import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FlowableDemo {

    public static void main(String[] args) throws InterruptedException {
        flowableDemo();
        Thread.sleep(5000);
    }

    public static void flowableFlatmap(){
//        Flowable.create(new FlowableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
//                for (int i = 0; i < 100; i++) {
//                    System.out.println(Thread.currentThread().getName());
//                    emitter.onNext(i);
//                }
//            }
//        }, BackpressureStrategy.ERROR).flatMap(new Function<Integer, Publisher<String>>() {
//            @Override
//            public Publisher<String> apply(Integer integer) throws Exception {
//                return PublishSubject.create();
//            }
//        })
    }

    public static void flowableDemo() throws InterruptedException {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 1; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " 发射数据");
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
//                        System.out.println(Thread.currentThread().getName() + " 过滤发射数据");
                        return integer > 0;
                    }
                }).observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Object>() {
            public void onSubscribe(Subscription subscription) {
                System.out.println("取出n条数据");
                subscription.request(3);
            }

            public void onNext(Object o) {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName());
                System.out.println("消费数据：" + o);
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
