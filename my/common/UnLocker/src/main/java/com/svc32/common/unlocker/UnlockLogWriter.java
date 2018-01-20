package com.svc32.common.unlocker;

import java.util.Date;
//import com.svc32.common.svc32Utils;

public class UnlockLogWriter implements Runnable {
    private boolean continueRun;

    public void UnlockLogWriter() {
        this.continueRun = true;
    }

    public void stopRunning() {
        continueRun = false;
    }

    public void run() {
        try {
            while (continueRun) {
                Thread.sleep(1000);
                System.out.println(new Date());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void writeLog() {
//        com.svc32.common.svc32Utils.file.FileFunctions ff = new com.svc32.common.svc32Utils.fileFileFunctions();
    }

}
