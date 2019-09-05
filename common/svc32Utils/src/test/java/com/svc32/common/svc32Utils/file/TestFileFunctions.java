package com.svc32.common.svc32Utils.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestFileFunctions {
    private static final String path = "D:\\wsRoot.gh\\svc32WS\\common\\svc32Utils\\WorkLog2.txt";

    @Test
    public void testReadFilelLines() throws IOException {
        File file = new File(path);
        FileFunctions ff = new FileFunctions(file);
        List<String> list = ff.readFileLines();
        for (String str : list)
            System.out.println(str);
    }
}
