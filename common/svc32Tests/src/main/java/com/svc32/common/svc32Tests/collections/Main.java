package com.svc32.common.svc32Tests.collections;

import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Map<Integer, String> treeMap = new TreeMap();
        Integer key = new Integer(0);
        String value;

        key = 32;
        value = "String_1";
        treeMap.put(key,value);

        key = 12;
        value = "String_2";
        treeMap.put(key,value);

        key = 88;
        value = "String_3";
        treeMap.put(key,value);

        for (Integer i : treeMap.keySet()) {
            System.out.println(i);
        }

        System.out.println();
        for (String str : treeMap.values()) {
            System.out.println(str);
        }
    }

}
