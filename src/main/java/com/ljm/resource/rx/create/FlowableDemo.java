package com.ljm.resource.rx.create;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.apache.commons.lang3.StringUtils;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FlowableDemo {
    public static void main(String[] args) {
        flowableDemo("", -1);
    }

    public static void flowableDemo(String param1, Integer param2) {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> flowableEmitter) throws Exception {
                if (StringUtils.isBlank(param1)) {
                    System.out.println("chack param1");
                    flowableEmitter.onError(new NullPointerException());
                }
                Integer a = 1/0;
                if (param2 <= 0) {
                    System.out.println("chack param2");
                    flowableEmitter.onNext(param2);
                }
                System.out.println("create is over");
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.computation()).observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("用户：123 发起了一次对后台的查询，查询参数为：456.");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext param :" + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("is error");
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("is down");
                    }
                });
    }
}
