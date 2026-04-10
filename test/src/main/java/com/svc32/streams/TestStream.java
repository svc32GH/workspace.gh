package com.svc32.streams;

import com.svc32.package_a.ClassA;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestStream {

    public static void main(String[] args) {
        System.out.println("================================");

        final String prefix = "Prefix: ";

        List<String> list = new ArrayList<>(Arrays.asList("123","123",null,"234","345"));
//        List<String> list = new ArrayList<>(Arrays.asList("123","123","234","345"));
        list.add("9876543210");
        list.add("0123456789");
        System.out.println(list);
        System.out.println("============ Stream ====================");

        Map map = list.stream()
                .peek(System.out::println)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), s -> s + "+" + s))
//                .collect(Collectors.toList())
//                .forEach(System.out::println);
//                .forEach(e -> System.out.println(e));
//                .forEach(System.out::println);
                ;
        System.out.println(map);
//                Trial f;



//        List<String> list2 = new ArrayList<String>(){{ add("123"); add("234"); add("345"); }};
//        System.out.println(list2);

        System.out.println("================================");
    }






}
