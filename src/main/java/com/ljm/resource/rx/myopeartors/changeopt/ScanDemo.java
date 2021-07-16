package com.ljm.resource.rx.myopeartors.changeopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class ScanDemo {
    public static void main(String[] args) {
        scandemo();
    }

    public static void scandemo(){
        CreateObservable.createObservable().scan(100, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer o, Integer o2) throws Exception {
                System.out.println("the previous number: "+ o);
                System.out.println("this number: "+ o2);
                System.out.println("return number: "+ (o+o2));
                return o + o2;
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }
}
