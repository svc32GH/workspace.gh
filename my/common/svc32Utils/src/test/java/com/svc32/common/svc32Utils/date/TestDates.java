package com.svc32.common.svc32Utils.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import static com.svc32.common.svc32Utils.date.DTFormats.*;

public class TestDates {

    @Test
    public void testDates() {
        long sec1 = 3700;
        long sec2 = 65;
        System.out.println(convertMs2TimeInt(sec1 * 1000L));
        System.out.println(HourMinSec(sec1 * 1000L));
        System.out.println(HourMinSec(sec2 * 1000L));

        System.out.println("*******************************************");


        long aFewSecsLong = 100;
        int aFewSecsInt = 100;
        Date aFewSecsDateL = new Date(aFewSecsLong * 1000);
        Date aFewSecsDateI = new Date(aFewSecsInt * 1000);
        System.out.println("aFewSecsDateI: " +  aFewSecsDateI);
        System.out.println("aFewSecsDateL: " +  aFewSecsDateL);

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.DD 'at' HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date(100 * 1000));
        System.out.println("utcTime:       " +  utcTime);

//        String[] timezones = TimeZone.getAvailableIDs();
//        System.out.println("timezones:     " + Arrays.asList(timezones));
//
//        SimpleDateFormat sdfSecs = new SimpleDateFormat("ss");
    }
}
