package com.jixianit.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StringDateDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<String> dateStrList = new ArrayList<>() ;
        dateStrList.add("2018-54-01") ;
        dateStrList.add("2018-04-02") ;
        dateStrList.add("2018-04-03") ;
        dateStrList.add("2018-04-04") ;
        dateStrList.add("2018-04-05") ;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (String str : dateStrList) {
            executorService.execute(() -> {
                try {
                    LocalDate localDate = LocalDate.parse(str, dateFormat);
                    ZoneId zone = ZoneId.systemDefault();
                    Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
                    java.util.Date date = Date.from(instant);
                    System.out.println(date);
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
