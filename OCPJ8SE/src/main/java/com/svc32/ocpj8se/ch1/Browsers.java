package com.svc32.ocpj8se.ch1;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 30.12.2017.
 **/
public class Browsers {
    static class Browser {
        public void go() {
            System.out.println("Inside Browser");
        }
    }
    static class Firefox extends Browser {
        public void go() {
            System.out.println("Inside Firefox");
        }
    }
    static class IE extends Browser {
        public void go() {
            System.out.println("Inside IE");
        }
    }

    public static void main(String[] args) {
        Browser b = new Firefox();
        IE e = (IE) b;
        e.go();
    }
}
