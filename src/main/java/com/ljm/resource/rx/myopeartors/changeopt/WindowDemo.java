package com.ljm.resource.rx.myopeartors.changeopt;

import com.alibaba.fastjson.JSON;
import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;

public class WindowDemo {

    public static void main(String[] args) {
        windowDemo();
    }
    public static void windowDemo(){
        CreateObservable.createObservable().window(3).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(JSON.toJSON(o));
            }
        });
    }
}
