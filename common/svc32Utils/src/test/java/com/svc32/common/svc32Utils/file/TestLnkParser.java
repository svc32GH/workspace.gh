package com.svc32.common.svc32Utils.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class TestLnkParser {
    private static final String path = "C:\\Users\\Serge\\Downloads\\2009 - 2019 - Shortcut.lnk";

    @Test
    public void testWindowsShortcut() throws IOException, ParseException {
        File file = new File(path);
        WindowsShortcut ws = new WindowsShortcut(file);
        boolean isDir = ws.isDirectory();
        boolean isLoc = ws.isLocal();
        String rfn = ws.getRealFilename();
//        File rfnFile = new File(rfn);
        System.out.println("isDir       = " + isDir);
        System.out.println("isLoc       = " + isLoc);
        System.out.println("rfn         = " + rfn);
        System.out.println("file.name   = " + file.getName());
    }

    @Test
    public void testWindowsShortcut2() throws IOException, ParseException {
        File file = new File(path);
        WindowsShortcut2 ws = new WindowsShortcut2(file);
        String wDir = ws.getWorkingDirectory();
        String rPath = ws.getRelativePath();
        String descr = ws.getDescription();
        String cla = ws.getCommandLineArguments();
        String rfn = ws.getRealFilename();
        boolean isDir = ws.isDirectory();
        boolean isLoc = ws.isLocal();
        System.out.println("wDir  = " + wDir);
        System.out.println("rPath = " + rPath);
        System.out.println("descr = " + descr);
        System.out.println("cla   = " + cla);
        System.out.println("rfn   = " + rfn);
        System.out.println("isDir = " + isDir);
        System.out.println("isLoc = " + isLoc);
    }

    @Test
    public void testLnkParser() throws IOException {
        File file = new File(path);
        LnkParser lp = new LnkParser(file);
        boolean isDir = lp.isDirectory();
        boolean isLoc = lp.isLocal();
        String rfn = lp.getRealFilename();
        System.out.println("isDir = " + isDir);
        System.out.println("isLoc = " + isLoc);
        System.out.println("rfn   = " + rfn);

    }
}
