package com.ljm.resource.rx.myopeartors.filteropt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ElementAtDemo {
    public static void main(String[] args) {
        elementAtDemo();
        elementAtOrDefault();
    }



    public static void elementAtOrDefault(){
        CreateObservable.createObservable().elementAtOrError(78).subscribe(new SingleObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("on error: ");
                e.printStackTrace();
            }
        });
        CreateObservable.createObservable().elementAt(100, 78).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }

    public static void elementAtDemo(){
        CreateObservable.createObservable().elementAt(4).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
