package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

public class MapDemo {
    public static void main(String[] args) {
        Observable.just("www.zhenai.com").map(new Function<String, String>() {

            public String apply(String s) throws Exception {
                //test onError
//                Integer y = 100/0;
                return s + " " + s.length() + " length";
            }
        }).subscribe(new DisposableObserver<String>() {
            public void onNext(String s) {
                System.out.println(s);
            }

            public void onError(Throwable throwable) {
                System.out.println(" is on error");
                throwable.printStackTrace();
            }

            public void onComplete() {

            }
        });
    }
}
