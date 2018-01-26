package ru.javarush.ws;

// таже аннотация, что и при описании интерфейса,
import javax.jws.WebService;

// но здесь используется с параметром endpointInterface,
// указывающим полное имя класса интерфейса нашего веб-сервиса:
@WebService(endpointInterface = "ru.javarush.ws.HelloWebService")
public class HelloWebServiceImpl implements HelloWebService {

    @Override
    public String getHelloString(String name) {
        return "Hello, " + name + "!";
    }

}
