package com.svc32.common.svc32Utils.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestFileFunctions {
    private static final String path = "C:\\wsRoot.git\\svc32WS\\common\\UnLocker\\WorkLog2.txt";

    @Test
    public void testReadFilelLines() throws IOException {
        File file = new File(path);
        FileFunctions ff = new FileFunctions(file);
        List<String> list = ff.readFileLines();
        for (String str : list)
            System.out.println(str);
    }
}
