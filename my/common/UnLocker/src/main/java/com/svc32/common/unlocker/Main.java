package com.svc32.common.unlocker;


public class Main {
    private final static String path = "C:\\ws.git\\my\\common\\UnLocker\\WorkLog.log";
//    private final static String path = "D:\\ws.git\\my\\common\\UnLocker\\WorkLog.log";

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Unlocker ul = new Unlocker(path);
        ul.setName("frmUnlocker");

        System.out.println(ul);
    }
}
