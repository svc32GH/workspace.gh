package com.svc32.common;

import com.svc32.common.unlocker.UnlockLogWriter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;

import static com.svc32.common.svc32Utils.date.DTFormats.convertHMS2Ms;

public class TimeStampValue {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;
    private int sec;

    private long elapsedTime;
    private int weekOfYear;

    public TimeStampValue(String startString, String elapsedTimeString) {
        try {
            Matcher matcherStartString = UnlockLogWriter.extractStartString.matcher(startString);
            Matcher matcherElapsedTimeString = UnlockLogWriter.extractElapsedTimeString.matcher(elapsedTimeString);
            matcherStartString.find();
            matcherElapsedTimeString.find();

            String startDate = matcherStartString.group(1);

            String hourGrp = matcherElapsedTimeString.group(2);
            hour = hourGrp == null ? 0 : Integer.parseInt(matcherElapsedTimeString.group(2).replace("H", ""));

            String minGrp = matcherElapsedTimeString.group(3);
            min = minGrp == null ? 0 : Integer.parseInt(minGrp.replace("min", ""));

            String secGrp = matcherElapsedTimeString.group(4);
            sec = secGrp == null ? 0 : Integer.parseInt(secGrp.replace("sec", ""));

            elapsedTime = convertHMS2Ms(hour, min, sec);

            String[] d = startDate.split("\\.");
            year = Integer.parseInt(d[0]);
            month = Integer.parseInt(d[1]);
            day = Integer.parseInt(d[2]);

            GregorianCalendar c = new GregorianCalendar(year, month - 1, day);
            weekOfYear = c.get(Calendar.WEEK_OF_YEAR);
        } catch (IllegalStateException e) {
            if (!(startString.equalsIgnoreCase("Elapsed time:                   0sec"))
                    || elapsedTimeString.equalsIgnoreCase("Elapsed time:                   0sec")
                    ) {
                e.printStackTrace();
            } else {
                year = 2018;
                month = 12;
                day = 31;
                hour = 0;
                min = 0;
                sec = 0;
            }
//            System.out.println(startString);
//            System.out.println(elapsedTimeString);
        }
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }
}
