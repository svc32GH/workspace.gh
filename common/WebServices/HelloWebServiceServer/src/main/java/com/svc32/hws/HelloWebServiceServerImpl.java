package com.svc32.hws;

// ���� ���������, ��� � ��� �������� ����������,
import javax.jws.WebService;

// �� ����� ������������ � ���������� endpointInterface,
// ����������� ������ ��� ������ ���������� ������ ���-�������:
@WebService(endpointInterface = "com.svc32.hws.HelloWebServiceServer")
public class HelloWebServiceServerImpl implements HelloWebServiceServer {

    @Override
    public String getHelloString(String name) {
        return "Hello, " + name + "!";
    }

}
