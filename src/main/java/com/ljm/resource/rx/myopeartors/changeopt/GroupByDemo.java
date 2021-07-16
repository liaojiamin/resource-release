package com.ljm.resource.rx.myopeartors.changeopt;

import com.ljm.resource.rx.myopeartors.CreateObservable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

public class GroupByDemo {

    public static void main(String[] args) {
        groupByDemo();
    }

    public static void groupByDemo(){
        CreateObservable.createObservable().groupBy(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer > 4 ? "group one": "group two";
            }
        }).subscribe(new Consumer<GroupedObservable>() {
            @Override
            public void accept(GroupedObservable groupedObservable) throws Exception {
                groupedObservable.subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        String key = groupedObservable.getKey().toString();
                        System.out.println(key + ":" + o);
                    }
                });

            }
        });
    }
}
