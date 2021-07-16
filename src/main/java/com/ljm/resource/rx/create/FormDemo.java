package com.ljm.resource.rx.create;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.FutureObserver;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FormDemo {
    public static void rxFuture(){
        Observable.fromFuture(new FutureObserver<>()).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Object o) {
                Future future = (Future) o;
                try {
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public static void rxForm(Integer[] items){

        Observable.fromArray(items).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                if(!disposable.isDisposed()){
                    System.out.println("disposable isDisposed");
                }

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public static void main(String[] args) {
        Integer[] item = {1,2,3,4,5,6,7,8,5,56,45,34,5};
        rxForm(item);
    }
}
