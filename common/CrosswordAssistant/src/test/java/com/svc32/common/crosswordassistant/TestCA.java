package com.svc32.common.crosswordassistant;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCA {
    private static final String vocZipFile = "lop2v2.zip";
//    private static final String vocZipFile = "russian.zip";

    @Test
    public void commonTests() {
        String checkStr = "мало";
        String checkStr2 = "мыло";
        String strPattern = ".ы.о";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(checkStr);
        boolean res = m.matches();

        System.out.println("res = " + res);

        m = p.matcher(checkStr2);
        res = m.matches();

        System.out.println("res = " + res);

        System.out.println();
        System.out.println(checkStr.replaceFirst("а", ""));
        String b = checkStr.replaceFirst("а", "");
        System.out.println(b);
    }

    @Test
    public void testArray() {
        String s = "sdsgffd";
        String[] arr = s.split("(?!^)");
        for (int i = 0; i < arr.length; i++)
            System.out.println("arr[" + i + "] = " + arr[i]);
    }

    @Test
    public void testCompareWords() {
        String allowedSymbols = "моооллк";
        String word = "молоко";
        assertTrue(CrossSet.checkWord(allowedSymbols, word));
        word = "молокко";
        assertFalse(CrossSet.checkWord(allowedSymbols, word));
        word = "малоко";
        assertFalse(CrossSet.checkWord(allowedSymbols, word));
    }

    @Test
    public void testPatternConverter() {
        String pattern = "*s*  w**";
        assertTrue(CA.getPattern(pattern).equals(".s...w.."));
    }

    @Test
    public void testReadZip() {
        ZipFile zipFile = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream is = classLoader.getResourceAsStream(vocZipFile);
            BufferedInputStream bis = new BufferedInputStream(is);
            ZipInputStream zis = new ZipInputStream(bis, Charset.forName("Windows-1251"));

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("entry: " + entry.getName() + ", " + entry.getSize());
                String result = IOUtils.toString(zis, Charset.forName("Windows-1251"));
                // I could close the entry, but getNextEntry does it automatically
                // zis.closeEntry()
                String[] arrRes = result.split("\n");
                System.out.println("count                   = " + arrRes.length
                        + "\narrRes[0]               = " + arrRes[0]
                        + "\narrRes[1]               = " + arrRes[1]
                        + "\narrRes[2]               = " + arrRes[2]
                        + "\narrRes[3]               = " + arrRes[3]
                        + "\narrRes[4]               = " + arrRes[4]
                        + "\narrRes[arrRes.length-6] = " + arrRes[arrRes.length - 6]
                        + "\narrRes[arrRes.length-5] = " + arrRes[arrRes.length - 5]
                        + "\narrRes[arrRes.length-4] = " + arrRes[arrRes.length - 4]
                        + "\narrRes[arrRes.length-3] = " + arrRes[arrRes.length - 3]
                        + "\narrRes[arrRes.length-2] = " + arrRes[arrRes.length - 2]
                        + "\narrRes[arrRes.length-1] = " + arrRes[arrRes.length - 1]
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
