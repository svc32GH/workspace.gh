package com.svc32.common.unlocker;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.svc32.common.svc32Utils.file.FileFunctions.*;

public class Main {
//    private final static String path = "C:\\ws.git\\my\\common\\UnLocker\\WorkLog.log";
//    private final static String path = "D:\\ws.git\\my\\common\\UnLocker\\WorkLog.log";

    /**
     * @param  args
     *         -lp=logFilePath, e.g. -lp=D:\ws.git\my\common\UnLocker\WorkLog.log
     *             if not provided: log file will be created by path: @user.home\WorkLog.txt
     */
    public static void main(String[] args) throws IOException {

        String logFilePath = getFilePath(args);
        System.out.println("log file: " + logFilePath);
        boolean isTestMode = getRunMode(args);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Main().run(logFilePath, isTestMode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void run(String logFilePath, boolean runMode) throws IOException {
        File currentLogPath = getLogFile(logFilePath);
        Unlocker ul = null;
        ul = new Unlocker(currentLogPath, runMode);
        ul.setName("frmUnlocker");

//        System.out.println("ul:\n" + ul);
    }

    private static String getFilePath(String[] args) {
        StringBuilder res = new StringBuilder("");
        for (int i=0; i<args.length; i++)
            if (args[i].startsWith("-lp=")) {
                res.append(args[i].replace("-lp=", ""));
                break;
            }
        return res.toString();
    }

    // return true = Run in Test Mode
    // return fale = Run in Ordinary Mode
    private static boolean getRunMode(String[] args) {
        boolean res = false;
        for (int i = 0; i < args.length; i++)
            if (args[i].startsWith("-t=y") || args[i].startsWith("-t=Y")) {
                res = true;
                break;
            }
        return res;
    }

}
