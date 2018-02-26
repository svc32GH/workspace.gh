package com.svc32.hws;

// таже аннотация, что и при описании интерфейса,
import javax.jws.WebService;

// но здесь используется с параметром endpointInterface,
// указывающим полное имя класса интерфейса нашего веб-сервиса:
@WebService(endpointInterface = "com.svc32.hws.HelloWebServiceServer")
public class HelloWebServiceServerImpl implements HelloWebServiceServer {

    @Override
    public String getHelloString(String name) {
        return "Hello, " + name + "!";
    }

}
