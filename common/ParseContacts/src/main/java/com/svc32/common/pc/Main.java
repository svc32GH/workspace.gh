package com.svc32.common.pc;

import com.opencsv.CSVReader;
import com.svc32.common.svc32Utils.file.FileFunctions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.svc32.common.svc32Utils.file.FileFunctions.readFileRows;

public class Main {
    public static final String csvPath = "D:\\SVC\\doc\\Private Data\\contacts.csv";
    public static final String sep = "\",\"";
    public static final String comma = ",";
    public static final String doubleQuote = "\"";

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException {

//        List<List<String>> records = new ArrayList<List<String>>();
//        try (CSVReader csvReader = new CSVReader(new FileReader(csvPath));) {
//            String[] values = null;
//            while ((values = csvReader.readNext()) != null) {
//                records.add(Arrays.asList(values));
//            }
//        }


        CsvDokcer csvDokcer = new CsvDokcer(csvPath);
        List<String> headers = csvDokcer.getHeaders();
        System.out.println(headers);

//        for (int i=0; i<csvRows.size(); i++) {
//            System.out.println(csvRows.get(i));
//        }
    }
}
