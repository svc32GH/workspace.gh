package com.svc32.hws.client;

// подключаем классы-заглушки
import com.svc32.hws.*;

public class HelloWebServiceClient {
    public static void main(String[] args) {
        // подключаемся к тегу service в wsdl описании
        HelloWebServiceServerImplService helloService = new HelloWebServiceServerImplService();
        // получив информацию из тега service подключаемся к самому веб-сервису
        HelloWebServiceServer hello = helloService.getHelloWebServiceServerImplPort();

        // обращаемся к веб-сервису и выводим результат в консоль
        System.out.println( hello.getHelloString("JavaRush Community by svc32 ") );
    }
}
