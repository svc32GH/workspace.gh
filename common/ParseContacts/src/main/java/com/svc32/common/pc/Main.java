package com.svc32.common.pc;

import com.svc32.common.svc32Utils.map.StringListMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static final String csvPath = "D:\\SVC\\doc\\Private Data\\contacts.csv";
    public static final String colA = "Name";
    public static final String colB = "Given Name";
    public static final String colD = "Family Name";
    public static final String colAP = "Phone 1 - Value";
    public static final String colAR = "Phone 2 - Value";

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException {
        CsvDocker csvDokcer = new CsvDocker(csvPath);
        StringListMap stringListMap = new StringListMap();

        for (int i = 0; i < csvDokcer.size(); i++) {
            String phoneString = csvDokcer.getColumnValue(colAP, i);
            String owner = csvDokcer.getColumnValue(colA, i);
            String[] phones = phoneString.split(" ::: ");
            for (int j = 0; j < phones.length; j++) {
                String phone = phones[j].replace(" ", "")
                        .replace("+38", "")
                        .replace("38", "");
                if (phone.length() > 0)
                    stringListMap.put(phone, owner);
            }
        }
        Map<String, List<String>> duplicates = stringListMap.getDuplicates();
        Object[] keySet = duplicates.keySet().toArray();
        for (int i = 0; i < keySet.length; i++) {
            String key = (String) keySet[i];
            List<String> owners = duplicates.get(key);
            System.out.println(key + " : " + owners);
        }
    }
}
