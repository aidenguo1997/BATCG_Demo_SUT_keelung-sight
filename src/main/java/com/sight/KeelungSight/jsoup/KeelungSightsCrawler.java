package com.sight.KeelungSight.jsoup;

import com.sight.KeelungSight.entity.Sight;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.*;

public class KeelungSightsCrawler {
    public Sight[] getItems(String zoneName) {
        Sight[] sight = new Sight[100];
        String keelungCityUrl = "https://www.travelking.com.tw/tourguide/taiwan/keelungcity/";
        try {
            Document keelungCity = Jsoup.connect(keelungCityUrl).get();
            Elements keelungSights = keelungCity.getElementsByClass("tourgudes fold");
            for (Element keelungSight : keelungSights) {
                Elements ul = keelungSight.select("h4:contains(" + zoneName + ")").next();
                for (Element li : ul) {
                    Elements a = li.select("li > a");
                    int count = 0;
                    for (Element href : a) {
                        count++;
                        parseSight(sight, href, count);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.stream(sight).filter(Objects::nonNull).toArray(Sight[]::new);
    }

    private void parseSight(Sight[] sight, Element href, int count) throws IOException {
        String sightUrl = href.attr("abs:href");
        Sight s = new Sight();
        Document sights = Jsoup.connect(sightUrl).timeout(40000).get();
        String zone = sights.getElementsByClass("bc_last").text();
        String sightName = sights.select("meta[itemprop=name]").attr("content");
        String photoURL = sights.select("meta[itemprop=image]").attr("content");
        String description = sights.select("meta[itemprop=description]").attr("content");
        String address = sights.select("meta[itemprop=address]").attr("content");
        String category = sights.getElementsByTag("strong").get(0).text();
        s.setZone(zone);
        s.setSightName(sightName);
        s.setPhotoURL(photoURL);
        s.setDescription(description);
        s.setAddress(address);
        s.setCategory(category);
        sight[count] = s;
    }
}
