package com.ljm.resource.rx.myopeartors.assistopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;

import java.util.concurrent.TimeUnit;

public class TimeIntervalDemo {

    public static void main(String[] args) {
//        timeIntervalDemo();
        TimeOutDemo();
    }

    public static void TimeOutDemo(){
        CreateObservable.createObservable().timeout(200, TimeUnit.MILLISECONDS).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("on error:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public static void timeIntervalDemo(){
        CreateObservable.createObservable().timeInterval(Schedulers.newThread()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Timed timed = (Timed)o;
                System.out.println( timed.time() + " : " +timed.value());
                System.out.println(o);
            }
        });
    }
}
