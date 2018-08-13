package com.svc32.common.unlocker;

import com.svc32.common.TimeStampValue;
import com.svc32.common.svc32Utils.file.FileFunctions;

import static com.svc32.common.svc32Utils.date.DTFormats.convertMs2H;
import static com.svc32.common.svc32Utils.file.FileFunctions.*;
import static com.svc32.common.svc32Utils.date.DTFormats.*;
import static com.svc32.common.svc32Utils.file.FileFunctions.rewriteLastLine;

import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;

public class UnlockLogWriter implements Runnable {
    private final int delayInt;
    //    private static final int _delayInt_test = 1000;
    private static final int _delayInt_test = 1000 * 5; // 5 sec
    private static final int _delayInt_run = 1000 * 60 * 5; // 5 min
    private static final String startedOnStr = "Started on ";
    private static final String elapsedTimeStr = "Elapsed time: ";
    private static final String elapsedTime0sec = "Elapsed time:                   0sec";
    private final String prevStartDate;
    private final String elapsedTime;
    private final long prevStartSec;
    private final String currentDate;

    public static final String START_STRING_PATTERN = "^.+(\\d{4}\\.\\d{2}\\.\\d{2}).*$";
    public static final String ELAPSED_STRING_PATTERN = "^.+\\s((\\d+H)?\\s+(\\d+min)?\\s+(\\d+sec)?).*$";
    public static final Pattern extractStartString = Pattern.compile(START_STRING_PATTERN);
    public static final Pattern extractElapsedTimeString = Pattern.compile(ELAPSED_STRING_PATTERN);

    private boolean continueRun;
    private boolean startLinePrinted = false;
    private boolean isTheSameStartDay = false;
    private final Date startDateTime = new Date();
    private final String startDateTimeStr = getDateTimeSecZ(new Date());
    private final Integer startYearInt = Integer.parseInt( startDateTimeStr.split("\\.")[0] );

    private long startTimeStamp;
    private File logFile;
    private final FileFunctions ff;
    private final Unlocker unlocker;
    private final Robot robot;

