package com.svc32.common.unlocker;

import static com.svc32.common.svc32Utils.file.FileFunctions.*;
import static com.svc32.common.svc32Utils.date.DTFormats.*;

import java.io.File;
import java.util.Date;

public class UnlockLogWriter implements Runnable {
    private static final int delayInt = 1000;

    private boolean continueRun;
    private File logFile;
    private Date startTimeStamp = new Date();

    public UnlockLogWriter(File logFile) {
        this.logFile = logFile;
        this.continueRun = true;
    }

    public void stopRunning() {
        continueRun = false;
    }

    public void run() {
        int startT, currentT = 0;
        writeLog("UnLocker started on " + DateTimeSecZ(startTimeStamp));

        try {
            while (continueRun) {
                Thread.sleep(delayInt);
                currentT += delayInt;

                writeLog("Elapsed time = " + currentT);
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
