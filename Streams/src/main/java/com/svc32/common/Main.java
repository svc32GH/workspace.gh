package com.svc32.common;

import java.io.*;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 1/21/2017.
 **/
public class Main {

    public static void main(String[] args) throws IOException {
        (new Main()).run();
    }

    public void run() throws IOException {
        String s = "ABCDEFÄäÜüÖöABCDEFÄäÜüÖöABCDEFÄäÜüÖöABCDEFÄäÜüÖöABCDEFÄäÜüÖöABCDEFÄäÜüÖöABCDEFÄäÜüÖöABCDEFÄäÜüÖABCDEFÄäÜüÖöö";
        System.out.println("s         = " + s);

        int buffSize = 32;
        char[] inBufer = new char[buffSize];
        Reader inStrReader = new StringReader(s);
//        Reader inStrReader = new CharArrayReader(s.toCharArray());

        byte[] b;
        OutputStream out = new ByteArrayOutputStream(buffSize);

        int count;
        while( ( count = inStrReader.read(inBufer) ) != -1 ) {
            String s_ = getString(inBufer, count);
            out.write( s_.getBytes() );
        }

        String converRes = out.toString();
        System.out.println("converRes = " + converRes);

    }

    private String getString(char[] array, int count) {
        StringBuffer sb = new StringBuffer();
        int len = count > array.length ? array.length : count;
        for (int i=0; i<len; i++)
            sb.append(array[i]);
        return sb.toString();
    }
}
