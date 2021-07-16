package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Observable.just("hello www.zhenai.com").subscribe(
                new Consumer<String>(){
                    @Override
                    public void accept(String s){
                        System.out.println(s);
                    }
                }
        );
    }
}
