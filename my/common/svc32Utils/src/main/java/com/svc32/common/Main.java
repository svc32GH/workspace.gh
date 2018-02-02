package com.svc32.common;

import com.svc32.common.svc32Utils.file.FileFunctions;

import java.util.Properties;

public class Main {
    private static final String propertyFilePath = "C:\\tmp\\CompanyLocalization_ru_RU.properties";
    private static final String propertyUnicodeFilePath = "C:\\tmp\\CompanyLocalizationUnicode_ru_RU.properties";

    public static void main(String[] args) {
        Properties propsIn = FileFunctions.readProperties(propertyFilePath, "windows-1251");
        Properties propsOut = new Properties();
        for (Object key : propsIn.keySet()) {
            String sKey = (String) key;
            String value = propsIn.getProperty(sKey);
            String valueEscUni = FileFunctions.escapeUnicode(value);
            System.out.println(valueEscUni);
            propsOut.setProperty(sKey, valueEscUni);
        }

        FileFunctions.writeProperties(propsOut, propertyUnicodeFilePath);

        System.out.println("props:\n" + propsIn);
    }
}
