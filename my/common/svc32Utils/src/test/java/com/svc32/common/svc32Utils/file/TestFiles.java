package com.svc32.common.svc32Utils.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.svc32.common.svc32Utils.file.FileFunctions.*;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 04.02.2018.
 **/
public class TestFiles {
    private static final String nullPath = null;
    private static final String emptyPath = "";
    private static final String incorrectPath = "D:\\1\\2\\3:3\\4.txt";
    private static final String noExtensionPath = "D:\\11\\22\\33";
    private static final String correctPath = "D:\\111\\222\\333.log";
    private static final String correctPath2 = "D:\\ws.gh\\my\\common\\UnLocker";

    @Test
    public void test_getLogFile() throws IOException {
        System.out.println("nullPath:        " + getLogFile(nullPath).getAbsolutePath());
        System.out.println("emptyPath:       " + getLogFile(emptyPath).getAbsolutePath());
        System.out.println("incorrectPath:   " + getLogFile(incorrectPath).getAbsolutePath());
        System.out.println("noExtensionPath: " + getLogFile(noExtensionPath).getAbsolutePath());
        System.out.println("correctPath:     " + getLogFile(correctPath).getAbsolutePath());
        System.out.println("correctPath2:    " + getLogFile(correctPath2).getAbsolutePath());

    }

    @Test
    public void testRegExp2() {
        System.out.println("incorrectPath      = " + isFileNameCorrect(incorrectPath));
        System.out.println("noExtensionPath    = " + isFileNameCorrect(noExtensionPath));
        System.out.println("correctPath        = " + isFileNameCorrect(correctPath));
    }

    @Test
    public void testRegExp() {
        System.out.println("correctPath1              = " + correctPath1);
        System.out.println("correctPath2              = " + correctPath2);
        System.out.println("isFileNameCorrectFind1    = " + isFileNameCorrectFind(correctPath1));
        System.out.println("isFileNameCorrectMatches1 = " + isFileNameCorrectMatches(correctPath1));
        System.out.println("isFileNameCorrectFind2    = " + isFileNameCorrectFind(correctPath2));
        System.out.println("isFileNameCorrectMatches2 = " + isFileNameCorrectMatches(correctPath2));
    }

}
