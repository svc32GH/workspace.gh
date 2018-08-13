package com.svc32.common.unlocker;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;

import static com.svc32.common.svc32Utils.date.DTFormats.*;
import static com.svc32.common.svc32Utils.file.FileFunctions.*;

public class TestUnlockLogWriter {
    private static final String path = "C:\\ws.git\\my\\common\\UnLocker\\WorkLog.txt";
    private static Unlocker listModel;
    private static Robot robot = null;

    static {
        try {
            listModel = new Unlocker(new File(path), true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetStartDate() throws IOException, ParseException {
//        System.out.println(args);

        UnlockLogWriter ulw = new UnlockLogWriter(new File(path), listModel, robot);
        String startDate = ulw.getStartDate();
        System.out.println("startDate   = " + startDate.split("\\|")[0]);
        System.out.println("startTime   = " + startDate.split("\\|")[1]);

        String currentDate = getDateOnly(new Date());
        System.out.println("currentDate = " + currentDate);

    }

    @Test
    public void testGetStartTimeAsSeconds() throws IOException, ParseException {
        UnlockLogWriter ulw = new UnlockLogWriter(new File(path), listModel, robot);

        String line;
        long startTimeAsSeconds;

        line = "\nElapsed time: 9999D 1H 15min 10sec";
        startTimeAsSeconds = ulw.getStartTimeAsMiliSeconds(line);
        System.out.println("startTimeAsSeconds = " + startTimeAsSeconds);

//        int i1 = 12;
//        int i2 = 5;
//        i1 -= i2;
//        System.out.println("i1                 = " + i1);

//        String str = "|";
//        String[] strArr = str.split("\\|");
//        int count = strArr.length;
//        System.out.println(count);
//        System.out.println(Arrays.asList(strArr));
//
//        int index = str.indexOf("|");
//        System.out.println("index = " + index);

        line = "\nElapsed time:   12D      1H       10sec";
        startTimeAsSeconds = ulw.getStartTimeAsMiliSeconds(line);
        System.out.println("startTimeAsSeconds = " + startTimeAsSeconds);

        line = "\nElapsed time:          1H       10sec";
        startTimeAsSeconds = ulw.getStartTimeAsMiliSeconds(line);
        System.out.println("startTimeAsSeconds = " + startTimeAsSeconds);

    }

//    @Test
//    public void testSysProps() {
//        Properties properties = System.getProperties();
//        for (Object key : properties.keySet())
//            System.out.println(String.format("%1$40s = %2$-80s", key, properties.get(key)));
//    }

    @Test
    public void testReadLastLine() {
        String lastLine = rewriteLastLine(new File(path), "Elapsed time:         1H 15min 12sec");
        System.out.println("lastLine = " + lastLine);

    }

    @Test
    public void testRegEx() {
        String startString = "Started on 2018.01.14 at 11:21:33 EET";
        String elapsedTimeString = "Elapsed time:        12H 15min 21sec";

        Matcher matcherStartString = UnlockLogWriter.extractStartString.matcher(startString);
        Matcher matcherElapsedTimeString = UnlockLogWriter.extractElapsedTimeString.matcher(elapsedTimeString);

        boolean matcherStartStringFind = matcherStartString.find();
        String startDate = matcherStartString.group(1);
        System.out.println("matcherStartString.find()       = " + matcherStartStringFind
                + ",        date = " + startDate
        );
        System.out.println("matcherElapsedTimeString.find() = " + matcherElapsedTimeString.find()
                + ", elapsedTime = " + matcherElapsedTimeString.group(1)
        );

        String[] d = startDate.split("\\.");
        int year = Integer.parseInt(d[0]);
        int month = Integer.parseInt(d[1]);
        int day = Integer.parseInt(d[2]);
        int etH = Integer.parseInt(matcherElapsedTimeString.group(2).replace("H", ""));
        int etM = Integer.parseInt(matcherElapsedTimeString.group(3).replace("min", ""));
        int etS = Integer.parseInt(matcherElapsedTimeString.group(4).replace("sec", ""));

        System.out.println("year  = " + year);
        System.out.println("month = " + month);
        System.out.println("day   = " + day);
        System.out.println("H     = " + etH);
        System.out.println("min   = " + etM);
        System.out.println("sec   = " + etS);

        GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
//        calendar.setTime(new Date());
//        calendar.set(year, month-1, day);

        System.out.println("Calendar.WEEK_OF_YEAR                = " + calendar.get(Calendar.WEEK_OF_YEAR));

        System.out.println();
        for (int dd=1; dd<32; dd++) {
            GregorianCalendar c = new GregorianCalendar(year, month-1, dd);
            System.out.println(dd + " Calendar.WEEK_OF_YEAR = " + c.get(Calendar.WEEK_OF_YEAR));
        }
        System.out.println("calendar.isWeekDateSupported() = " + calendar.isWeekDateSupported());

    }

}
