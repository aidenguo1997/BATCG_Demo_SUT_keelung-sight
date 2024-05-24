package com.sight.KeelungSight.repository;

import com.sight.KeelungSight.jsoup.RunJsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class EventHandler {
    private volatile AtomicBoolean isInit = new AtomicBoolean(false);
    @Autowired
    private SightRepository repository;

    @EventListener(ContextRefreshedEvent.class)
    public void handleContextRefreshEvent(ContextRefreshedEvent e) {
        if (!isInit.compareAndSet(false, true)) {
            return;
        }
        RunJsoup runJsoup = new RunJsoup();
        repository.insert(runJsoup.getSightDB());
        System.out.println("Insert data success");
    }

    /*@EventListener(ContextClosedEvent.class)
    public void handleContextCloseEvent(ContextClosedEvent e) {
        repository.deleteAll();
        System.out.println("Delete data success");
    }*/
}
