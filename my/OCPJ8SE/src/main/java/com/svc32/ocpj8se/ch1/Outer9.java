package com.svc32.ocpj8se.ch1;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 30.12.2017.
 **/
public class Outer9 {
    private int x = 24;
    public int getX() {
        String message = "x is ";
        class Inner {
            private int x = Outer9.this.x;
            public void printX() {
                System.out.println(message + x);
            }
        }
        Inner in = new Inner();
        in.printX();
        return x;
    }

    public static void main(String[] args) {
        new Outer9().getX();
        int i = 3;
        i += 1;
        System.out.println(i);
    }
}
