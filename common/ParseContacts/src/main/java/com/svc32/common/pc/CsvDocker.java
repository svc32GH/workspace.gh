package com.svc32.common.pc;

import com.opencsv.CSVReader;
import com.svc32.common.svc32Utils.file.FileFunctions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvDocker {
    private List<List<String>> csvRecords;
    private Map<String, Integer> headerIndexes;

    public CsvDocker(String path) throws IOException {
        this.headerIndexes = new HashMap();
        this.csvRecords = new ArrayList<List<String>>();

        try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                csvRecords.add(Arrays.asList(values));
            }
        }

        List<String> headers = getHeaders();
        for (int i=0; i<getHeadCount(); i++) {
            String header = headers.get(i);
            headerIndexes.put(header, Integer.valueOf(i));
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

    public String getColumnValue(String name, int index) {
        String res = null;
        int headIndex = headerIndexes.get(name);
        if (headIndex >= 0) {
            if (index < csvRecords.size()) {
                List<String> row = csvRecords.get(index);
                res = row.get(headIndex);
            } else
                res = "Input index = " + index + " is out of range";
        } else
            res = "Column '" + name + "' does not exist";
        return res;
    }

    public int size() {
        return this.csvRecords.size();
    }

}
