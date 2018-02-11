package com.svc32.common.svc32Utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DTFormats {
    static final String unlockerHmFormat = "HH'H' mm'min'";
    static final String unlockerHmsFormat = "HH'H' mm'min' ss'sec'";
    static final String baseHmFormat = "HH:mm";
    static final String baseHmsFormat = "HH:mm:ss";
    static final String instantFormat = "yyyy-MM-DD'T'HH:mm:ss";
    static final SimpleDateFormat unlockerHmSdf = new SimpleDateFormat(unlockerHmFormat);
    static final SimpleDateFormat unlockerHmsSdf = new SimpleDateFormat(unlockerHmsFormat);
    static final SimpleDateFormat baseHmSdf = new SimpleDateFormat(baseHmFormat);
    static final SimpleDateFormat baseHmsSdf = new SimpleDateFormat(baseHmsFormat);
    static final SimpleDateFormat instantSdf = new SimpleDateFormat(instantFormat);

//    static {
//        baseHmsSdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//        instantSdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//    }

    public static String DateTimeSecZ(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.DD 'at' HH:mm:ss z");
        return sdf.format(date);
    }

    public static String HourMinSec(long milisecs) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(milisecs);
    }


    public static String convertMs2TimeInt(long milisecs) {
        long secs = milisecs / 1000;
        long hours = secs / 3600;
        long mins = (secs / 60) % 60;

        secs = secs % 60;
        return hours + "  " + mins + "  " + secs + "  ";
    }

    // timeInterval - in seconds
    public static String GetUTCdate(long timeInt) {
        String res;
        long timeInterval = timeInt / 1000;
        long nDays = TimeUnit.SECONDS.toDays(timeInterval);

        Instant date = Instant.ofEpochSecond(timeInterval);
        Date iDate = null;
        try {
            iDate = instantSdf.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        String iDateStr = unlockerHmSdf.format(iDate);
        String iDateStr = unlockerHmsSdf.format(iDate);
        res = (getNumberOfDays(nDays) + " " + iDateStr).replace(" 0", "  ");
        return res;
    }

    public static String getNumberOfDays(long nDays) {
        String res;
        String nDaysStr = String.valueOf(nDays);
        if (nDays == 0)
            res = "      ";
        else
            switch ( nDaysStr.length() ) {
                case 1:  res = "    " + nDaysStr + "D";
                break;
                case 2:  res = "   " + nDaysStr + "D";
                break;
                case 3:  res = "  " + nDaysStr + "D";
                break;
                case 4:  res = " " + nDaysStr + "D";
                break;
                default: res = " >27 Years!! +";
            }
        return res;
    }

    private static void tmp() {
    }

}
