package com.ljm.resource.rx.myopeartors.createopt;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FlowableEmitterDemo {
    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<3 ;i++){
            fix(flowableEmitterDemo());
        }
        Thread.sleep(90000);
    }

    public static void fix(Flowable flowable){
        flowable.observeOn(Schedulers.computation()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(20);
            }

            @Override
            public void onNext(Integer integer) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费数据： "+ 100);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static Flowable flowableEmitterDemo(){
        Flowable flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                while (emitter.requested() > 0){
                    System.out.println("下游处理能力："+ emitter.requested());
                    emitter.onNext(100);
                }

            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io());
        return flowable;
    }
}
