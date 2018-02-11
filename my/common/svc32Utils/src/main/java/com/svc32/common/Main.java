package com.svc32.common;

import com.svc32.common.svc32Utils.file.FileFunctions;

import java.io.*;
import java.util.Properties;

public class Main {
    private static final String propertyFilePath = "C:\\tmp\\CompanyLocalization_ru_RU.properties";
    private static final String propertyUnicodeFilePath = "C:\\tmp\\CompanyLocalizationUnicode_ru_RU.properties";
    private static final String propertyUnicodeFilePath2 = "C:\\tmp\\CompanyLocalizationUnicode_ru_RU2.properties";

    public static void main(String[] args) throws IOException {
        Properties propsIn = FileFunctions.readProperties(propertyFilePath, "windows-1251");
        Properties propsOut = new Properties();
        PrintWriter pw = new PrintWriter(new FileWriter(propertyUnicodeFilePath2));
        for (Object key : propsIn.keySet()) {
            String sKey = (String) key;
            String value = propsIn.getProperty(sKey);
            String valueEscUni = FileFunctions.escapeUnicode(value);
            System.out.println(valueEscUni);
            propsOut.setProperty(sKey, valueEscUni);

            pw.println(sKey + "=" + valueEscUni);
        }
        pw.close();

        FileFunctions.writeProperties(propsOut, propertyUnicodeFilePath);

        System.out.println("propsIn:\n" + propsIn);
        System.out.println("propsOut:\n" + propsOut);
    }
}
