package com.svc32.common.svc32Utils.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
        long k = 24 * 60 * 60 * 1000;
        for (int i=0; i<6; i++  )
            System.out.println("| " + GetUTCdate(nDaysArray[i] * k + oneHour16mins) + " |");

        System.out.println("\n| " + GetUTCdate(oneHour16mins * 1000) + " |");
        System.out.println("| " + GetUTCdate(oneHour02mins * 1000) + " |");
        System.out.println("| " + GetUTCdate(tenHour16mins * 1000) + " |");
        System.out.println("| " + GetUTCdate(tenHour02mins * 1000) + " |");
    }

    @Test
    public void testParseDate() throws ParseException {
        String dateString = "2016.03.17 at 10:59:27 EET";
        Date date = parseDateTimeSecZ(dateString);
        String dateStringRenew = getDateTimeSecZ(date);
        System.out.println("dateString      = " + dateString);
        System.out.println("date            = " + date);
        System.out.println("dateStringRenew = " + dateStringRenew);

        Date currentDate = new Date();
        System.out.println("currentDate     = " + currentDate);

        System.out.println();

        System.out.println("date            = " + new Date( date.getTime() ));
        System.out.println("dateString      = " + new Date( currentDate.getTime() ));

        long diffL = currentDate.getTime() - date.getTime();
        Date diff = new Date( diffL );
        System.out.println("diff            = " + diff);
        String diffS = convertMs2TimeInt(diffL);
        System.out.println("diffS           = " + diffS);

        String diffYdhms = getYdhms(diff);
        System.out.println("diffYdhms       = " + diffYdhms);

        System.out.println();

        System.out.println("0-Date          = " + new Date(0L));

        Map<TimeUnit, Long> difff = computeDiff(date, currentDate);
        System.out.println("difff           = " + difff);

    }

    @Test
    public void testStringFormat() {
        int y1 = 22;
        int y2 = 2;
        System.out.println(
                "\n"
                        + "y1 = " + String.format("%1$2dY", y1)
                        + "\n"
                        + "y2 = " + String.format("%1$2dY n", y2)
        );
    }
}
