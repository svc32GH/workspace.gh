package com.svc32.common.unlocker;


public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Unlocker ul = new Unlocker();
        ul.setName("frmUnlocker");

        System.out.println(ul);
    }
}
