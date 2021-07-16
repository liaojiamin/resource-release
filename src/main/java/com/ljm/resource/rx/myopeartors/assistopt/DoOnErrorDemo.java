package com.ljm.resource.rx.myopeartors.assistopt;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DoOnErrorDemo {
    public static void main(String[] args) {
        doOnErrorDemo();
    }

    public static void doOnErrorDemo(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {

                    if(i > 8){
                        try{Integer temp = 10/0;}catch (Exception e){
                            emitter.onError(e);
                        }
                    }else {
                        emitter.onNext(System.currentTimeMillis() + " : " + i);

                    }
                }
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("myException message: " + throwable.getMessage());
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
