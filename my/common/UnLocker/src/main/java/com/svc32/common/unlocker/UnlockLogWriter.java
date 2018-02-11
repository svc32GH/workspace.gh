package com.svc32.common.unlocker;

import com.svc32.common.svc32Utils.file.FileFunctions;

import static com.svc32.common.svc32Utils.file.FileFunctions.*;
import static com.svc32.common.svc32Utils.date.DTFormats.*;
import static com.svc32.common.svc32Utils.file.FileFunctions.rewriteLastLine;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class UnlockLogWriter implements Runnable {
    private static final int delayInt = 1000;
    private static final String startedOn = "Started on ";
    private static final String elapsedTime = "Elapsed time: ";

    private boolean continueRun;
    private final String startDateTime = getDateTimeSecZ(new Date());

    private long startTimeStamp;
    private File logFile;
    private final FileFunctions ff;

    public UnlockLogWriter(File logFile) throws IOException {
        this.logFile = logFile;
        this.continueRun = true;
        this.ff = new FileFunctions(logFile);
//        this.startDateTime = System.currentTimeMillis();
    }

    public void stopRunning() {
        continueRun = false;
    }

    public void run() {
        startTimeStamp = System.currentTimeMillis();
        long currentInterval;

        try {
            writeLog("");

            while (continueRun) {
                Thread.sleep(delayInt);
                currentInterval = System.currentTimeMillis() - startTimeStamp + (4500 * 1000);
//                currentInterval = System.currentTimeMillis() - startTimeStamp;

                String line = GetUTCdate(currentInterval)
                        .replace("0H", "  ")
                        .replace("0min", "    ");
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

    //                Date.from(Instant.ofEpochSecond(currentInterval));
    private void writeLog(String line) throws IOException {
        String startDate = getStartDate();
        String currentDate = getDateOnly(new Date());

        // Put "Started on" line:
        if (line.length() == 0 && !currentDate.equals(startDate)) {
            writeToFile(logFile, startedOn + startDateTime);
        }
        // Put "Elapsed time" line;
        else {
            if (!currentDate.equals(startDate))
                writeToFile(logFile, elapsedTime + line);
            else
                rewriteLastLine(logFile, elapsedTime + line);
        }

    }

    public String getStartDate() throws IOException {
        String startDate = null;
        String line = null;
        while ((line = ff.readFileLine()) != null)
            if (line.startsWith(startedOn))
                startDate = line;

        ff.reopenReader();
        if (startDate == null)
            return "";
        else
            return startDate.substring(startedOn.length(), startDate.indexOf(" at "));
    }

}
