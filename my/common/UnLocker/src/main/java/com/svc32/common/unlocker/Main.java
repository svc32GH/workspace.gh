package com.svc32.common.unlocker;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.svc32.common.svc32Utils.file.FileFunctions.*;

public class Main {
//    private final static String path = "C:\\ws.git\\my\\common\\UnLocker\\WorkLog.log";
    private final static String path = "D:\\ws.git\\my\\common\\UnLocker\\WorkLog.log";

    /**
     * @param  args
     *         -lp=logFilePath, e.g. -lp=D:\ws.git\my\common\UnLocker\WorkLog.log
     *             if not provided: log file will be created by path: @user.home\WorkLog.txt
     */
    public static void main(String[] args) throws IOException {

        String logFilePath = getFilePath(args);
        System.out.println(logFilePath);

        new Main().run(logFilePath);
    }

    private void run(String logFilePath) throws IOException {
        File currentLogPath = getLogFile(logFilePath);
        Unlocker ul = new Unlocker(currentLogPath);
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

}
