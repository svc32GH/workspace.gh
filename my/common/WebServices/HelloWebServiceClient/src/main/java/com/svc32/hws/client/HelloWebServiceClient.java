package com.svc32.hws.client;

// нужно, чтобы получить wsdl описание и через него
// дот€нутьс€ до самого веб-сервиса
import java.net.URL;
// такой эксепшн возникнет при работе с объектом URL
import java.net.MalformedURLException;

// классы, чтобы пропарсить xml-ку c wsdl описанием
// и дот€нутьс€ до тега service в нем
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

// интерфейс нашего веб-сервиса (нам больше и нужно)
import com.svc32.hws.HelloWebServiceServer;

public class HelloWebServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        // создаем ссылку на wsdl описание
        URL url = new URL("http://localhost:1986/svc32/hws/hello?wsdl");
        System.out.println("\n" + "url = \n" + url + "\n");

        // ѕараметры следующего конструктора смотрим в самом первом теге WSDL описани€ - definitions
        // 1-ый аргумент смотрим в атрибуте targetNamespace
        // 2-ой аргумент смотрим в атрибуте name
        QName qname = new QName("http://hws.svc32.com/", "HelloWebServiceServerImplService");

        // “еперь мы можем дот€нутьс€ до тега service в wsdl описании,
        Service service = Service.create(url, qname);

        // а далее и до вложенного в него тега port, чтобы
        // получить ссылку на удаленный от нас объект веб-сервиса
        HelloWebServiceServer hello = service.getPort(HelloWebServiceServer.class);

        // ”ра! “еперь можно вызывать удаленный метод
        System.out.println(hello.getHelloString("JavaRush"));
    }

}
