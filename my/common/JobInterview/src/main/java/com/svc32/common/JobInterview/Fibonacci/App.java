package com.svc32.common.JobInterview.Fibonacci;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 5/21/2017.
 **/
public class App {
    static List<Long> fibonacciNumbers = new ArrayList<Long>();

    static {
        fibonacciNumbers.add(1L);
        long[] row = new long[]{1, 1, 2};
        while (row[1] > 0) {
            fibonacciNumbers.add(row[1]);
            row[2] = row[1];
            row[1] = row[0] + row[1];
            row[0] = row[2];
        }
    }

    public static long fibonacci(int numb) {
        if (fibonacciNumbers.size() >= numb) {
            return fibonacciNumbers.get(numb - 1);
        } else throw new NumberFormatException("Too big digit");
    }

    public static void main(String[] args) {

        System.out.println("Fibonacci from 1 to " + fibonacciNumbers.size());
        //fibonacciNumbers.stream().forEach(System.out::println);

        System.out.println(fibonacci(10));



    }
}
