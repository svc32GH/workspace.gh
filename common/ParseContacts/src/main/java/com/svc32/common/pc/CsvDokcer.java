package com.svc32.common.pc;

import com.opencsv.CSVReader;
import com.svc32.common.svc32Utils.file.FileFunctions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvDokcer {
    private List<List<String>> csvRecords;

    public CsvDokcer(String path) throws IOException {
        this.csvRecords = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                csvRecords.add(Arrays.asList(values));
            }
        }


    }

    public List<List<String>> getCsvRows() {
        return csvRecords;
    }

    public int getHeadCount() {
        return csvRecords.get(0).size();
    }

    public List<String> getHeaders() {
        return csvRecords.get(0);
    }

}
