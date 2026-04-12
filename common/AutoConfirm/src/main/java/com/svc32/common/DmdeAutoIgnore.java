package com.svc32.common;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DmdeAutoIgnore {

    private static final User32 USER32 = User32.INSTANCE;
    private static final int BM_CLICK = 0x00F5;

    private static final Logger log = Logger.getLogger(DmdeAutoIgnore.class.getName());

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleWithFixedDelay(() -> {
            try {
                scanAndClick();
            } catch (Exception e) {
                log.log(Level.WARNING, "Error during scan", e);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down...");
            executor.shutdownNow();
        }));
    }

    private static void scanAndClick() throws InterruptedException {
        List<WindowEntry> windows = getAllWindowsDeep();

        boolean hasWinError1117 = false;
        boolean hasPleaseWait = false;

        HWND ignoreButton = null;
        HWND ignoreAllButton = null;

        for (WindowEntry entry : windows) {
            String text = normalize(entry.text);

            if (text.contains("winerror 1117")) {
                hasWinError1117 = true;
            }

            if (text.equals("please, wait...")) {
                hasPleaseWait = true;
            }

            if (text.equals("&ignore")) {
                ignoreButton = entry.hwnd;
            }

            if (text.equals("ignore all")) {
                ignoreAllButton = entry.hwnd;
            }
        }

        if (hasWinError1117 && hasPleaseWait) {
            if (ignoreButton != null) {
                System.out.println("Clicking &Ignore on "+new Date());
                USER32.SendMessage(ignoreButton, BM_CLICK, new WPARAM(0), new LPARAM(0));
                Thread.sleep(1200);
            } else if (ignoreAllButton != null) {
                System.out.println("Clicking Ignore all on "+new Date());
                USER32.SendMessage(ignoreAllButton, BM_CLICK, new WPARAM(0), new LPARAM(0));
                Thread.sleep(1200);
            }
        }
    }

    private static List<WindowEntry> getAllWindowsDeep() {
        List<WindowEntry> result = new ArrayList<WindowEntry>();

        USER32.EnumWindows((hwnd, data) -> {
            addWindowIfHasText(result, hwnd);

            USER32.EnumChildWindows(hwnd, (child, data2) -> {
                addWindowIfHasText(result, child);
                return true;
            }, Pointer.NULL);

            return true;
        }, Pointer.NULL);

        return result;
    }

    private static void addWindowIfHasText(List<WindowEntry> result, HWND hwnd) {
        String text = getWindowText(hwnd);
        if (text != null && !text.trim().isEmpty()) {
            result.add(new WindowEntry(hwnd, text));
        }
    }

    private static String getWindowText(HWND hwnd) {
        char[] buffer = new char[2048];
        int len = USER32.GetWindowText(hwnd, buffer, buffer.length);
        if (len <= 0) {
            return "";
        }
        return Native.toString(buffer);
    }

    private static String normalize(String s) {
        if (s == null) {
            return "";
        }
        return s.replace('\u00A0', ' ')
                .replaceAll("\\s+", " ")
                .trim()
                .toLowerCase(Locale.ROOT);
    }

    private static class WindowEntry {
        private final HWND hwnd;
        private final String text;

        private WindowEntry(HWND hwnd, String text) {
            this.hwnd = hwnd;
            this.text = text;
        }
    }
}