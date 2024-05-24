package com.sight.KeelungSight.jsoup;

import com.sight.KeelungSight.entity.Sight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunJsoup {
    private final List<Sight> sightDB = new ArrayList<>();

    public List<Sight> getSightDB() {
        String[] zoneNames = {"七堵", "中山", "中正", "仁愛", "安樂", "信義", "暖暖"};
        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        for (String zoneName : zoneNames) {
            Sight[] sights = crawler.getItems(zoneName);
            sightDB.addAll(Arrays.asList(sights));
        }
        return sightDB;
    }
}
