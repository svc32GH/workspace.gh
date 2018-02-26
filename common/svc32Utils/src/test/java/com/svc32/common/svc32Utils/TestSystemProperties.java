package com.svc32.common.svc32Utils;

import org.junit.Test;

import java.util.Properties;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 04.02.2018.
 **/
public class TestSystemProperties {

    @Test
    public void testSysProps() {
        Properties properties = System.getProperties();
        for (Object key : properties.keySet())
            System.out.println(String.format("%1$40s = %2$-80s", key, properties.get(key)));
    }
}
