// Prototype:
// http://info.javarush.ru/eGarmin/2015/03/14/%D0%92%D0%B5%D0%B1-%D1%81%D0%B5%D1%80%D0%B2%D0%B8%D1%81%D1%8B-%D0%A8%D0%B0%D0%B3-1-%D0%A7%D1%82%D0%BE-%D1%82%D0%B0%D0%BA%D0%BE%D0%B5-%D0%B2%D0%B5%D0%B1-%D1%81%D0%B5%D1%80%D0%B2%D0%B8%D1%81-%D0%B8-%D0%BA%D0%B0%D0%BA-%D1%81-%D0%BD%D0%B8%D0%BC-%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0%D1%82%D1%8C-.html
package com.svc32.hws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

// это аннотации, т.е. способ отметить наши классы и методы,
// как связанные с веб-сервисной технологией
// говорим, что наш интерфейс будет работать как веб-сервис
@WebService
// говорим, что веб-сервис будет использоваться для вызова методов
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWebServiceServer {

    // говорим, что этот метод можно вызывать удаленно
    @WebMethod
    public String getHelloString(String name);

}
