package com.svc32.common.svc32Utils.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static com.svc32.common.svc32Utils.date.DTFormats.*;

public class TestDates {
    private static final long[] nDaysArray = {0L, 9L, 99L, 999L, 9999L, 99999L};
    public static final long oneHour16mins = 60L * 60L + 60L * 16L;
    public static final long oneHour02mins = 60L * 60L + 60L * 2L;
    public static final long tenHour16mins = 10L *60L * 60L + 60L * 16L;
    public static final long tenHour02mins = 10L *60L * 60L + 60L * 2L;

    @Test
    public void test_getNumberOfDays() {
        for (int i=0; i<6; i++  )
            System.out.println("| " + getNumberOfDays(nDaysArray[i]) + " |");
    }

    @Test
    public void test_GetUTCdate() {
        long k = 24 * 60 * 60;
        for (int i=0; i<6; i++  )
            System.out.println("| " + GetUTCdate(nDaysArray[i] * k + oneHour16mins) + " |");

        System.out.println("\n| " + GetUTCdate(oneHour16mins) + " |");
        System.out.println("| " + GetUTCdate(oneHour02mins) + " |");
        System.out.println("| " + GetUTCdate(tenHour16mins) + " |");
        System.out.println("| " + GetUTCdate(tenHour02mins) + " |");
    }
}
