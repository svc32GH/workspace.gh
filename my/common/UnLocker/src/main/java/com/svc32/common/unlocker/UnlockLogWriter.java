package com.svc32.common.unlocker;

import com.svc32.common.svc32Utils.file.FileFunctions;

import javax.swing.*;

import static com.svc32.common.svc32Utils.file.FileFunctions.*;
import static com.svc32.common.svc32Utils.date.DTFormats.*;
import static com.svc32.common.svc32Utils.file.FileFunctions.rewriteLastLine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class UnlockLogWriter implements Runnable {
//    private static final int delayInt = 1000;
//    private static final int delayInt = 1000 * 5; // 5 sec
    private static final int delayInt = 1000 * 60 * 5; // 5 mins
    private static final String startedOn = "Started on ";
    private static final String elapsedTime = "Elapsed time: ";
    private final String prevStartDate;
    private final long prevStartSec;
    private final String currentDate;

    private boolean continueRun;
    private boolean startLinePrinted = false;
    private boolean isTheSameStartDay = false;
    private final String startDateTime = getDateTimeSecZ(new Date());

    private long startTimeStamp;
    private File logFile;
    private final FileFunctions ff;
    private final Unlocker unlocker;
    private final Robot robot;

    public UnlockLogWriter(File logFile, Unlocker unlocker, Robot robot) throws IOException {
        this.unlocker = unlocker;
        this.robot = robot;
        this.logFile = logFile;
        this.continueRun = true;
        this.ff = new FileFunctions(logFile);
//        this.startDateTime = System.currentTimeMillis();
        String startDateString = getStartDate();
        if (startDateString.equals("|")) {
            prevStartDate = "";
            prevStartSec = 0;
        } else {
            prevStartDate = startDateString.split("\\|")[0];
            String prevStartSecStr = startDateString.split("\\|")[1];
            prevStartSec = Long.parseLong(prevStartSecStr);
        }

        currentDate = getDateOnly(new Date());
        isTheSameStartDay = currentDate.equals(prevStartDate);
    }

    public void stopRunning() {
        continueRun = false;
    }

    public void run() {
        startTimeStamp = System.currentTimeMillis();
        if (isTheSameStartDay)
            startTimeStamp -= prevStartSec;
        long currentInterval;

        try {
            writeStartLine();

            while (continueRun) {
                Thread.sleep(delayInt);
//                currentInterval = System.currentTimeMillis() - startTimeStamp + (4500 * 1000);
                currentInterval = System.currentTimeMillis() - startTimeStamp;

                String line = GetUTCdate(currentInterval)
                        .replace(" 0H", "   ")
                        .replace(" 0min", "     ")
                        .replace(" 0sec", "     ")
                        ;
                robot.keyPress(KeyEvent.VK_NUM_LOCK);
                robot.keyRelease(KeyEvent.VK_NUM_LOCK);
                robot.keyPress(KeyEvent.VK_NUM_LOCK);
                robot.keyRelease(KeyEvent.VK_NUM_LOCK);
                writeLog(line);
//                System.out.println(date);
            }
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            new Date();
        }

    }

    protected void writeStartLine() {
        if (!isTheSameStartDay) {
            writeToFile(logFile, startedOn + startDateTime);
//            listModel.
            startLinePrinted = true;
        }
        unlocker.addString(startedOn + startDateTime);
    }
    //                Date.from(Instant.ofEpochSecond(currentInterval));
    protected void writeLog(String line) throws IOException {
//        String prevStartDate = getStartDate();
//        String currentDate = getDateOnly(new Date());

        // Put "Elapsed time" line;
        if (startLinePrinted) {
            writeToFile(logFile, elapsedTime + line);
            startLinePrinted = false;
        } else {
            rewriteLastLine(logFile, elapsedTime + line);
        }
        unlocker.addString(elapsedTime + line);

    }

    public String getStartDate() throws IOException {
        String startDate = null;
        String startTimeInMilisecSec = null;
        String line = null;
        while ((line = ff.readFileLine()) != null)
            if (line.startsWith(startedOn))
                startDate = line;
            else
                startTimeInMilisecSec = line;

        ff.reopenReader();
        if (startDate == null)
            startDate = "";
        else
            startDate = startDate.substring(startedOn.length(), startDate.indexOf(" at "));

        if (startTimeInMilisecSec == null)
            startTimeInMilisecSec = "";
        else
            startTimeInMilisecSec = String.valueOf(getStartTimeAsMiliSeconds(startTimeInMilisecSec));

        return startDate + "|" + startTimeInMilisecSec;
    }

    protected String getStartTime(String line) {
        int pos = -1;
        StringBuffer res = new StringBuffer();
        pos = line.indexOf("Y ");
        if (pos >= 0)
            res.append(line.substring(pos - 2, pos + 1)).append(" ");
        pos = line.indexOf("D ");
        if (pos >= 0)
            res.append(line.substring(pos - 3, pos + 1)).append(" ");
        pos = line.indexOf("H ");
        if (pos >= 0)
            res.append(line.substring(pos - 2, pos + 1)).append(" ");
        pos = line.indexOf("min");
        if (pos >= 0)
            res.append(line.substring(pos - 2, pos + 3)).append(" ");
        pos = line.indexOf("sec");
        if (pos >= 0)
            res.append(line.substring(pos - 2, pos + 3));
        return res.toString();
    }

    protected long getStartTimeAsMiliSeconds(String line) {
        int pos;
        long seconds, ld = 0, lh = 0, lm = 0, ls = 0;
        String days, hours, mins, secs;

//        pos = line.indexOf("Y ");
//        if (pos >= 0) {
//            years = line.substring(pos - 2, pos).trim();
//            ly = Long.parseLong(years);
//        }

        pos = line.indexOf("D ");
        if (pos >= 0) {
            days = line.substring(pos - 5, pos).trim();
            ld = Long.parseLong(days);
        }

        pos = line.indexOf("H ");
        if (pos >= 0) {
            hours = line.substring(pos - 2, pos).trim();
            lh = Long.parseLong(hours);
        }

        pos = line.indexOf("min");
        if (pos >= 0) {
            mins = line.substring(pos - 2, pos).trim();
            lm = Long.parseLong(mins);
        }

        pos = line.indexOf("sec");
        if (pos >= 0) {
            secs = line.substring(pos - 2, pos).trim();
            ls = Long.parseLong(secs);
        }

        seconds = ls + (lm * 60) + (lh * 60 * 60) + (ld * 60 * 60 * 24)
//                + (ly * 60 * 60 * 24 * 365)
        ;

        return seconds * 1000;
    }

}
