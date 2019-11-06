package com.svc32.common.pc;

import java.util.*;

public class PhoneMap extends HashMap<String, List<String>> {
    private Map<String, List<String>> ownerMap = new TreeMap();

    public PhoneMap() {
    }

    public void put(String phone, String owner) {
        List owners = get(phone);
        if (owners == null) {
            owners = new ArrayList();
            owners.add(owner);
            put(phone, owners);
        } else {
            owners.add(owner);
            ownerMap.put(phone, owners);
        }
    }

    public Map<String, List<String>> getDuplicates() {
        return ownerMap;
    }
}
