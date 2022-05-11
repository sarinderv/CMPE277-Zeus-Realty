package com.cmpe277.project.zeusrealty;


import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class EventBusRx {
    private static EventBusRx ourInstance = new EventBusRx();
    public static EventBusRx getInstance() {
        return ourInstance;
    }
    private EventBusRx() {}

    /**
     * Use of multiple topics can be usefull
     * SerializedSubject avoid concurrency issues
     */

}
