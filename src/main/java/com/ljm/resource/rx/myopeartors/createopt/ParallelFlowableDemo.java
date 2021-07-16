package com.ljm.resource.rx.myopeartors.createopt;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

public class ParallelFlowableDemo {
    public static void main(String[] args) throws InterruptedException {
        parallelFollowableDemo();
        Thread.sleep(1000);
    }


  public static void parallelFollowableDemo(){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + ": :" + "onNext");
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR).parallel(2).runOn(Schedulers.io())
                .flatMap(new Function<Integer, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(Integer integer) throws Exception {
                        System.out.println(Thread.currentThread().getName() + " : "+ String.valueOf(integer) +" : "+ System.currentTimeMillis());
                        return Flowable.just(Thread.currentThread().getName() + " : "+ String.valueOf(integer) +" : "+ System.currentTimeMillis());
                    }
                }).runOn(Schedulers.newThread()).sequential().subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) throws Exception {
//                System.out.println(Thread.currentThread().getName() + " : "+ integer +" onConsumer");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
  }


    public static void parallelFollwoableDemo(){
        Flowable.range(1, 100).parallel().runOn(Schedulers.newThread(), 10)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        System.out.println(Thread.currentThread().getName() + "   : map  " + integer);
                        return String.valueOf(integer) + "  :  " +System.currentTimeMillis();
                    }
                }).sequential().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(Thread.currentThread().getName() + "  :  Consumer");
                System.out.println(s);
            }
        });
    }
}
