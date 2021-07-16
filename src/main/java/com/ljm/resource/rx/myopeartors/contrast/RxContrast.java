package com.ljm.resource.rx.myopeartors.contrast;

import com.google.common.collect.Lists;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class RxContrast {

    public static void main(String[] args) throws InterruptedException {
//        rxDemo();
        javaDemo();
        Thread.sleep(2000);
    }
    public static void rxDemo(){
        Observable.fromArray(new File("E:\\learn").listFiles())
                .flatMap(new Function<File, ObservableSource<File>>() {
                    @Override
                    public ObservableSource<File> apply(File file) throws Exception {
                        if(file.isDirectory()){
                            return Observable.fromArray(file.listFiles());
                        }
                        return Observable.just(file);
                    }
                })
                .filter(fileFilter -> fileFilter.getName().endsWith("pdf"))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation()).subscribe(name -> System.out.println(name.getName()));
    }

    public static void javaDemo(){
        new Thread(){
            public void run(){
                for (File file : new File("E:\\learn").listFiles()) {
                    if(file.isDirectory()){
                        for (File fileSun : file.listFiles()){
                            if(fileSun.getName().endsWith("pdf")){
                                System.out.println(fileSun.getName());
                            }
                        }
                    }
                    if(file.getName().endsWith("pdf")){
                        System.out.println(file.getName());
                    }
                }
            }
        }.start();
    }
}
