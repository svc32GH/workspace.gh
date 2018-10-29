package com.svc32.common;

import com.svc32.common.svc32Utils.file.FileFunctions;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommonTests {

    @Test
    public void testReverSymbols() throws IOException {
        String path = "SomeText.txt";
        File file = new File(path);
        String rows = FileFunctions.readFileRowsFromClassPath(path);
        System.out.println(rows);
    }
}
