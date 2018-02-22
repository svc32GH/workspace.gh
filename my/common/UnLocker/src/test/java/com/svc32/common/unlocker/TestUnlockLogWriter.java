package com.svc32.common.unlocker;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import static com.svc32.common.svc32Utils.date.DTFormats.*;
import static com.svc32.common.svc32Utils.file.FileFunctions.*;

public class TestUnlockLogWriter {
    private static final String path = "C:\\ws.git\\my\\common\\UnLocker\\WorkLog.txt";
    private static Unlocker listModel;
    private static Robot robot = null;

    static {
        try {
            listModel = new Unlocker(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetStartDate() throws IOException {
//        System.out.println(args);

        UnlockLogWriter ulw = new UnlockLogWriter(new File(path), listModel, robot);
        String startDate = ulw.getStartDate();
        System.out.println("startDate   = " + startDate.split("\\|")[0]);
        System.out.println("startTime   = " + startDate.split("\\|")[1]);

        String currentDate = getDateOnly(new Date());
        System.out.println("currentDate = " + currentDate);

    }

    @Test
    public void testGetStartTimeAsSeconds() throws IOException {
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

}
