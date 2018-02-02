package com.svc32.ocpj8se.ch1;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 30.12.2017.
 **/
public class FlavorEnum {

    enum Flavors {
        VANILLA, CHOCOLATE, STRAWBERRY
    }

    public static void main(String[] args) {
        System.out.println(Flavors.CHOCOLATE.ordinal());
    }


}