    public UnlockLogWriter(File logFile, Unlocker unlocker, Robot robot) throws IOException, ParseException {
        delayInt = unlocker.isTestMode() ? _delayInt_test : _delayInt_run;
        this.unlocker = unlocker;
        this.robot = robot;
        this.logFile = logFile;
        this.continueRun = true;
        this.ff = new FileFunctions(logFile);
//        this.startDateTimeStr = System.currentTimeMillis();

        String startDateString = getStartDate();
        String[] dateArray = startDateString.split("\\|");
        int len = dateArray.length;

        if (startDateString.equals("||")) {
            prevStartDate = "";
            elapsedTime = "";
            prevStartSec = 0;
        } else {
            if (len == 1) {
                prevStartDate = dateArray[0];
                elapsedTime = "";
                prevStartSec = 0;
            } else {
                prevStartDate = dateArray[0];
                String prevStartSecStr = dateArray[1];
                prevStartSec = Long.parseLong(prevStartSecStr);
                elapsedTime = dateArray[2];
                if (prevStartSec == 0 && elapsedTime.equals("Elapsed time:                   0sec")) {

                } else {

                }
            }
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
            prepareSummaryReport();
            while (continueRun) {
                Thread.sleep(delayInt);
//                currentInterval = System.currentTimeMillis() - startTimeStamp + (4500 * 1000);
                currentInterval = System.currentTimeMillis() - startTimeStamp;

                String line = GetUTCdate(currentInterval)
                        .replace(" 0H", "   ")
                        .replace(" 0min", "     ")
                        .replace(" 0sec", "     ");
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

    protected void prepareSummaryReport() {
        List<String> list;
        try {
            list = ff.readFileLines();
            Map<Integer, Long> whPerWeek = new TreeMap();


            String startString = list.get(0);
            String elapsedTimeString = list.get(1);
            TimeStampValue timeStampValue = new TimeStampValue(startString, elapsedTimeString);
            int weekNum = timeStampValue.getWeekOfYear();
            long weekElapsedTime = 0;

            Iterator it = list.iterator();
            while (it.hasNext()) {
                startString = (String) it.next();
                elapsedTimeString = (String) it.next();
                timeStampValue = new TimeStampValue(startString, elapsedTimeString);
                int currentWeekNum = timeStampValue.getWeekOfYear();

                if (currentWeekNum == 14) {
                    System.out.println(currentWeekNum);
                }

                long elapsedTime = timeStampValue.getElapsedTime();
                if (currentWeekNum == weekNum) {
                    weekElapsedTime += elapsedTime;
                } else {
                    whPerWeek.put(new Integer(weekNum), new Long(weekElapsedTime));
                    System.out.println(
                            String.format(
                                    "%1$s %2$2s %3$s %4$s %5$s",
                                    "Week N ", weekNum,
                                    "(" + getWeekDaysInterval(startYearInt, weekNum) + ")",
                                    "ElapsedTime = ", convertMs2H(weekElapsedTime)
                            )
                    );
                    weekElapsedTime = elapsedTime;
                    weekNum++;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

    }

    protected void writeStartLine() {
        unlocker.addString(startedOnStr + startDateTimeStr);
        if (!isTheSameStartDay) {
            writeToFile(logFile, startedOnStr + startDateTimeStr);
            startLinePrinted = true;
        } else {
            if (elapsedTime.equals(elapsedTime0sec) || elapsedTime.length() == 0)
                writeToFile(logFile, elapsedTime0sec);
        }

        if (isTheSameStartDay)
            unlocker.addString(elapsedTime);
    }

    //                Date.from(Instant.ofEpochSecond(currentInterval));
    protected void writeLog(String line) throws IOException {
//        String prevStartDate = getStartDate();
//        String currentDate = getDateOnly(new Date());

        // Put "Elapsed time" line;
        if (startLinePrinted) {
            writeToFile(logFile, elapsedTimeStr + line);
            startLinePrinted = false;
        } else {
            rewriteLastLine(logFile, elapsedTimeStr + line);
        }

        if (unlocker.getRowCount() == 2)
            unlocker.changeString(elapsedTimeStr + line);
        else
            unlocker.addString(elapsedTimeStr + line);
    }

    public String getStartDate() throws IOException, ParseException {
        String startDate = null;
        String prevStartDate = null;
        String timeElapsed = null;
        String startTimeInMilisecSec = null;
        String line = null;
        boolean elapsedIsLast = false;
        while ((line = ff.readFileLine()) != null) {
            prevStartDate = startDate;
            if (line.startsWith(startedOnStr)) {
                startDate = line;
                elapsedIsLast = false;
            } else {
                startTimeInMilisecSec = line;
                elapsedIsLast = true;
            }
        }
        ff.reopenReader();

//        String prevStartDateTimeStr = startDate.substring(11);
//        Date prevStartDateTime = parseDateTimeSecZ(prevStartDateTimeStr);
//        long diffL = startDateTime.getTime() - prevStartDateTime.getTime();
//        String diffS = convertMs2TimeInt(diffL);
//        System.out.println(prevStartDateTimeStr);

        if (startDate == null)
            startDate = "";
        else
            startDate = startDate.substring(startedOnStr.length(), startDate.indexOf(" at "));

        if (startTimeInMilisecSec == null) {
            timeElapsed = "";
            startTimeInMilisecSec = "";
        } else {
            if (elapsedIsLast) {
                timeElapsed = startTimeInMilisecSec;
                startTimeInMilisecSec = String.valueOf(getStartTimeAsMiliSeconds(startTimeInMilisecSec));
            } else {
                timeElapsed = elapsedTime0sec;
                startTimeInMilisecSec = "0";
            }
        }

        String res = startDate
                + "|" + startTimeInMilisecSec
                + "|" + timeElapsed;
        return res;
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
