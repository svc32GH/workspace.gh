package com.svc32.common.svc32Utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DTFormats {
    static final String unlockerHmFormat = "HH'H' mm'min'";
    static final SimpleDateFormat unlockerHmSdf = new SimpleDateFormat(unlockerHmFormat);

    static final String unlockerHmsFormat = "HH'H' mm'min' ss'sec'";
    static final SimpleDateFormat unlockerHmsSdf = new SimpleDateFormat(unlockerHmsFormat);

    static final String baseHmFormat = "HH:mm";
    static final SimpleDateFormat baseHmSdf = new SimpleDateFormat(baseHmFormat);

    static final String baseHmsFormat = "HH:mm:ss";
    static final SimpleDateFormat baseHmsSdf = new SimpleDateFormat(baseHmsFormat);

    static final String customYdhmsFormat = "YY'Y' DDD'days' HH'H' mm'min' ss'sec'";
    static final SimpleDateFormat customYdhmsSdf = new SimpleDateFormat(customYdhmsFormat);

    static final String instantFormat = "yyyy-MM-dd'T'HH:mm:ss";
    static final SimpleDateFormat instantSdf = new SimpleDateFormat(instantFormat);

    static final String logDTZformat = "yyyy.MM.dd 'at' HH:mm:ss z";
    static final String logDformat = "yyyy.MM.dd";
    static final SimpleDateFormat logDTZsdf = new SimpleDateFormat(logDTZformat);
    static final SimpleDateFormat logDsdf = new SimpleDateFormat(logDformat);

//    static {
//        baseHmsSdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//        instantSdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//    }

//    public static long getMiliseconds(String customDateString) throws ParseException {
//        long res = 0;
//        Date resDate = customHmsSdf.parse(customDateString);
//        resDate.toInstant()
//
//        GregorianCalendar cal = new GregorianCalendar(resDate);
//
//        return res;
//    }

    public static String getYdhms(Date date) {
        return customYdhmsSdf.format(date);
    }

    public static String getDateOnly(Date date) {
        return logDsdf.format(date);
    }

    public static String getDateTimeSecZ(Date date) {
        return logDTZsdf.format(date);
    }

    public static Date parseDateTimeSecZ(String dateString) throws ParseException {
        return logDTZsdf.parse(dateString);
    }

    public static String getHourMinSec(long milisecs) {
        return baseHmsSdf.format(milisecs);
    }


    public static String convertMs2TimeInt(long milisecs) {
        long secs = milisecs / 1000;
        long years = (secs / 31536000 ) % 31536000;
        long days = (secs / 86400 ) % 365;
        long hours = (secs / 3600) % 24;
        long mins = (secs / 60) % 60;
        secs = secs % 60;
        String yearS = (years == 0 ? "   " : String.format("%1$2dY ", years) );
        String dayS = (days == 0 ? "     " : String.format("%1$3dD ", days) );
        String hourS = (hours == 0 ? "   " : String.format("%1$2dH ", hours) );
        String minS = (mins == 0 ? "      " : String.format("%1$2dmin ", mins) );
        String secS = (secs == 0 ? "   " : String.format("%1$2dsec ", secs) );
        return yearS + dayS + hourS + minS + secS;
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

    public static Map<TimeUnit,Long> computeDiff(Date date1, Date date2) {
        long diffInMillies = date2.getTime() - date1.getTime();
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);
        Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
        long milliesRest = diffInMillies;
        for ( TimeUnit unit : units ) {
            long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            milliesRest = milliesRest - diffInMilliesForUnit;
            result.put(unit,diff);
        }
        return result;
    }
}
