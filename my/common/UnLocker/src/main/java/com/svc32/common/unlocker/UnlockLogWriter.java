package com.svc32.common.unlocker;

import static com.svc32.common.svc32Utils.file.FileFunctions.*;
import static com.svc32.common.svc32Utils.date.DTFormats.*;

import java.io.File;
import java.util.Date;

public class UnlockLogWriter implements Runnable {
    private static final int delayInt = 1000;

    private boolean continueRun;
    private File logFile;
    private long startTimeStamp;

    public UnlockLogWriter(File logFile) {
        this.logFile = logFile;
        this.continueRun = true;
    }

    public void stopRunning() {
        continueRun = false;
    }

    public void run() {
        startTimeStamp = System.currentTimeMillis();
        long currentInterval;
        writeLog("Started on " + DateTimeSecZ(new Date()));

        try {
            while (continueRun) {
                Thread.sleep(delayInt);
//                currentInterval = System.currentTimeMillis() - startTimeStamp + 55000;
                currentInterval = System.currentTimeMillis() - startTimeStamp;

                String line = GetUTCdate(currentInterval)
                        .replace("0H", "  ")
                        .replace("0min", "    ");
                writeLog("Elapsed time: " + line);
//                System.out.println(date);
            }
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        } finally {

        }

    }

    private void writeLog(String line) {
        writeToFile(logFile, line);
    }

}
