package com.svc32.common.crosswordassistant;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class CA {
//    private static final String vocZipFile = "russian.zip";
    private static final String vocZipFile = "lop2v2.zip";
    private CrossSet cSet;

    public static void main(String[] args) {
        new CA().run();
    }

    private void run() {
        String allowedSymbols = null;
        this.cSet = getCrossSetFromZip(vocZipFile);
        while (!"q".equalsIgnoreCase(allowedSymbols)
                && !"й".equalsIgnoreCase(allowedSymbols)) {
            Scanner in = new Scanner(System.in);
            System.out.println("Input allowed symbols:   ");
            allowedSymbols = in.nextLine();
            if ("q".equalsIgnoreCase(allowedSymbols)
                    || "й".equalsIgnoreCase(allowedSymbols))
                break;
            String wordPattern = null;
            while (!"q".equalsIgnoreCase(wordPattern)
                    && !"й".equalsIgnoreCase(wordPattern)) {
                System.out.println("Input crossword pattern: ");
                wordPattern = in.nextLine();

                System.out.println(""
                        + "\nallowed symbols:   " + allowedSymbols
                        + "\ncrossword pattern: " + wordPattern
                );

                String pattern = getPattern(wordPattern);
                List<String> suitableWords = this.cSet.getSuitableWords(allowedSymbols, pattern);
                printAdvice(suitableWords);
            }
        }

    }

    public void printAdvice(List<String> suitableWords) {
        if (suitableWords.size() > 0) {
            System.out.println("Suitable words (count = " + suitableWords.size() + "):");
            for (String word : suitableWords) {
                System.out.println(word);
            }
        } else
            System.out.println("Suitable words are absent!!");
    }


    public static String getPattern(String crossPattern) {
        String res = crossPattern.replace("*", ".")
                .replace(" ", ".");
        return res;
    }

    public CrossSet getCrossSetFromZip(String fileName) {
        CrossSet cSet = new CrossSet();
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream is = classLoader.getResourceAsStream(vocZipFile);
            BufferedInputStream bis = new BufferedInputStream(is);
            ZipInputStream zis = new ZipInputStream(bis, Charset.forName("Windows-1251"));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("entry: " + entry.getName() + ", " + entry.getSize());
                String result = IOUtils.toString(zis, Charset.forName("Windows-1251"));
                String[] words = result.split("\n");
                int len = words.length;
                for (int i = 0; i < len; i++) {
                    String word = words[i].replace("\r", "");
                    cSet.addWord(word);
                }
                System.out.println("words count = " + len);
                System.out.println("Cross Set   = " + cSet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cSet;
    }

    public CrossSet getCrossSet(String fileName) {

        Set<String> voc = new TreeSet();
        ClassLoader classLoader = getClass().getClassLoader();
        CrossSet cSet = new CrossSet();

        try (InputStream is = classLoader.getResourceAsStream(fileName)) {

            String result = IOUtils.toString(is, Charset.forName("Windows-1251"));
            String[] words = result.split("\n");
            int len = words.length;
            for (int i = 0; i < len; i++) {
                String word = words[i].replace("\r", "");
                voc.add(word);
                cSet.addWord(word);
            }
            System.out.println("words count = " + len);
            System.out.println("Cross Set   = " + cSet);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cSet;
    }
}

