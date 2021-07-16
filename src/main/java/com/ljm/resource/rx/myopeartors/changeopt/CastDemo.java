package com.ljm.resource.rx.myopeartors.changeopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;

public class CastDemo {
    public static void main(String[] args) {
        castDemo();
    }
    public static void castDemo(){
        CreateObservable.createObservable().cast(Number.class).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                if(o instanceof Number){
                    System.out.println("is Long type: "+ o );
                }else {
                    System.out.println("is not Long type");
                }
            }
        });
    }
}
