package com.svc32.common.unlocker;

import com.svc32.common.svc32Utils.file.FileFunctions;

import java.io.File;
import java.util.Date;
//import com.svc32.common.svc32Utils;

public class UnlockLogWriter implements Runnable {
    private boolean continueRun;
    private File logFile;

    public UnlockLogWriter(File logFile) {
        this.logFile = logFile;
        this.continueRun = true;
    }

    public void stopRunning() {
        continueRun = false;
    }

    public void run() {
        try {
            while (continueRun) {
                Thread.sleep(1000);
                Date date = new Date();
                writeLog(date.toString());
//                System.out.println(date);
            }
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        } finally {

        }

    }

    private void writeLog(String line) {
        FileFunctions.writeFile(logFile, line);
    }

}
