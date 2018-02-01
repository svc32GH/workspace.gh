package com.svc32.hws.endpoint;

// класс, для запуска веб-сервера с веб-сервисами
import javax.xml.ws.Endpoint;
// класс нашего веб-сервиса
import com.svc32.hws.HelloWebServiceServerImpl;

public class HelloWebServicePublisher {

    public static void main(String[] args) {
        // запускаем веб-сервер на порту 1986
        // и по адресу, указанному в первом аргументе,
        // запускаем веб-сервис, передаваемый во втором аргументе
        Endpoint.publish("http://0.0.0.0:1986/svc32/hws/hello", new HelloWebServiceServerImpl());
        System.out.println("***");
    }
}
