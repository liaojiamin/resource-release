package com.ljm.resource.rx.myopeartors.single;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public class TestSchedule {
    public void mySchedule(){
      Scheduler.Worker worker = Schedulers.newThread().createWorker();

    }

}
