package com.svc32.common.unlocker;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import static com.svc32.common.svc32Utils.date.DTFormats.*;
import static com.svc32.common.svc32Utils.file.FileFunctions.*;

public class TestUnlockLogWriter {
    private static final String path = "C:\\ws.git\\my\\common\\UnLocker\\WorkLog.txt";

    @Test
    public void testGetStartDate() throws IOException {
//        System.out.println(args);


        UnlockLogWriter ulw = new UnlockLogWriter(new File(path));
        String startDate = ulw.getStartDate();
        System.out.println("startDate   = " + startDate);

        String currentDate = getDateOnly(new Date());
        System.out.println("currentDate = " + currentDate);

    }

//    @Test
//    public void testSysProps() {
//        Properties properties = System.getProperties();
//        for (Object key : properties.keySet())
//            System.out.println(String.format("%1$40s = %2$-80s", key, properties.get(key)));
//    }

    @Test
    public void testReadLastLine() {
        String lastLine = rewriteLastLine(new File(path), "Elapsed time:         1H 15min 12sec");
        System.out.println("lastLine = " + lastLine);

    }

}
