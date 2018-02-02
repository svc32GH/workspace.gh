package com.svc32.common.svc32Utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DTFormats {

    public static String DateTimeSecZ (Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.DD 'at' HH:mm:ss z");
        return sdf.format(date);
    }

    public static String HourMinSec (long milisecs) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(milisecs);
    }


    public static String convertMs2TimeInt(long milisecs) {
        long secs = milisecs / 1000;
        long hours = secs / 3600;
        long mins = (secs / 60) % 60;

        secs = secs % 60;
        return hours + "  " + mins + "  " + secs +"  ";
    }

    // Possible date formats: "yyyy.MM.DD 'at' HH:mm:ss z"
    // .....................   "HH:mm:ss"
    // .....................   "mm:ss"
    // .....................   "ss"
    // ... and any others
    //
    // This is intended for correct transformation some period of dates by certain format
    public static Date GetUTCdate(long dateInterval, String dateFormat) {
        final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcDate = sdf.format(new Date(dateInterval));

        return null;
    }

}
