package com.svc32.ocpj8se.ch1;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 30.12.2017.
 **/
public class Outer6 {
    private int x = 5;

    protected class Inner {
        public /*static*/ int x = 10;
        public void go() {
            System.out.println(x);
            System.out.println(Outer6.this.x);
        }
    }

    public static void main(String[] args) {
        Outer6 out = new Outer6();
        Outer6.Inner in = out.new Inner();
        in.go();
    }
}
