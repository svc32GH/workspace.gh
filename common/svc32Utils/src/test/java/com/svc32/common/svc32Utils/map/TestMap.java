package com.svc32.common.svc32Utils.map;

import org.junit.Test;

import java.util.Set;

import static junit.framework.Assert.assertEquals;

public class TestMap {

    @Test
    public void testM2MMap() {
        M2MMap<String, String> m2mMap = new M2MMap<>();
        m2mMap.put("aaa", "111");
        m2mMap.put("aaa", "222");
        m2mMap.put("aaa", "333");
        m2mMap.put("bbb", "111");
        m2mMap.put("bbb", "444");

        assertEquals("m2mMap.getSize() should be equal to 2", 2, m2mMap.getSize());
        assertEquals("m2mMap.getSize('aaa') should be equal to 3", 3, m2mMap.getSize("aaa"));
        assertEquals("m2mMap.getSize('bbb') should be equal to 3", 2, m2mMap.getSize("bbb"));
        assertEquals("m2mMap.getSize('ccc') should be equal to 3", 0, m2mMap.getSize("ccc"));

        Set<String> valueSet = m2mMap.valueSet();
        assertEquals("m2mMap.valueSet().size() should be equal to 4", 4, valueSet.size());
        System.out.println(valueSet);

//        System.out.println("m2mMap.getSize()      = " + m2mMap.getSize());
//        System.out.println("m2mMap.getSize('aaa') = " + m2mMap.getSize("aaa"));
//        System.out.println("m2mMap.getSize('bbb') = " + m2mMap.getSize("bbb"));
//        System.out.println("m2mMap.getSize('ccc') = " + m2mMap.getSize("ccc"));
    }
}
