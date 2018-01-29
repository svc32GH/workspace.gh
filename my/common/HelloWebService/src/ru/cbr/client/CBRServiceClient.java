package ru.cbr.client;

import ru.cbr.web.DailyInfo;
import ru.cbr.web.DailyInfoSoap;

public class CBRServiceClient {

    public static void main(String[] args) {
        DailyInfo daylyInfo = new DailyInfo();
        DailyInfoSoap daylyInfoSoap = daylyInfo.getDailyInfoSoap();

        String latestDate = daylyInfoSoap.getLatestDate();
        System.out.println("latestDate     = " + latestDate);

        String latestDateSeld = daylyInfoSoap.getLatestDateSeld();
        System.out.println("latestDateSeld = " + latestDateSeld);
    }

}
