package com.svc32.package_a;

public class ClassA implements InterfaceA {

    @Override
    public void test1() {

    }

    protected void test2() {

    }

    public static boolean println(String s) {
        if (s == null) {
            System.out.println("ClassA -> null");
            return false;
        }
        System.out.println("ClassA -> " + s);
        return true;
    }
}
